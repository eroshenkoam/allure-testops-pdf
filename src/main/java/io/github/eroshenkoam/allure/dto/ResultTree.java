package io.github.eroshenkoam.allure.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class ResultTree implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ResultTreeGroup> groups = new ArrayList<>();
    private List<ResultTreeLeaf> leaves = new ArrayList<>();

}
