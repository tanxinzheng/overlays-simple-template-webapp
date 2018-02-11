package com.xmomen.module.core.controller;

import com.google.common.collect.Maps;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.web.rest.RestError;
import com.xmomen.module.core.model.SelectIndex;
import com.xmomen.module.core.model.SelectOptionModel;
import com.xmomen.module.core.model.SelectOptionQuery;
import com.xmomen.module.core.service.SelectService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by tanxinzheng on 17/6/26.
 */
@Slf4j
@RestController
@RequestMapping(value = "/select")
public class CacheController {

    @Autowired
    CacheManager cacheManager;

    Map<SelectIndex, SelectService> selectServiceMap = Maps.newConcurrentMap();

    @Autowired
    public void register(List<SelectService> serviceList){
        for (SelectService selectService : serviceList) {
            selectServiceMap.put(selectService.getSelectIndex(), selectService);
        }
    }

    /**
     * 数据字典列表
     * @param   selectOptionQuery    数据字典查询参数对象
     * @return  List<SelectOptionModel> 数据字典领域分页对象
     */
    @ApiOperation(value = "查询数据字典列表")
    @ActionLog(actionName = "查询数据字典列表")
    @RequestMapping(value = "/cache", method = RequestMethod.GET)
    public List<SelectOptionModel> getDictionaryList(SelectOptionQuery selectOptionQuery){
        String selectIndex = selectOptionQuery.getTypeCode();
        SelectIndex selectIndexKey = null;
        try{
            selectIndexKey = SelectIndex.valueOf(StringUtils.upperCase(selectIndex));
        }catch (IllegalArgumentException e){
            log.error(e.getMessage(), e);
        }
        if(selectIndexKey == null){
            selectIndexKey = SelectIndex.DICTIONARY;
        }
        SelectService defaultSelectService = selectServiceMap.get(selectIndexKey);
        if(defaultSelectService == null){
            return selectServiceMap.get(SelectIndex.DICTIONARY).selectOptionModels(selectOptionQuery);
        }
        return defaultSelectService.selectOptionModels(selectOptionQuery);
    }

    /**
     * 清除缓存
     * @param cacheKey
     * @return
     */
    @ApiOperation(value = "清除缓存")
    @RequestMapping(value = "/cache/clear", method = RequestMethod.GET)
    public RestError clearCache(@RequestParam(value = "cacheKey") String cacheKey){
        cacheManager.getCache(cacheKey).clear();
        return new RestError("缓存清理成功");
    }
}
