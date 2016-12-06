package com.xmomen.module.authorization.mapper;

import com.xmomen.module.authorization.entity.Group;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:20
 * @version 1.0.0
 */
public interface GroupMapperExt {

    String GroupMapperNameSpace = "com.xmomen.module.authorization.mapper.GroupMapperExt.";


    @Select("select r.* from xmo_group r left join xmo_user_group ur on ur.group_id = r.id where ur.USER_ID = #{userId}")
    @ResultType(Group.class)
    List<Group> getGroupList(String userId);

}
