package com.xmomen.module.authorization.service.impl;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.authorization.entity.Permission;
import com.xmomen.module.authorization.entity.PermissionExample;
import com.xmomen.module.authorization.mapper.PermissionMapperExt;
import com.xmomen.module.authorization.model.PermissionCreate;
import com.xmomen.module.authorization.model.PermissionModel;
import com.xmomen.module.authorization.model.PermissionQuery;
import com.xmomen.module.authorization.model.PermissionUpdate;
import com.xmomen.module.authorization.service.PermissionService;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:20
 * @version 1.0.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增权限
     *
     * @param permissionCreate 新增权限对象参数
     * @return PermissionModel    权限领域对象
     */
    @Override
    @Transactional
    public PermissionModel createPermission(PermissionCreate permissionCreate) {
        Permission permission = createPermission(permissionCreate.getEntity());
        if(permission != null){
            return getOnePermissionModel(permission.getId());
        }
        return null;
    }

    /**
     * 批量新增权限
     *
     * @param permissionCreates 新增权限对象集合参数
     * @return List<PermissionModel>    权限领域对象集合
     */
    @Override
    @Transactional
    public List<PermissionModel> createPermissions(List<PermissionCreate> permissionCreates) {
        List<PermissionModel> permissionModelList = null;
        for (PermissionCreate permissionCreate : permissionCreates) {
            PermissionModel permissionModel = createPermission(permissionCreate);

            if(permissionModel != null){
                if(permissionModelList == null){
                    permissionModelList = new ArrayList<>();
                }
                permissionModelList.add(permissionModel);
            }
        }
        return permissionModelList;
    }

    /**
     * 新增权限实体对象
     *
     * @param permission 新增权限实体对象参数
     * @return Permission 权限实体对象
     */
    @Override
    @Transactional
    public Permission createPermission(Permission permission) {
        permission.setPermissionCode(permission.getPermissionCode().toUpperCase());
        return mybatisDao.insertByModel(permission);
    }

    /**
     * 更新权限
     *
     * @param permissionUpdate 更新权限对象参数
     */
    @Override
    @Transactional
    public void updatePermission(PermissionUpdate permissionUpdate) {
        mybatisDao.update(permissionUpdate.getEntity());
    }

    /**
     * 更新权限实体对象
     *
     * @param permission 新增权限实体对象参数
     * @return Permission 权限实体对象
     */
    @Override
    @Transactional
    public void updatePermission(Permission permission) {
        mybatisDao.update(permission);
    }

    /**
     * 删除权限
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deletePermission(String[] ids) {
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andIdIn(Arrays.asList((String[]) ids));
        mybatisDao.deleteByExample(permissionExample);
    }

    /**
    * 删除权限
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deletePermission(String id) {
        mybatisDao.deleteByPrimaryKey(Permission.class, id);
    }

    /**
     * 查询权限领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param permissionQuery 查询参数
     * @return Page<PermissionModel>   权限参数对象
     */
    @Override
    public Page<PermissionModel> getPermissionModelPage(int limit, int offset, PermissionQuery permissionQuery) {
        return (Page<PermissionModel>) mybatisDao.selectPage(PermissionMapperExt.PermissionMapperNameSpace + "getPermissionModel", permissionQuery, limit, offset);
    }

    /**
     * 查询权限领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<PermissionModel> 权限领域对象
     */
    @Override
    public Page<PermissionModel> getPermissionModelPage(int limit, int offset) {
        return (Page<PermissionModel>) mybatisDao.selectPage(PermissionMapperExt.PermissionMapperNameSpace + "getPermissionModel", null, limit, offset);
    }

    /**
     * 查询权限领域集合对象（带参数条件）
     *
     * @param permissionQuery 查询参数对象
     * @return List<PermissionModel> 权限领域集合对象
     */
    @Override
    public List<PermissionModel> getPermissionModelList(PermissionQuery permissionQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(PermissionMapperExt.PermissionMapperNameSpace + "getPermissionModel", permissionQuery);
    }

    /**
     * 查询权限领域集合对象（无参数条件）
     *
     * @return List<PermissionModel> 权限领域集合对象
     */
    @Override
    public List<PermissionModel> getPermissionModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(PermissionMapperExt.PermissionMapperNameSpace + "getPermissionModel");
    }

    /**
     * 查询权限实体对象
     *
     * @param id 主键
     * @return Permission 权限实体对象
     */
    @Override
    public Permission getOnePermission(String id) {
        return mybatisDao.selectByPrimaryKey(Permission.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return PermissionModel 权限领域对象
     */
    @Override
    public PermissionModel getOnePermissionModel(String id) {
        PermissionQuery permissionQuery = new PermissionQuery();
        permissionQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(PermissionMapperExt.PermissionMapperNameSpace + "getPermissionModel", permissionQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param permissionQuery 权限查询参数对象
     * @return PermissionModel 权限领域对象
     */
    @Override
    public PermissionModel getOnePermissionModel(PermissionQuery permissionQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(PermissionMapperExt.PermissionMapperNameSpace + "getPermissionModel", permissionQuery);
    }
}
