package io.github.eroshenkoam.allure.config;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

@Data
@Accessors(chain = true)
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, String> statusColor;

}
