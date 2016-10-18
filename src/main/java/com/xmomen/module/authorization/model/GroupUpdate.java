package com.xmomen.module.authorization.model;

import lombok.Data;
import com.xmomen.module.authorization.entity.Group;
import org.springframework.beans.BeanUtils;

    import java.lang.Boolean;
    import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-18 23:09:38
 * @version 1.0.0
 */
public @Data class GroupUpdate implements Serializable {

    /** 主键 */
    private String id;
    /** 用户组代码 */
    private String groupCode;
    /** 用户组名称 */
    private String groupName;
    /** 用户组描述 */
    private String description;
    /** 激活 */
    private Boolean active;


    public Group getEntity(){
        Group group = new Group();
        BeanUtils.copyProperties(this, group);
        return group;
    }
}
