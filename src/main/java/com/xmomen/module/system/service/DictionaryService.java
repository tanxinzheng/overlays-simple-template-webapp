package com.xmomen.module.system.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.system.model.DictionaryQuery;
import com.xmomen.module.system.model.DictionaryModel;
import com.xmomen.module.system.model.Dictionary;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-6-11 1:07:45
 * @version 1.0.0
 */
public interface DictionaryService {

    /**
     * 新增数据字典
     * @param  dictionaryModel   新增数据字典对象参数
     * @return  DictionaryModel    数据字典领域对象
     */
    public DictionaryModel createDictionary(DictionaryModel dictionaryModel);

    /**
     * 新增数据字典实体对象
     * @param   dictionary 新增数据字典实体对象参数
     * @return  Dictionary 数据字典实体对象
     */
    public Dictionary createDictionary(Dictionary dictionary);

    /**
    * 批量新增数据字典
    * @param dictionaryModels     新增数据字典对象集合参数
    * @return List<DictionaryModel>    数据字典领域对象集合
    */
    List<DictionaryModel> createDictionarys(List<DictionaryModel> dictionaryModels);

    /**
    * 更新数据字典
    *
    * @param dictionaryModel 更新数据字典对象参数
    * @param dictionaryQuery 过滤数据字典对象参数
    */
    public void updateDictionary(DictionaryModel dictionaryModel, DictionaryQuery dictionaryQuery);

    /**
     * 更新数据字典
     * @param dictionaryModel    更新数据字典对象参数
     */
    public void updateDictionary(DictionaryModel dictionaryModel);

    /**
     * 更新数据字典实体对象
     * @param   dictionary 新增数据字典实体对象参数
     * @return  Dictionary 数据字典实体对象
     */
    public void updateDictionary(Dictionary dictionary);

    /**
     * 批量删除数据字典
     * @param ids   主键数组
     */
    public void deleteDictionary(String[] ids);

    /**
     * 删除数据字典
     * @param id   主键
     */
    public void deleteDictionary(String id);

    /**
     * 查询数据字典领域分页对象（带参数条件）
     * @param dictionaryQuery 查询参数
     * @return Page<DictionaryModel>   数据字典参数对象
     */
    public Page<DictionaryModel> getDictionaryModelPage(DictionaryQuery dictionaryQuery);

    /**
     * 查询数据字典领域集合对象（带参数条件）
     * @param dictionaryQuery 查询参数对象
     * @return List<DictionaryModel> 数据字典领域集合对象
     */
    public List<DictionaryModel> getDictionaryModelList(DictionaryQuery dictionaryQuery);

    /**
     * 查询数据字典实体对象
     * @param id 主键
     * @return Dictionary 数据字典实体对象
     */
    public Dictionary getOneDictionary(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return DictionaryModel 数据字典领域对象
     */
    public DictionaryModel getOneDictionaryModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param dictionaryQuery 数据字典查询参数对象
     * @return DictionaryModel 数据字典领域对象
     */
    public DictionaryModel getOneDictionaryModel(DictionaryQuery dictionaryQuery) throws TooManyResultsException;
}
