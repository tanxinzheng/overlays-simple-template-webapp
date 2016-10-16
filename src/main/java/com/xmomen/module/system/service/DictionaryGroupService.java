package com.xmomen.module.system.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.system.model.DictionaryGroupCreate;
import com.xmomen.module.system.model.DictionaryGroupQuery;
import com.xmomen.module.system.model.DictionaryGroupUpdate;
import com.xmomen.module.system.model.DictionaryGroupModel;
import com.xmomen.module.system.entity.DictionaryGroup;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-17 0:24:57
 * @version 1.0.0
 */
public interface DictionaryGroupService {

    /**
     * 新增数据字典组
     * @param  dictionaryGroupCreate   新增数据字典组对象参数
     * @return  DictionaryGroupModel    数据字典组领域对象
     */
    public DictionaryGroupModel createDictionaryGroup(DictionaryGroupCreate dictionaryGroupCreate);

    /**
     * 新增数据字典组实体对象
     * @param   dictionaryGroup 新增数据字典组实体对象参数
     * @return  DictionaryGroup 数据字典组实体对象
     */
    public DictionaryGroup createDictionaryGroup(DictionaryGroup dictionaryGroup);

    /**
     * 更新数据字典组
     * @param dictionaryGroupUpdate    更新数据字典组对象参数
     */
    public void updateDictionaryGroup(DictionaryGroupUpdate dictionaryGroupUpdate);

    /**
     * 更新数据字典组实体对象
     * @param   dictionaryGroup 新增数据字典组实体对象参数
     * @return  DictionaryGroup 数据字典组实体对象
     */
    public void updateDictionaryGroup(DictionaryGroup dictionaryGroup);

    /**
     * 批量删除数据字典组
     * @param ids   主键数组
     */
    public void deleteDictionaryGroup(String[] ids);

    /**
     * 删除数据字典组
     * @param id   主键
     */
    public void deleteDictionaryGroup(String id);

    /**
     * 查询数据字典组领域分页对象（带参数条件）
     * @param dictionaryGroupQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<DictionaryGroupModel>   数据字典组参数对象
     */
    public Page<DictionaryGroupModel> getDictionaryGroupModelPage(int limit, int offset, DictionaryGroupQuery dictionaryGroupQuery);

    /**
     * 查询数据字典组领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<DictionaryGroupModel> 数据字典组领域对象
     */
    public Page<DictionaryGroupModel> getDictionaryGroupModelPage(int limit, int offset);

    /**
     * 查询数据字典组领域集合对象（带参数条件）
     * @param dictionaryGroupQuery 查询参数对象
     * @return List<DictionaryGroupModel> 数据字典组领域集合对象
     */
    public List<DictionaryGroupModel> getDictionaryGroupModelList(DictionaryGroupQuery dictionaryGroupQuery);

    /**
     * 查询数据字典组领域集合对象（无参数条件）
     * @return List<DictionaryGroupModel> 数据字典组领域集合对象
     */
    public List<DictionaryGroupModel> getDictionaryGroupModelList();

    /**
     * 查询数据字典组实体对象
     * @param id 主键
     * @return DictionaryGroup 数据字典组实体对象
     */
    public DictionaryGroup getOneDictionaryGroup(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return DictionaryGroupModel 数据字典组领域对象
     */
    public DictionaryGroupModel getOneDictionaryGroupModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param dictionaryGroupQuery 数据字典组查询参数对象
     * @return DictionaryGroupModel 数据字典组领域对象
     */
    public DictionaryGroupModel getOneDictionaryGroupModel(DictionaryGroupQuery dictionaryGroupQuery) throws TooManyResultsException;
}
