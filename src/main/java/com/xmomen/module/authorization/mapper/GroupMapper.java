package com.xmomen.module.authorization.mapper;

import com.xmomen.module.authorization.model.Group;
import com.xmomen.module.authorization.model.GroupModel;
import com.xmomen.module.authorization.model.GroupQuery;
import com.xmomen.module.authorization.model.UserGroupQuery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 0:51:13
 * @version 1.0.0
 */
public interface GroupMapper {

    List<Group> select(GroupQuery groupQuery);

    List<GroupModel> selectModel(GroupQuery groupQuery);

    Group selectByPrimaryKey(String primaryKey);

    GroupModel selectModelByPrimaryKey(String primaryKey);

    int deleteByPrimaryKey(String primaryKey);

    int deletesByPrimaryKey(@Param("ids") List<String> primaryKeys);

    int insertSelective(Group record);

    int updateSelective(Group record);

    int updateSelectiveByQuery(@Param("record") Group record, @Param("query") GroupQuery example);

    GroupModel selectUserGroup(UserGroupQuery userGroupQuery);
}
