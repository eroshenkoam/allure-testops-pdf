package io.github.eroshenkoam.allure.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ResultTreeLeaf implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String status;

}
