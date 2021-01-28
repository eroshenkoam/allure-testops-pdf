package io.github.eroshenkoam.allure;

import picocli.CommandLine;

public class AllureTestOpsPDF {

    public static void main(String[] args) throws Exception {
        final CommandLine cmd = new CommandLine(new MainCommand());
        final CommandLine.ParseResult parseResult = cmd.parseArgs(args);
        if (!parseResult.errors().isEmpty()) {
            System.out.println(cmd.getUsageMessage());
        }
        int exitCode = cmd.execute(args);
        System.exit(exitCode);
    }

}
