package com.xmomen.module.system.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.system.model.DictionaryCreate;
import com.xmomen.module.system.model.DictionaryQuery;
import com.xmomen.module.system.model.DictionaryUpdate;
import com.xmomen.module.system.model.DictionaryModel;
import com.xmomen.module.system.entity.Dictionary;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-20 23:14:12
 * @version 1.0.0
 */
public interface DictionaryService {

    /**
     * 新增数据字典
     * @param  dictionaryCreate   新增数据字典对象参数
     * @return  DictionaryModel    数据字典领域对象
     */
    DictionaryModel createDictionary(DictionaryCreate dictionaryCreate);

    /**
     * 新增数据字典实体对象
     * @param   dictionary 新增数据字典实体对象参数
     * @return  Dictionary 数据字典实体对象
     */
    Dictionary createDictionary(Dictionary dictionary);

    /**
     * 更新数据字典
     * @param dictionaryUpdate    更新数据字典对象参数
     */
    void updateDictionary(DictionaryUpdate dictionaryUpdate);

    /**
     * 更新数据字典实体对象
     * @param   dictionary 新增数据字典实体对象参数
     * @return  Dictionary 数据字典实体对象
     */
    void updateDictionary(Dictionary dictionary);

    /**
     * 批量删除数据字典
     * @param ids   主键数组
     */
    void deleteDictionary(String[] ids);

    /**
     * 删除数据字典
     * @param id   主键
     */
    void deleteDictionary(String id);

    /**
     * 查询数据字典领域分页对象（带参数条件）
     * @param dictionaryQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<DictionaryModel>   数据字典参数对象
     */
    Page<DictionaryModel> getDictionaryModelPage(int limit, int offset, DictionaryQuery dictionaryQuery);

    /**
     * 查询数据字典领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<DictionaryModel> 数据字典领域对象
     */
    Page<DictionaryModel> getDictionaryModelPage(int limit, int offset);

    /**
     * 查询数据字典领域集合对象（带参数条件）
     * @param dictionaryQuery 查询参数对象
     * @return List<DictionaryModel> 数据字典领域集合对象
     */
    List<DictionaryModel> getDictionaryModelList(DictionaryQuery dictionaryQuery);

    /**
     * 查询数据字典领域集合对象（无参数条件）
     * @return List<DictionaryModel> 数据字典领域集合对象
     */
    List<DictionaryModel> getDictionaryModelList();

    /**
     * 查询数据字典实体对象
     * @param id 主键
     * @return Dictionary 数据字典实体对象
     */
    Dictionary getOneDictionary(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return DictionaryModel 数据字典领域对象
     */
    DictionaryModel getOneDictionaryModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param dictionaryQuery 数据字典查询参数对象
     * @return DictionaryModel 数据字典领域对象
     */
    DictionaryModel getOneDictionaryModel(DictionaryQuery dictionaryQuery) throws TooManyResultsException;
}
