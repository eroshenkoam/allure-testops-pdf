package io.github.eroshenkoam.allure;

import io.github.eroshenkoam.allure.export.ExportCommand;
import picocli.CommandLine;

@CommandLine.Command(
        name = "allure-testops-pdf", mixinStandardHelpOptions = true,
        subcommands = {ExportCommand.class}
)
public class MainCommand implements Runnable{

    @Override
    public void run() {
    }

}
