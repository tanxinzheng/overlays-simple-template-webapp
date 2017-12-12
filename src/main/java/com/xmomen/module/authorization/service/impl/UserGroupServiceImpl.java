package com.xmomen.module.authorization.service.impl;

import com.github.pagehelper.Page;
import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.page.PageInterceptor;
import com.xmomen.module.authorization.mapper.UserGroupMapper;
import com.xmomen.module.authorization.model.GroupModel;
import com.xmomen.module.authorization.model.UserGroup;
import com.xmomen.module.authorization.model.UserGroupModel;
import com.xmomen.module.authorization.model.UserGroupQuery;
import com.xmomen.module.authorization.service.UserGroupService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    UserGroupMapper userGroupMapper;

    /**
     * 新增用户组关联
     *
     * @param userGroupModel 新增用户组关联对象参数
     * @return UserGroupModel    用户组关联领域对象
     */
    @Override
    @Transactional
    public UserGroupModel createUserGroup(UserGroupModel userGroupModel) {
        UserGroup userGroup = createUserGroup(userGroupModel.getEntity());
        if(userGroup != null){
            return getOneUserGroupModel(userGroup.getId());
        }
        return null;
    }

    /**
     * 新增用户组关联实体对象
     *
     * @param userGroup 新增用户组关联实体对象参数
     * @return UserGroup 用户组关联实体对象
     */
    @Override
    @Transactional
    public UserGroup createUserGroup(UserGroup userGroup) {
        userGroupMapper.insertSelective(userGroup);
        return userGroup;
    }

    /**
    * 批量新增用户组关联
    *
    * @param userId 用户Id
    * @return groupIds    用户组Id集合
    */
    @Override
    @Transactional
    public void createUserGroups(String userId, String... groupIds) {
        for (String groupId : groupIds) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUserId(userId);
            userGroup.setGroupId(groupId);
            createUserGroup(userGroup);
        }
    }

    /**
    * 更新用户组关联
    *
    * @param userGroupModel 更新用户组关联对象参数
    * @param userGroupQuery 过滤用户组关联对象参数
    */
    @Override
    @Transactional
    public void updateUserGroup(UserGroupModel userGroupModel, UserGroupQuery userGroupQuery) {
        userGroupMapper.updateSelectiveByQuery(userGroupModel.getEntity(), userGroupQuery);
    }

    /**
     * 更新用户组关联
     *
     * @param userGroupModel 更新用户组关联对象参数
     */
    @Override
    @Transactional
    public void updateUserGroup(UserGroupModel userGroupModel) {
        updateUserGroup(userGroupModel.getEntity());
    }

    /**
     * 更新用户组关联实体对象
     *
     * @param userGroup 新增用户组关联实体对象参数
     * @return UserGroup 用户组关联实体对象
     */
    @Override
    @Transactional
    public void updateUserGroup(UserGroup userGroup) {
        userGroupMapper.updateSelective(userGroup);
    }

    /**
     * 删除用户组关联
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteUserGroup(String[] ids) {
        if(ArrayUtils.isEmpty(ids)){
            return;
        }
        userGroupMapper.deletesByPrimaryKey(Arrays.asList(ids));
    }

    /**
    * 删除用户组关联
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteUserGroup(String id) {
        userGroupMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询用户组关联领域分页对象（带参数条件）
     *
     * @param userGroupQuery 查询参数
     * @return Page<UserGroupModel>   用户组关联参数对象
     */
    @Override
    public Page<UserGroupModel> getUserGroupModelPage(UserGroupQuery userGroupQuery) {
        PageInterceptor.startPage(userGroupQuery);
        userGroupMapper.selectModel(userGroupQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询用户组关联领域集合对象（带参数条件）
     *
     * @param userGroupQuery 查询参数对象
     * @return List<UserGroupModel> 用户组关联领域集合对象
     */
    @Override
    public List<UserGroupModel> getUserGroupModelList(UserGroupQuery userGroupQuery) {
        return userGroupMapper.selectModel(userGroupQuery);
    }

    /**
     * 查询用户组关联实体对象
     *
     * @param id 主键
     * @return UserGroup 用户组关联实体对象
     */
    @Override
    public UserGroup getOneUserGroup(String id) {
        return userGroupMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return UserGroupModel 用户组关联领域对象
     */
    @Override
    public UserGroupModel getOneUserGroupModel(String id) {
        return userGroupMapper.selectModelByPrimaryKey(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param userGroupQuery 用户组关联查询参数对象
     * @return UserGroupModel 用户组关联领域对象
     */
    @Override
    public UserGroupModel getOneUserGroupModel(UserGroupQuery userGroupQuery) {
        List<UserGroupModel> userGroupModelList = userGroupMapper.selectModel(userGroupQuery);
        if(CollectionUtils.isEmpty(userGroupModelList)){
            return null;
        }
        if(userGroupModelList.size() > 1){
            throw new BusinessException();
        }
        return userGroupModelList.get(0);
    }

    /**
     * 查询用户所属组
     * @param userGroupQuery
     * @return
     */
    @Override
    public Page<GroupModel> getUserGroupsPage(UserGroupQuery userGroupQuery) {
        PageInterceptor.startPage(userGroupQuery);
        userGroupMapper.selectUserGroup(userGroupQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询用户组
     *
     * @param userGroupQuery
     * @return
     */
    @Override
    public List<GroupModel> getUserGroups(UserGroupQuery userGroupQuery) {
        return userGroupMapper.selectUserGroup(userGroupQuery);
    }

    /**
     * 批量删除
     *
     * @param userId
     * @param groupIds
     */
    @Override
    public void deleteUserGroups(String userId, String[] groupIds) {
        UserGroupQuery userGroupQuery = new UserGroupQuery();
        userGroupQuery.setUserId(userId);
        userGroupQuery.setGroupIds(groupIds);
        userGroupMapper.deleteByQuery(userGroupQuery);
    }
}
