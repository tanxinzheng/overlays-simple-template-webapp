package com.xmomen.module.system.service.impl;

import com.xmomen.module.system.entity.DictionaryGroup;
import com.xmomen.module.system.entity.DictionaryGroupExample;
import com.xmomen.module.system.mapper.DictionaryGroupMapperExt;
import com.xmomen.module.system.model.DictionaryGroupCreate;
import com.xmomen.module.system.model.DictionaryGroupQuery;
import com.xmomen.module.system.model.DictionaryGroupUpdate;
import com.xmomen.module.system.model.DictionaryGroupModel;
import com.xmomen.module.system.service.DictionaryGroupService;
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
public class DictionaryGroupServiceImpl implements DictionaryGroupService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增数据字典组
     *
     * @param dictionaryGroupCreate 新增数据字典组对象参数
     * @return DictionaryGroupModel    数据字典组领域对象
     */
    @Override
    @Transactional
    public DictionaryGroupModel createDictionaryGroup(DictionaryGroupCreate dictionaryGroupCreate) {
        DictionaryGroup dictionaryGroup = createDictionaryGroup(dictionaryGroupCreate.getEntity());
        if(dictionaryGroup != null){
            return getOneDictionaryGroupModel(dictionaryGroup.getId());
        }
        return null;
    }

    /**
     * 新增数据字典组实体对象
     *
     * @param dictionaryGroup 新增数据字典组实体对象参数
     * @return DictionaryGroup 数据字典组实体对象
     */
    @Override
    @Transactional
    public DictionaryGroup createDictionaryGroup(DictionaryGroup dictionaryGroup) {
        return mybatisDao.insertByModel(dictionaryGroup);
    }

    /**
     * 更新数据字典组
     *
     * @param dictionaryGroupUpdate 更新数据字典组对象参数
     */
    @Override
    @Transactional
    public void updateDictionaryGroup(DictionaryGroupUpdate dictionaryGroupUpdate) {
        mybatisDao.update(dictionaryGroupUpdate.getEntity());
    }

    /**
     * 更新数据字典组实体对象
     *
     * @param dictionaryGroup 新增数据字典组实体对象参数
     * @return DictionaryGroup 数据字典组实体对象
     */
    @Override
    @Transactional
    public void updateDictionaryGroup(DictionaryGroup dictionaryGroup) {
        mybatisDao.update(dictionaryGroup);
    }

    /**
     * 删除数据字典组
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteDictionaryGroup(String[] ids) {
        DictionaryGroupExample dictionaryGroupExample = new DictionaryGroupExample();
        dictionaryGroupExample.createCriteria().andIdIn(Arrays.asList((String[]) ids));
        mybatisDao.deleteByExample(dictionaryGroupExample);
    }

    /**
    * 删除数据字典组
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteDictionaryGroup(String id) {
        mybatisDao.deleteByPrimaryKey(DictionaryGroup.class, id);
    }

    /**
     * 查询数据字典组领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param dictionaryGroupQuery 查询参数
     * @return Page<DictionaryGroupModel>   数据字典组参数对象
     */
    @Override
    public Page<DictionaryGroupModel> getDictionaryGroupModelPage(int limit, int offset, DictionaryGroupQuery dictionaryGroupQuery) {
        return (Page<DictionaryGroupModel>) mybatisDao.selectPage(DictionaryGroupMapperExt.DictionaryGroupMapperNameSpace + "getDictionaryGroupModel", dictionaryGroupQuery, limit, offset);
    }

    /**
     * 查询数据字典组领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<DictionaryGroupModel> 数据字典组领域对象
     */
    @Override
    public Page<DictionaryGroupModel> getDictionaryGroupModelPage(int limit, int offset) {
        return (Page<DictionaryGroupModel>) mybatisDao.selectPage(DictionaryGroupMapperExt.DictionaryGroupMapperNameSpace + "getDictionaryGroupModel", null, limit, offset);
    }

    /**
     * 查询数据字典组领域集合对象（带参数条件）
     *
     * @param dictionaryGroupQuery 查询参数对象
     * @return List<DictionaryGroupModel> 数据字典组领域集合对象
     */
    @Override
    public List<DictionaryGroupModel> getDictionaryGroupModelList(DictionaryGroupQuery dictionaryGroupQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(DictionaryGroupMapperExt.DictionaryGroupMapperNameSpace + "getDictionaryGroupModel", dictionaryGroupQuery);
    }

    /**
     * 查询数据字典组领域集合对象（无参数条件）
     *
     * @return List<DictionaryGroupModel> 数据字典组领域集合对象
     */
    @Override
    public List<DictionaryGroupModel> getDictionaryGroupModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(DictionaryGroupMapperExt.DictionaryGroupMapperNameSpace + "getDictionaryGroupModel");
    }

    /**
     * 查询数据字典组实体对象
     *
     * @param id 主键
     * @return DictionaryGroup 数据字典组实体对象
     */
    @Override
    public DictionaryGroup getOneDictionaryGroup(String id) {
        return mybatisDao.selectByPrimaryKey(DictionaryGroup.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return DictionaryGroupModel 数据字典组领域对象
     */
    @Override
    public DictionaryGroupModel getOneDictionaryGroupModel(String id) {
        DictionaryGroupQuery dictionaryGroupQuery = new DictionaryGroupQuery();
        dictionaryGroupQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(DictionaryGroupMapperExt.DictionaryGroupMapperNameSpace + "getDictionaryGroupModel", dictionaryGroupQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param dictionaryGroupQuery 数据字典组查询参数对象
     * @return DictionaryGroupModel 数据字典组领域对象
     */
    @Override
    public DictionaryGroupModel getOneDictionaryGroupModel(DictionaryGroupQuery dictionaryGroupQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(DictionaryGroupMapperExt.DictionaryGroupMapperNameSpace + "getDictionaryGroupModel", dictionaryGroupQuery);
    }
}
