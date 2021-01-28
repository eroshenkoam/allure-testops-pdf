package io.github.eroshenkoam.allure.export;

import io.github.eroshenkoam.allure.dto.ResultTree;
import io.github.eroshenkoam.allure.dto.ResultTreeGroup;
import io.github.eroshenkoam.allure.dto.ResultTreeLeaf;
import io.github.eroshenkoam.allure.utils.FreemarkerUtils;
import io.github.eroshenkoam.allure.utils.PDFUtils;
import io.qameta.allure.ee.client.ServiceBuilder;
import io.qameta.allure.ee.client.TestResultTreeService;
import io.qameta.allure.ee.client.dto.Page;
import io.qameta.allure.ee.client.dto.TestResultTreeGroup;
import io.qameta.allure.ee.client.dto.TestResultTreeLeaf;
import picocli.CommandLine;
import retrofit2.Response;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CommandLine.Command(
        name = "export", mixinStandardHelpOptions = true,
        description = "Export allure launch into PDF"
)
public class ExportCommand implements Runnable {


    @CommandLine.Option(
            names = {"--endpoint"},
            description = "Allure TestOps endpoint"
    )
    protected String endpoint;

    @CommandLine.Option(
            names = {"--token"},
            description = "Allure TestOps token"
    )
    protected String token;

    @CommandLine.Option(
            names = {"--launch-id"},
            description = "Allure TestOps launch id"
    )
    protected Long launchId;

    @CommandLine.Option(
            names = {"--tree-id"},
            description = "Allure TestOps tree id"
    )
    protected Long treeId;

    @CommandLine.Option(
            names = {"--output-file"},
            description = "Export output directory",
            defaultValue = "./report.pdf"
    )
    protected Path outputPath;

    @Override
    public void run() {
        try {
            runUnsafe();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void runUnsafe() throws Exception {
        final ServiceBuilder builder = new ServiceBuilder(endpoint).authToken(token);

        final TestResultTreeService testResultTreeService = builder.create(TestResultTreeService.class);
        final ResultTree tree = getTree(launchId, treeId, testResultTreeService);

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("tree", tree);

        final String html = FreemarkerUtils.render("report.ftl", parameters);
        PDFUtils.saveToFile(html, outputPath.toString());
    }

    private ResultTree getTree(final Long launchId,
                               final Long treeId,
                               final TestResultTreeService service) throws IOException {
        final ResultTree tree = new ResultTree();
        tree.setGroups(getChildGroups(launchId, treeId, new ArrayList<>(), service));
        tree.setLeaves(getChildLeaves(launchId, treeId, new ArrayList<>(), service));
        return tree;
    }

    private List<ResultTreeGroup> getChildGroups(final Long launchId,
                                                 final Long treeId,
                                                 final List<Long> paths,
                                                 final TestResultTreeService service) throws IOException {
        final List<ResultTreeGroup> groups = new ArrayList<>();

        final Response<Page<TestResultTreeGroup>> groupResponse = service
                .getGroups(launchId, treeId, paths, 0, 1000).execute();
        if (!groupResponse.isSuccessful()) {
            throw new RuntimeException(groupResponse.message());
        }

        for (final TestResultTreeGroup responseGroup : groupResponse.body().getContent()) {
            final ResultTreeGroup group = new ResultTreeGroup();
            group.setName(responseGroup.getName());
            group.setPassed(responseGroup.getStatistic().getPassed());
            group.setFailed(responseGroup.getStatistic().getFailed());
            group.setBroken(responseGroup.getStatistic().getBroken());
            group.setSkipped(responseGroup.getStatistic().getSkipped());

            final List<Long> childPath = new ArrayList<>(paths);
            childPath.add(responseGroup.getId());

            group.setGroups(getChildGroups(launchId, treeId, childPath, service));
            group.setLeaves(getChildLeaves(launchId, treeId, childPath, service));

            groups.add(group);
        }

        return groups;
    }

    private List<ResultTreeLeaf> getChildLeaves(final Long launchId,
                                                final Long treeId,
                                                final List<Long> paths,
                                                final TestResultTreeService service) throws IOException {
        final Response<Page<TestResultTreeLeaf>> leavesResponse = service
                .getLeafs(launchId, treeId, paths, 0, 1000).execute();
        if (!leavesResponse.isSuccessful()) {
            throw new RuntimeException(leavesResponse.message());
        }
        return leavesResponse.body().getContent().stream()
                .map(this::toResultTreeLeaf)
                .collect(Collectors.toList());
    }

    private ResultTreeLeaf toResultTreeLeaf(final TestResultTreeLeaf leaf) {
        return new ResultTreeLeaf()
                .setName(leaf.getName())
                .setStatus(leaf.getStatus().value());
    }

}
