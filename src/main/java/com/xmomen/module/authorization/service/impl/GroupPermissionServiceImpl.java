package com.xmomen.module.authorization.service.impl;

import com.xmomen.framework.mybatis.page.PageInterceptor;
import com.xmomen.module.authorization.model.*;
import com.xmomen.module.authorization.mapper.GroupPermissionMapper;
import com.xmomen.module.authorization.service.GroupPermissionService;
import com.xmomen.framework.mybatis.page.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@Service
public class GroupPermissionServiceImpl implements GroupPermissionService {

    @Autowired
    GroupPermissionMapper groupPermissionMapper;

    /**
     * 新增组权限
     *
     * @param groupPermissionModel 新增组权限对象参数
     * @return GroupPermissionModel    组权限领域对象
     */
    @Override
    @Transactional
    public GroupPermissionModel createGroupPermission(GroupPermissionModel groupPermissionModel) {
        GroupPermission groupPermission = createGroupPermission(groupPermissionModel.getEntity());
        if(groupPermission != null){
            return getOneGroupPermissionModel(groupPermission.getId());
        }
        return null;
    }

    /**
     * 新增组权限实体对象
     *
     * @param groupPermission 新增组权限实体对象参数
     * @return GroupPermission 组权限实体对象
     */
    @Override
    @Transactional
    public GroupPermission createGroupPermission(GroupPermission groupPermission) {
        groupPermissionMapper.insertSelective(groupPermission);
        return groupPermission;
    }

    /**
    * 批量新增组权限
    *
    * @param groupPermissionModels 新增组权限对象集合参数
    * @return List<GroupPermissionModel>    组权限领域对象集合
    */
    @Override
    @Transactional
    public void createGroupPermissions(String groupId, String... permissionIds) {
        for (String permissionId : permissionIds) {
            GroupPermission groupPermission = new GroupPermission();
            groupPermission.setGroupId(groupId);
            groupPermission.setPermissionId(permissionId);
            createGroupPermission(groupPermission);
        }
    }

    /**
    * 更新组权限
    *
    * @param groupPermissionModel 更新组权限对象参数
    * @param groupPermissionQuery 过滤组权限对象参数
    */
    @Override
    @Transactional
    public void updateGroupPermission(GroupPermissionModel groupPermissionModel, GroupPermissionQuery groupPermissionQuery) {
        groupPermissionMapper.updateSelectiveByQuery(groupPermissionModel.getEntity(), groupPermissionQuery);
    }

    /**
     * 更新组权限
     *
     * @param groupPermissionModel 更新组权限对象参数
     */
    @Override
    @Transactional
    public void updateGroupPermission(GroupPermissionModel groupPermissionModel) {
        updateGroupPermission(groupPermissionModel.getEntity());
    }

    /**
     * 更新组权限实体对象
     *
     * @param groupPermission 新增组权限实体对象参数
     * @return GroupPermission 组权限实体对象
     */
    @Override
    @Transactional
    public void updateGroupPermission(GroupPermission groupPermission) {
        groupPermissionMapper.updateSelective(groupPermission);
    }

    /**
     * 删除组权限
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteGroupPermission(String[] ids) {
        groupPermissionMapper.deletesByPrimaryKey(Arrays.asList(ids));
    }

    /**
    * 删除组权限
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteGroupPermission(String id) {
        groupPermissionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询组权限领域分页对象（带参数条件）
     *
     * @param groupPermissionQuery 查询参数
     * @return Page<GroupPermissionModel>   组权限参数对象
     */
    @Override
    public Page<GroupPermissionModel> getGroupPermissionModelPage(GroupPermissionQuery groupPermissionQuery) {
        PageInterceptor.startPage(groupPermissionQuery);
        groupPermissionMapper.selectModel(groupPermissionQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询组权限领域集合对象（带参数条件）
     *
     * @param groupPermissionQuery 查询参数对象
     * @return List<GroupPermissionModel> 组权限领域集合对象
     */
    @Override
    public List<GroupPermissionModel> getGroupPermissionModelList(GroupPermissionQuery groupPermissionQuery) {
        return groupPermissionMapper.selectModel(groupPermissionQuery);
    }

    /**
     * 查询组权限实体对象
     *
     * @param id 主键
     * @return GroupPermission 组权限实体对象
     */
    @Override
    public GroupPermission getOneGroupPermission(String id) {
        return groupPermissionMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return GroupPermissionModel 组权限领域对象
     */
    @Override
    public GroupPermissionModel getOneGroupPermissionModel(String id) {
        return groupPermissionMapper.selectModelByPrimaryKey(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param groupPermissionQuery 组权限查询参数对象
     * @return GroupPermissionModel 组权限领域对象
     */
    @Override
    public GroupPermissionModel getOneGroupPermissionModel(GroupPermissionQuery groupPermissionQuery) throws TooManyResultsException {
        List<GroupPermissionModel> groupPermissionModelList = groupPermissionMapper.selectModel(groupPermissionQuery);
        if(CollectionUtils.isEmpty(groupPermissionModelList)){
            return null;
        }
        if(groupPermissionModelList.size() > 1){
            throw new TooManyResultsException();
        }
        return groupPermissionModelList.get(0);
    }



    /**
     * 查询用户组权限
     *
     * @param groupPermissionQuery
     * @return
     */
    @Override
    public Page<PermissionModel> getGroupPermissions(GroupPermissionQuery groupPermissionQuery) {
        PageInterceptor.startPage(groupPermissionQuery);
        groupPermissionMapper.selectGroupPermissions(groupPermissionQuery);
        return PageInterceptor.endPage();
    }
}
