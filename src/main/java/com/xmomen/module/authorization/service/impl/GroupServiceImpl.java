package com.xmomen.module.authorization.service.impl;

import com.github.pagehelper.Page;
import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.page.PageInterceptor;
import com.xmomen.module.authorization.mapper.GroupMapper;
import com.xmomen.module.authorization.model.Group;
import com.xmomen.module.authorization.model.GroupModel;
import com.xmomen.module.authorization.model.GroupQuery;
import com.xmomen.module.authorization.service.GroupService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 0:51:13
 * @version 1.0.0
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupMapper groupMapper;

    /**
     * 新增用户组
     *
     * @param groupModel 新增用户组对象参数
     * @return GroupModel    用户组领域对象
     */
    @Override
    @Transactional
    public GroupModel createGroup(GroupModel groupModel) {
        Group group = createGroup(groupModel.getEntity());
        if(group != null){
            return getOneGroupModel(group.getId());
        }
        return null;
    }

    /**
     * 新增用户组实体对象
     *
     * @param group 新增用户组实体对象参数
     * @return Group 用户组实体对象
     */
    @Override
    @Transactional
    public Group createGroup(Group group) {
        groupMapper.insertSelective(group);
        return group;
    }

    /**
    * 批量新增用户组
    *
    * @param groupModels 新增用户组对象集合参数
    * @return List<GroupModel>    用户组领域对象集合
    */
    @Override
    @Transactional
    public List<GroupModel> createGroups(List<GroupModel> groupModels) {
        List<GroupModel> groupModelList = null;
        for (GroupModel groupModel : groupModels) {
            groupModel = createGroup(groupModel);
            if(groupModel != null){
                if(groupModelList == null){
                    groupModelList = new ArrayList<>();
                }
                groupModelList.add(groupModel);
            }
        }
        return groupModelList;
    }

    /**
    * 更新用户组
    *
    * @param groupModel 更新用户组对象参数
    * @param groupQuery 过滤用户组对象参数
    */
    @Override
    @Transactional
    public void updateGroup(GroupModel groupModel, GroupQuery groupQuery) {
        groupMapper.updateSelectiveByQuery(groupModel.getEntity(), groupQuery);
    }

    /**
     * 更新用户组
     *
     * @param groupModel 更新用户组对象参数
     */
    @Override
    @Transactional
    public void updateGroup(GroupModel groupModel) {
        updateGroup(groupModel.getEntity());
    }

    /**
     * 更新用户组实体对象
     *
     * @param group 新增用户组实体对象参数
     * @return Group 用户组实体对象
     */
    @Override
    @Transactional
    public void updateGroup(Group group) {
        groupMapper.updateSelective(group);
    }

    /**
     * 删除用户组
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteGroup(String[] ids) {
        groupMapper.deletesByPrimaryKey(Arrays.asList(ids));
    }

    /**
    * 删除用户组
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteGroup(String id) {
        groupMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询用户组领域分页对象（带参数条件）
     *
     * @param groupQuery 查询参数
     * @return Page<GroupModel>   用户组参数对象
     */
    @Override
    public Page<GroupModel> getGroupModelPage(GroupQuery groupQuery) {
        PageInterceptor.startPage(groupQuery);
        groupMapper.selectModel(groupQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询用户组领域集合对象（带参数条件）
     *
     * @param groupQuery 查询参数对象
     * @return List<GroupModel> 用户组领域集合对象
     */
    @Override
    public List<GroupModel> getGroupModelList(GroupQuery groupQuery) {
        return groupMapper.selectModel(groupQuery);
    }

    /**
     * 查询用户组
     *
     * @param groupQuery
     * @return
     */
    @Override
    public Page<GroupModel> getUserGroups(GroupQuery groupQuery) {
        return null;
    }

    /**
     * 查询用户组实体对象
     *
     * @param id 主键
     * @return Group 用户组实体对象
     */
    @Override
    public Group getOneGroup(String id) {
        return groupMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return GroupModel 用户组领域对象
     */
    @Override
    public GroupModel getOneGroupModel(String id) {
        return groupMapper.selectModelByPrimaryKey(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param groupQuery 用户组查询参数对象
     * @return GroupModel 用户组领域对象
     */
    @Override
    public GroupModel getOneGroupModel(GroupQuery groupQuery) {
        List<GroupModel> groupModelList = groupMapper.selectModel(groupQuery);
        if(CollectionUtils.isEmpty(groupModelList)){
            return null;
        }
        if(groupModelList.size() > 1){
            throw new BusinessException();
        }
        return groupModelList.get(0);
    }
}
