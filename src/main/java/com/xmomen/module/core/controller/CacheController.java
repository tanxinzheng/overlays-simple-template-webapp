package com.xmomen.module.core.controller;

import io.swagger.annotations.ApiOperation;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.module.core.model.SelectOptionModel;
import com.xmomen.module.core.model.SelectOptionQuery;
import com.xmomen.module.core.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by tanxinzheng on 17/6/26.
 */
@RestController
@RequestMapping(value = "/select")
public class CacheController {

    @Autowired
    SelectService dictionarySelectService;

    /**
     * 数据字典列表
     * @param   selectOptionQuery    数据字典查询参数对象
     * @return  Page<DictionaryModel> 数据字典领域分页对象
     */
    @ApiOperation(value = "查询数据字典列表")
    @ActionLog(actionName = "查询数据字典列表")
    @RequestMapping(value = "/cache", method = RequestMethod.GET)
    public List<SelectOptionModel> getDictionaryList(SelectOptionQuery selectOptionQuery){
        return dictionarySelectService.selectOptionModels(selectOptionQuery);
    }
}
