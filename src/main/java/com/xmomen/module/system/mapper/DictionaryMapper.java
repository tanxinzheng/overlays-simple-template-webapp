package com.xmomen.module.system.mapper;

import com.xmomen.module.system.model.Dictionary;
import com.xmomen.module.system.model.DictionaryModel;
import com.xmomen.module.system.model.DictionaryQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-6-11 1:07:45
 * @version 1.0.0
 */
public interface DictionaryMapper {

    List<Dictionary> select(DictionaryQuery dictionaryQuery);

    List<DictionaryModel> selectModel(DictionaryQuery dictionaryQuery);

    Dictionary selectByPrimaryKey(String primaryKey);

    DictionaryModel selectModelByPrimaryKey(String primaryKey);

    int deleteByPrimaryKey(String primaryKey);

    int deletesByPrimaryKey(@Param("ids") List<String> primaryKeys);

    int insertSelective(Dictionary record);

    int updateSelective(Dictionary record);

    int updateSelectiveByQuery(@Param("record") Dictionary record, @Param("query") DictionaryQuery example);
}
