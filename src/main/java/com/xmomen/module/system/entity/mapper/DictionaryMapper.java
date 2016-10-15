package com.xmomen.module.system.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.system.entity.Dictionary;
import com.xmomen.module.system.entity.DictionaryExample;
import org.apache.ibatis.annotations.Param;

public interface DictionaryMapper extends MybatisMapper {
    int countByExample(DictionaryExample example);

    int deleteByExample(DictionaryExample example);

    int insertSelective(Dictionary record);

    int updateByExampleSelective(@Param("record") Dictionary record, @Param("example") DictionaryExample example);
}