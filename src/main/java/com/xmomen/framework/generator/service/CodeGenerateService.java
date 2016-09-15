package com.xmomen.framework.generator.service;

import com.xmomen.framework.generator.mapper.GenerateMapperExt;
import com.xmomen.framework.generator.model.TableDefine;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tanxinzheng on 16/9/14.
 */
@Service
public class CodeGenerateService {

    @Autowired
    MybatisDao mybatisDao;

    public List<TableDefine> getTableDefine(String tableName){
        Map map = new HashMap();
        map.put("name", tableName);
        return mybatisDao.getSqlSessionTemplate().selectList(GenerateMapperExt.GenerateMapperExtNameSpace + "getTableDefineModel", tableName);
    }

    public List<TableDefine> getAllTableDefine(){
        return mybatisDao.getSqlSessionTemplate().selectList(GenerateMapperExt.GenerateMapperExtNameSpace + "getTableDefineModel");
    }
}
