package io.github.eroshenkoam.allure.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class ReportMeta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Date createdDate;


}
