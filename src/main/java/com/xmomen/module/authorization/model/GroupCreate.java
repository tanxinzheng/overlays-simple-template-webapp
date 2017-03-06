package com.xmomen.module.authorization.model;

import com.xmomen.module.authorization.entity.Group;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:20
 * @version 1.0.0
 */
public @Data class GroupCreate implements Serializable {

    /** 主键 */
    private String id;
    /** 用户组代码 */
    @NotBlank(message = "com.xmomen.module.authorization.model.groupCode.NotBlank.message")
    @NotNull(message = "com.xmomen.module.authorization.model.groupCode.NotNull.message")
    private String groupCode;
    /** 用户组名称 */
    @NotBlank(message = "com.xmomen.module.authorization.model.groupName.NotBlank.message")
    @NotNull(message = "com.xmomen.module.authorization.model.groupName.NotNull.message")
    private String groupName;
    /** 用户组描述 */
    @Length(max = 2, message = "")
    @Range(min = 213, max = 323, message = "")
    private String description;
    /** 激活 */
    private Boolean active;

    public Group getEntity(){
        Group group = new Group();
        BeanUtils.copyProperties(this, group);
        return group;
    }
}
