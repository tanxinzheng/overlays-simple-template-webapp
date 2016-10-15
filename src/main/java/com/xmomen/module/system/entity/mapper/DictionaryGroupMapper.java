package com.xmomen.module.system.entity.mapper;

import com.xmomen.framework.mybatis.mapper.MybatisMapper;
import com.xmomen.module.system.entity.DictionaryGroup;
import com.xmomen.module.system.entity.DictionaryGroupExample;
import org.apache.ibatis.annotations.Param;

public interface DictionaryGroupMapper extends MybatisMapper {
    int countByExample(DictionaryGroupExample example);

    int deleteByExample(DictionaryGroupExample example);

    int insertSelective(DictionaryGroup record);

    int updateByExampleSelective(@Param("record") DictionaryGroup record, @Param("example") DictionaryGroupExample example);
}