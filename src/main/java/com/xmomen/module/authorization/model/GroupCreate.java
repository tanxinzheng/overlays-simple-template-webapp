package com.xmomen.module.authorization.model;

import lombok.Data;
import com.xmomen.module.authorization.entity.Group;
import org.springframework.beans.BeanUtils;

    import java.lang.Boolean;
    import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-17 0:59:11
 * @version 1.0.0
 */
public @Data class GroupCreate implements Serializable {

    private String id;
    private String groupCode;
    private String groupName;
    private String description;
    private Boolean active;

    public Group getEntity(){
        Group group = new Group();
        BeanUtils.copyProperties(this, group);
        return group;
    }
}
