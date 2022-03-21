package io.github.eroshenkoam.allure.config;

import lombok.Data;
import lombok.experimental.Accessors;
import picocli.CommandLine;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class StatusColor implements Serializable {

    private static final long serialVersionUID = 1L;

    @CommandLine.Option(names = "--status.color.passed", defaultValue = "#97cc64")
    private String passed;
    @CommandLine.Option(names = "--status.color.failed", defaultValue = "#fd5a3e")
    private String failed;
    @CommandLine.Option(names = "--status.color.broken", defaultValue = "#ffd050")
    private String broken;
    @CommandLine.Option(names = "--status.color.skipped", defaultValue = "#aaa")
    private String skipped;

}
