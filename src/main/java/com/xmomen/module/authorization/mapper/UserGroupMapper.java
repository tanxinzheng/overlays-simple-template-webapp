package com.xmomen.module.authorization.mapper;

import com.xmomen.module.authorization.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
public interface UserGroupMapper {

    List<UserGroup> select(UserGroupQuery userGroupQuery);

    List<UserGroupModel> selectModel(UserGroupQuery userGroupQuery);

    UserGroup selectByPrimaryKey(String primaryKey);

    UserGroupModel selectModelByPrimaryKey(String primaryKey);

    int deleteByPrimaryKey(String primaryKey);

    int deletesByPrimaryKey(@Param("ids") List<String> primaryKeys);

    int insertSelective(UserGroup record);

    int updateSelective(UserGroup record);

    int updateSelectiveByQuery(@Param("record") UserGroup record, @Param("query") UserGroupQuery example);

    List<GroupModel> selectUserGroup(UserGroupQuery userGroupQuery);
}
