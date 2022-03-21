package io.github.eroshenkoam.allure;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

public class SimpleTest {

    @Test
    public void testSimple() {
        final CommandLine cmd = new CommandLine(new MainCommand());
        cmd.execute(
                "export",
                "--endpoint", "http://localhost:8080",
                "--token", "bd29a609-aeb0-4442-81da-3f543133ac04",
                "--launch-id", "1",
                "--tree-id", "1",
                "--status.color.passed", "#000",
                "--output-file", "/Users/eroshenkoam/Downloads/report.pdf"
        );
    }

}
