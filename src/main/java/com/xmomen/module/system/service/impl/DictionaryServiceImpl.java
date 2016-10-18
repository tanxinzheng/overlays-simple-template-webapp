package com.xmomen.module.system.service.impl;

import com.xmomen.module.system.entity.Dictionary;
import com.xmomen.module.system.entity.DictionaryExample;
import com.xmomen.module.system.mapper.DictionaryMapperExt;
import com.xmomen.module.system.model.DictionaryCreate;
import com.xmomen.module.system.model.DictionaryQuery;
import com.xmomen.module.system.model.DictionaryUpdate;
import com.xmomen.module.system.model.DictionaryModel;
import com.xmomen.module.system.service.DictionaryService;
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
 * @date    2016-10-18 23:09:38
 * @version 1.0.0
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增数据字典
     *
     * @param dictionaryCreate 新增数据字典对象参数
     * @return DictionaryModel    数据字典领域对象
     */
    @Override
    @Transactional
    public DictionaryModel createDictionary(DictionaryCreate dictionaryCreate) {
        Dictionary dictionary = createDictionary(dictionaryCreate.getEntity());
        if(dictionary != null){
            return getOneDictionaryModel(dictionary.getId());
        }
        return null;
    }

    /**
     * 新增数据字典实体对象
     *
     * @param dictionary 新增数据字典实体对象参数
     * @return Dictionary 数据字典实体对象
     */
    @Override
    @Transactional
    public Dictionary createDictionary(Dictionary dictionary) {
        return mybatisDao.insertByModel(dictionary);
    }

    /**
     * 更新数据字典
     *
     * @param dictionaryUpdate 更新数据字典对象参数
     */
    @Override
    @Transactional
    public void updateDictionary(DictionaryUpdate dictionaryUpdate) {
        mybatisDao.update(dictionaryUpdate.getEntity());
    }

    /**
     * 更新数据字典实体对象
     *
     * @param dictionary 新增数据字典实体对象参数
     * @return Dictionary 数据字典实体对象
     */
    @Override
    @Transactional
    public void updateDictionary(Dictionary dictionary) {
        mybatisDao.update(dictionary);
    }

    /**
     * 删除数据字典
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteDictionary(String[] ids) {
        DictionaryExample dictionaryExample = new DictionaryExample();
        dictionaryExample.createCriteria().andIdIn(Arrays.asList((String[]) ids));
        mybatisDao.deleteByExample(dictionaryExample);
    }

    /**
    * 删除数据字典
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteDictionary(String id) {
        mybatisDao.deleteByPrimaryKey(Dictionary.class, id);
    }

    /**
     * 查询数据字典领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param dictionaryQuery 查询参数
     * @return Page<DictionaryModel>   数据字典参数对象
     */
    @Override
    public Page<DictionaryModel> getDictionaryModelPage(int limit, int offset, DictionaryQuery dictionaryQuery) {
        return (Page<DictionaryModel>) mybatisDao.selectPage(DictionaryMapperExt.DictionaryMapperNameSpace + "getDictionaryModel", dictionaryQuery, limit, offset);
    }

    /**
     * 查询数据字典领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<DictionaryModel> 数据字典领域对象
     */
    @Override
    public Page<DictionaryModel> getDictionaryModelPage(int limit, int offset) {
        return (Page<DictionaryModel>) mybatisDao.selectPage(DictionaryMapperExt.DictionaryMapperNameSpace + "getDictionaryModel", null, limit, offset);
    }

    /**
     * 查询数据字典领域集合对象（带参数条件）
     *
     * @param dictionaryQuery 查询参数对象
     * @return List<DictionaryModel> 数据字典领域集合对象
     */
    @Override
    public List<DictionaryModel> getDictionaryModelList(DictionaryQuery dictionaryQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(DictionaryMapperExt.DictionaryMapperNameSpace + "getDictionaryModel", dictionaryQuery);
    }

    /**
     * 查询数据字典领域集合对象（无参数条件）
     *
     * @return List<DictionaryModel> 数据字典领域集合对象
     */
    @Override
    public List<DictionaryModel> getDictionaryModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(DictionaryMapperExt.DictionaryMapperNameSpace + "getDictionaryModel");
    }

    /**
     * 查询数据字典实体对象
     *
     * @param id 主键
     * @return Dictionary 数据字典实体对象
     */
    @Override
    public Dictionary getOneDictionary(String id) {
        return mybatisDao.selectByPrimaryKey(Dictionary.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return DictionaryModel 数据字典领域对象
     */
    @Override
    public DictionaryModel getOneDictionaryModel(String id) {
        DictionaryQuery dictionaryQuery = new DictionaryQuery();
        dictionaryQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(DictionaryMapperExt.DictionaryMapperNameSpace + "getDictionaryModel", dictionaryQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param dictionaryQuery 数据字典查询参数对象
     * @return DictionaryModel 数据字典领域对象
     */
    @Override
    public DictionaryModel getOneDictionaryModel(DictionaryQuery dictionaryQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(DictionaryMapperExt.DictionaryMapperNameSpace + "getDictionaryModel", dictionaryQuery);
    }
}
