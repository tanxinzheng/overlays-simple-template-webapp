package com.xmomen.module.system.service.impl;

import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.page.PageInterceptor;
import com.xmomen.framework.web.json.DictionaryIndex;
import com.xmomen.module.core.model.SelectIndex;
import com.xmomen.module.core.model.SelectOptionModel;
import com.xmomen.module.core.model.SelectOptionQuery;
import com.xmomen.module.core.service.SelectService;
import com.xmomen.module.system.mapper.DictionaryMapper;
import com.xmomen.module.system.model.Dictionary;
import com.xmomen.module.system.model.DictionaryModel;
import com.xmomen.module.system.model.DictionaryQuery;
import com.xmomen.module.system.service.DictionaryService;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author  tanxinzheng
 * @date    2017-6-11 1:07:45
 * @version 1.0.0
 */
@Service
public class DictionaryServiceImpl implements DictionaryService, SelectService {

    @Autowired
    DictionaryMapper dictionaryMapper;

    /**
     * 新增数据字典
     *
     * @param dictionaryModel 新增数据字典对象参数
     * @return DictionaryModel    数据字典领域对象
     */
    @Override
    @Transactional
    public DictionaryModel createDictionary(DictionaryModel dictionaryModel) {
        Dictionary dictionary = createDictionary(dictionaryModel.getEntity());
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
        dictionary.setCreatedTime(new Date());
        dictionary.setUpdatedTime(new Date());
        dictionary.setDataVersion(1);
        dictionaryMapper.insertSelective(dictionary);
        return dictionary;
    }

    /**
    * 批量新增数据字典
    *
    * @param dictionaryModels 新增数据字典对象集合参数
    * @return List<DictionaryModel>    数据字典领域对象集合
    */
    @Override
    @Transactional
    public List<DictionaryModel> createDictionaries(List<DictionaryModel> dictionaryModels) {
        List<DictionaryModel> dictionaryModelList = Lists.newArrayList();
        for (DictionaryModel dictionaryModel : dictionaryModels) {
            dictionaryModel.setUpdatedUserId("SYSTEM");
            dictionaryModel.setCreatedUserId("SYSTEM");
            dictionaryModel.setId(UUID.randomUUID().toString().replaceAll("-",""));
            dictionaryModelList.add(dictionaryModel);
        }
        dictionaryMapper.insertByBatch(dictionaryModels);
        return dictionaryModelList;
    }

    /**
    * 更新数据字典
    *
    * @param dictionaryModel 更新数据字典对象参数
    * @param dictionaryQuery 过滤数据字典对象参数
    */
    @Override
    @Transactional
    @CacheEvict(cacheNames = "dictionariesCache", key = "#dictionaryModel.groupCode")
    public void updateDictionary(DictionaryModel dictionaryModel, DictionaryQuery dictionaryQuery) {
        dictionaryMapper.updateSelectiveByQuery(dictionaryModel.getEntity(), dictionaryQuery);
    }

    /**
     * 更新数据字典
     *
     * @param dictionaryModel 更新数据字典对象参数
     */
    @Override
    @Transactional
    @CacheEvict(cacheNames = "dictionariesCache", key = "#dictionaryModel.groupCode")
    public void updateDictionary(DictionaryModel dictionaryModel) {
        updateDictionary(dictionaryModel.getEntity());
    }

    /**
     * 更新数据字典实体对象
     *
     * @param dictionary 新增数据字典实体对象参数
     * @return Dictionary 数据字典实体对象
     */
    @Override
    @Transactional
    @CacheEvict(cacheNames = "dictionariesCache", key = "#dictionary.groupCode")
    public void updateDictionary(Dictionary dictionary) {
        dictionaryMapper.updateSelective(dictionary);
    }

    /**
     * 删除数据字典
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    @CacheEvict(cacheNames = "dictionariesCache", allEntries = true)
    public void deleteDictionary(String[] ids) {
        if(ArrayUtils.isEmpty(ids)){
            return;
        }
        dictionaryMapper.deletesByPrimaryKey(Arrays.asList(ids));
    }

    /**
    * 删除数据字典
    *
    * @param id 主键
    */
    @Override
    @Transactional
    @CacheEvict(cacheNames = "dictionariesCache", allEntries = true)
    public void deleteDictionary(String id) {
        if(StringUtils.isBlank(id)){
            return;
        }
        dictionaryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询数据字典领域分页对象（带参数条件）
     *
     * @param dictionaryQuery 查询参数
     * @return Page<DictionaryModel>   数据字典参数对象
     */
    @Override
    public Page<DictionaryModel> getDictionaryModelPage(DictionaryQuery dictionaryQuery) {
        PageInterceptor.startPage(dictionaryQuery);
        dictionaryMapper.selectModel(dictionaryQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询数据字典领域集合对象（带参数条件）
     *
     * @param dictionaryQuery 查询参数对象
     * @return List<DictionaryModel> 数据字典领域集合对象
     */
    @Override
//    @Cacheable(cacheNames = "dictionariesCache")
    public List<DictionaryModel> getDictionaryModelList(DictionaryQuery dictionaryQuery) {
        return dictionaryMapper.selectModel(dictionaryQuery);
    }

    /**
     * 查询数据字典实体对象
     *
     * @param id 主键
     * @return Dictionary 数据字典实体对象
     */
    @Override
    public Dictionary getOneDictionary(String id) {
        if(StringUtils.isBlank(id)){
            return null;
        }
        return dictionaryMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return DictionaryModel 数据字典领域对象
     */
    @Override
    public DictionaryModel getOneDictionaryModel(String id) {
        if(StringUtils.isBlank(id)){
            return null;
        }
        return dictionaryMapper.selectModelByPrimaryKey(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param dictionaryQuery 数据字典查询参数对象
     * @return DictionaryModel 数据字典领域对象
     */
    @Override
    @Cacheable(cacheNames = DictionaryIndex.DICTIONARY_CACHE_NAME_KEY)
    public DictionaryModel getOneDictionaryModel(DictionaryQuery dictionaryQuery) {
        List<DictionaryModel> dictionaryModelList = dictionaryMapper.selectModel(dictionaryQuery);
        if(CollectionUtils.isEmpty(dictionaryModelList)){
            return null;
        }
        if(dictionaryModelList.size() > 1){
            throw new BusinessException();
        }
        return dictionaryModelList.get(0);
    }

    @Cacheable(cacheNames = DictionaryIndex.DICTIONARY_CACHE_NAME_KEY)
    @Override
    public List<SelectOptionModel> selectOptionModels(SelectOptionQuery selectOptionQuery) {
        Assert.notNull(selectOptionQuery);
        Assert.notNull(selectOptionQuery.getTypeCode(), "typeCode不能为空");
        List<SelectOptionModel> selectOptionModelList = new ArrayList<>();
        DictionaryQuery dictionaryQuery = new DictionaryQuery();
        dictionaryQuery.setType(selectOptionQuery.getTypeCode());
        dictionaryQuery.setParentId(selectOptionQuery.getParentId());
        List<DictionaryModel> dictionaryModels = getDictionaryModelList(dictionaryQuery);
        if(CollectionUtils.isEmpty(dictionaryModels)){
            return Lists.newArrayList();
        }
        for (DictionaryModel dictionaryModel : dictionaryModels) {
            SelectOptionModel selectOptionModel = new SelectOptionModel(
                    dictionaryModel.getGroupCode(),
                    dictionaryModel.getGroupName(),
                    dictionaryModel.getDictionaryCode(),
                    dictionaryModel.getDictionaryName(),
                    dictionaryModel.getSort()
            );
            selectOptionModelList.add(selectOptionModel);
        }
        return selectOptionModelList;
    }

    /**
     * 获取select index
     *
     * @return
     */
    @Override
    public SelectIndex getSelectIndex() {
        return SelectIndex.DICTIONARY;
    }
}
