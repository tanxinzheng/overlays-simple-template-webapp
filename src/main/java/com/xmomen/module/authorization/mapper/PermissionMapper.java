package com.xmomen.module.authorization.mapper;

import com.xmomen.module.authorization.model.Permission;
import com.xmomen.module.authorization.model.PermissionModel;
import com.xmomen.module.authorization.model.PermissionQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
public interface PermissionMapper {

    List<Permission> select(PermissionQuery permissionQuery);

    List<PermissionModel> selectModel(PermissionQuery permissionQuery);

    Permission selectByPrimaryKey(String primaryKey);

    PermissionModel selectModelByPrimaryKey(String primaryKey);

    int deleteByPrimaryKey(String primaryKey);

    int deletesByPrimaryKey(@Param("ids") List<String> primaryKeys);

    int insertSelective(Permission record);

    int updateSelective(Permission record);

    int updateSelectiveByQuery(@Param("record") Permission record, @Param("query") PermissionQuery example);

    void insertByBatch(@Param(value = "list") List<PermissionModel> permissionModels);
}
