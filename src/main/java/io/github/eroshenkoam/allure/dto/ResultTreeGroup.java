package io.github.eroshenkoam.allure.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class ResultTreeGroup implements Serializable {

    private String name;

    private long passed;
    private long failed;
    private long broken;
    private long skipped;

    private List<ResultTreeGroup> groups = new ArrayList<>();
    private List<ResultTreeLeaf> leaves = new ArrayList<>();

}
