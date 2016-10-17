package com.xmomen.module.authorization.service.impl;

import com.xmomen.module.authorization.entity.Group;
import com.xmomen.module.authorization.entity.GroupExample;
import com.xmomen.module.authorization.mapper.GroupMapperExt;
import com.xmomen.module.authorization.model.GroupCreate;
import com.xmomen.module.authorization.model.GroupQuery;
import com.xmomen.module.authorization.model.GroupUpdate;
import com.xmomen.module.authorization.model.GroupModel;
import com.xmomen.module.authorization.service.GroupService;
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
 * @date    2016-10-17 0:59:11
 * @version 1.0.0
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增组
     *
     * @param groupCreate 新增组对象参数
     * @return GroupModel    组领域对象
     */
    @Override
    @Transactional
    public GroupModel createGroup(GroupCreate groupCreate) {
        Group group = createGroup(groupCreate.getEntity());
        if(group != null){
            return getOneGroupModel(group.getId());
        }
        return null;
    }

    /**
     * 新增组实体对象
     *
     * @param group 新增组实体对象参数
     * @return Group 组实体对象
     */
    @Override
    @Transactional
    public Group createGroup(Group group) {
        return mybatisDao.insertByModel(group);
    }

    /**
     * 更新组
     *
     * @param groupUpdate 更新组对象参数
     */
    @Override
    @Transactional
    public void updateGroup(GroupUpdate groupUpdate) {
        mybatisDao.update(groupUpdate.getEntity());
    }

    /**
     * 更新组实体对象
     *
     * @param group 新增组实体对象参数
     * @return Group 组实体对象
     */
    @Override
    @Transactional
    public void updateGroup(Group group) {
        mybatisDao.update(group);
    }

    /**
     * 删除组
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteGroup(String[] ids) {
        GroupExample groupExample = new GroupExample();
        groupExample.createCriteria().andIdIn(Arrays.asList((String[]) ids));
        mybatisDao.deleteByExample(groupExample);
    }

    /**
    * 删除组
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteGroup(String id) {
        mybatisDao.deleteByPrimaryKey(Group.class, id);
    }

    /**
     * 查询组领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param groupQuery 查询参数
     * @return Page<GroupModel>   组参数对象
     */
    @Override
    public Page<GroupModel> getGroupModelPage(int limit, int offset, GroupQuery groupQuery) {
        return (Page<GroupModel>) mybatisDao.selectPage(GroupMapperExt.GroupMapperNameSpace + "getGroupModel", groupQuery, limit, offset);
    }

    /**
     * 查询组领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<GroupModel> 组领域对象
     */
    @Override
    public Page<GroupModel> getGroupModelPage(int limit, int offset) {
        return (Page<GroupModel>) mybatisDao.selectPage(GroupMapperExt.GroupMapperNameSpace + "getGroupModel", null, limit, offset);
    }

    /**
     * 查询组领域集合对象（带参数条件）
     *
     * @param groupQuery 查询参数对象
     * @return List<GroupModel> 组领域集合对象
     */
    @Override
    public List<GroupModel> getGroupModelList(GroupQuery groupQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(GroupMapperExt.GroupMapperNameSpace + "getGroupModel", groupQuery);
    }

    /**
     * 查询组领域集合对象（无参数条件）
     *
     * @return List<GroupModel> 组领域集合对象
     */
    @Override
    public List<GroupModel> getGroupModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(GroupMapperExt.GroupMapperNameSpace + "getGroupModel");
    }

    /**
     * 查询组实体对象
     *
     * @param id 主键
     * @return Group 组实体对象
     */
    @Override
    public Group getOneGroup(String id) {
        return mybatisDao.selectByPrimaryKey(Group.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return GroupModel 组领域对象
     */
    @Override
    public GroupModel getOneGroupModel(String id) {
        GroupQuery groupQuery = new GroupQuery();
        groupQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(GroupMapperExt.GroupMapperNameSpace + "getGroupModel", groupQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param groupQuery 组查询参数对象
     * @return GroupModel 组领域对象
     */
    @Override
    public GroupModel getOneGroupModel(GroupQuery groupQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(GroupMapperExt.GroupMapperNameSpace + "getGroupModel", groupQuery);
    }
}
