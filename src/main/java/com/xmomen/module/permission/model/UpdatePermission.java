package com.xmomen.module.permission.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by tanxinzheng on 16/6/16.
 */
public @Data
class UpdatePermission implements Serializable{

    @NotNull
    private Integer id;
    @NotBlank
    @NotNull
    private String description;
    private Boolean available;
}
