package com.xmomen.module.user.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Jeng on 2016/1/7.
 */
public @Data
class UpdateUser implements Serializable {

    @NotNull
    private Integer id;
    private String username;
    private String email;
    private String realname;
    private String phoneNumber;
    private Integer age;
    private String qq;
    private String officeTel;
    private Boolean locked;
    /**
     * 1-男，2女
     */
    private Integer sex;
    
    private Integer[] userGroupIds;

}
