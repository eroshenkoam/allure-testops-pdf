package io.github.eroshenkoam.allure;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

public class SimpleTest {

    @Test
    @Disabled
    public void testOutput() {
        final String[] args = new String[]{
                "export",
                "--endpoint", "http://localhost:8080",
                "--token", "137ae68e-620f-47a8-b56d-e9f78e120632",
//                "--launch-id", "42",
//                "--tree-id", "7",
                "--launch-id", "44",
                "--tree-id", "14",
//                "--search", "W3siaWQiOiJzdGF0dXMiLCJ0eXBlIjoidGVzdFN0YXR1c0FycmF5IiwidmFsdWUiOlsiZmFpbGVkIl19XQ%3D%3D",
                "--output-file", "/Users/eroshenkoam/Downloads/report.pdf",
        };

        final CommandLine cmd = new CommandLine(new MainCommand());
        final CommandLine.ParseResult parseResult = cmd.parseArgs(args);
        if (!parseResult.errors().isEmpty()) {
            System.out.println(cmd.getUsageMessage());
        }
        cmd.execute(args);
    }

}
