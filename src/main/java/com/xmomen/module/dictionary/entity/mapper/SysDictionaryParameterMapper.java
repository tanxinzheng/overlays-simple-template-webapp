package com.xmomen.module.dictionary.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.dictionary.entity.SysDictionaryParameter;
import com.xmomen.module.dictionary.entity.SysDictionaryParameterExample;
import org.apache.ibatis.annotations.Param;

public interface SysDictionaryParameterMapper extends MybatisMapper {
    int countByExample(SysDictionaryParameterExample example);

    int deleteByExample(SysDictionaryParameterExample example);

    int insertSelective(SysDictionaryParameter record);

    int updateByExampleSelective(@Param("record") SysDictionaryParameter record, @Param("example") SysDictionaryParameterExample example);
}