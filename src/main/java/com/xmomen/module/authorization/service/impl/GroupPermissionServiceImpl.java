package com.xmomen.module.authorization.service.impl;

import com.xmomen.module.authorization.entity.GroupPermission;
import com.xmomen.module.authorization.entity.GroupPermissionExample;
import com.xmomen.module.authorization.mapper.GroupPermissionMapperExt;
import com.xmomen.module.authorization.model.GroupPermissionCreate;
import com.xmomen.module.authorization.model.GroupPermissionQuery;
import com.xmomen.module.authorization.model.GroupPermissionUpdate;
import com.xmomen.module.authorization.model.GroupPermissionModel;
import com.xmomen.module.authorization.service.GroupPermissionService;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-18 23:46:57
 * @version 1.0.0
 */
@Service
public class GroupPermissionServiceImpl implements GroupPermissionService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增组权限
     *
     * @param groupPermissionCreate 新增组权限对象参数
     * @return GroupPermissionModel    组权限领域对象
     */
    @Override
    @Transactional
    public GroupPermissionModel createGroupPermission(GroupPermissionCreate groupPermissionCreate) {
        GroupPermission groupPermission = createGroupPermission(groupPermissionCreate.getEntity());
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
        return mybatisDao.insertByModel(groupPermission);
    }

    /**
     * 更新组权限
     *
     * @param groupPermissionUpdate 更新组权限对象参数
     */
    @Override
    @Transactional
    public void updateGroupPermission(GroupPermissionUpdate groupPermissionUpdate) {
        mybatisDao.update(groupPermissionUpdate.getEntity());
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
        mybatisDao.update(groupPermission);
    }

    /**
     * 删除组权限
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteGroupPermission(String[] ids) {
        GroupPermissionExample groupPermissionExample = new GroupPermissionExample();
        groupPermissionExample.createCriteria().andIdIn(Arrays.asList((String[]) ids));
        mybatisDao.deleteByExample(groupPermissionExample);
    }

    /**
    * 删除组权限
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteGroupPermission(String id) {
        mybatisDao.deleteByPrimaryKey(GroupPermission.class, id);
    }

    /**
     * 查询组权限领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param groupPermissionQuery 查询参数
     * @return Page<GroupPermissionModel>   组权限参数对象
     */
    @Override
    public Page<GroupPermissionModel> getGroupPermissionModelPage(int limit, int offset, GroupPermissionQuery groupPermissionQuery) {
        return (Page<GroupPermissionModel>) mybatisDao.selectPage(GroupPermissionMapperExt.GroupPermissionMapperNameSpace + "getGroupPermissionModel", groupPermissionQuery, limit, offset);
    }

    /**
     * 查询组权限领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<GroupPermissionModel> 组权限领域对象
     */
    @Override
    public Page<GroupPermissionModel> getGroupPermissionModelPage(int limit, int offset) {
        return (Page<GroupPermissionModel>) mybatisDao.selectPage(GroupPermissionMapperExt.GroupPermissionMapperNameSpace + "getGroupPermissionModel", null, limit, offset);
    }

    /**
     * 查询组权限领域集合对象（带参数条件）
     *
     * @param groupPermissionQuery 查询参数对象
     * @return List<GroupPermissionModel> 组权限领域集合对象
     */
    @Override
    public List<GroupPermissionModel> getGroupPermissionModelList(GroupPermissionQuery groupPermissionQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(GroupPermissionMapperExt.GroupPermissionMapperNameSpace + "getGroupPermissionModel", groupPermissionQuery);
    }

    /**
     * 查询组权限领域集合对象（无参数条件）
     *
     * @return List<GroupPermissionModel> 组权限领域集合对象
     */
    @Override
    public List<GroupPermissionModel> getGroupPermissionModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(GroupPermissionMapperExt.GroupPermissionMapperNameSpace + "getGroupPermissionModel");
    }

    /**
     * 查询组权限实体对象
     *
     * @param id 主键
     * @return GroupPermission 组权限实体对象
     */
    @Override
    public GroupPermission getOneGroupPermission(String id) {
        return mybatisDao.selectByPrimaryKey(GroupPermission.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return GroupPermissionModel 组权限领域对象
     */
    @Override
    public GroupPermissionModel getOneGroupPermissionModel(String id) {
        GroupPermissionQuery groupPermissionQuery = new GroupPermissionQuery();
        groupPermissionQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(GroupPermissionMapperExt.GroupPermissionMapperNameSpace + "getGroupPermissionModel", groupPermissionQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param groupPermissionQuery 组权限查询参数对象
     * @return GroupPermissionModel 组权限领域对象
     */
    @Override
    public GroupPermissionModel getOneGroupPermissionModel(GroupPermissionQuery groupPermissionQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(GroupPermissionMapperExt.GroupPermissionMapperNameSpace + "getGroupPermissionModel", groupPermissionQuery);
    }
}
