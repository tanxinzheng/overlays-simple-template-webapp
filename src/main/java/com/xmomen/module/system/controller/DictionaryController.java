package com.xmomen.module.system.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.module.system.model.DictionaryQuery;
import com.xmomen.module.system.model.DictionaryModel;
import com.xmomen.module.system.service.DictionaryService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-6-11 18:57:11
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/dictionary")
public class DictionaryController extends BaseRestController {

    public static final String PERMISSION_DICTIONARY_CREATE = "dictionary:create";
    public static final String PERMISSION_DICTIONARY_DELETE = "dictionary:delete";
    public static final String PERMISSION_DICTIONARY_UPDATE = "dictionary:update";
    public static final String PERMISSION_DICTIONARY_VIEW   = "dictionary:view";

    @Autowired
    DictionaryService dictionaryService;

    /**
     * 数据字典列表
     * @param   dictionaryQuery    数据字典查询参数对象
     * @return  Page<DictionaryModel> 数据字典领域分页对象
     */
    @ApiOperation(value = "查询数据字典列表")
    @ActionLog(actionName = "查询数据字典列表")
    //@RequiresPermissions(value = {PERMISSION_DICTIONARY_VIEW})
    @RequestMapping(method = RequestMethod.GET)
    public Page<DictionaryModel> getDictionaryList(DictionaryQuery dictionaryQuery){
        if(dictionaryQuery.isPaging()){
            return dictionaryService.getDictionaryModelPage(dictionaryQuery);
        }
        List<DictionaryModel> dictionaryList = dictionaryService.getDictionaryModelList(dictionaryQuery);
        return new Page(dictionaryList);
    }

    /**
     * 查询单个数据字典
     * @param   id  主键
     * @return  DictionaryModel   数据字典领域对象
     */
    @ApiOperation(value = "查询数据字典")
    @ActionLog(actionName = "查询数据字典")
    //@RequiresPermissions(value = {PERMISSION_DICTIONARY_VIEW})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DictionaryModel getDictionaryById(@PathVariable(value = "id") String id){
        return dictionaryService.getOneDictionaryModel(id);
    }

    /**
     * 新增数据字典
     * @param   dictionaryModel  新增对象参数
     * @return  DictionaryModel   数据字典领域对象
     */
    @ApiOperation(value = "新增数据字典")
    @ActionLog(actionName = "新增数据字典")
    //@RequiresPermissions(value = {PERMISSION_DICTIONARY_CREATE})
    @RequestMapping(method = RequestMethod.POST)
    public DictionaryModel createDictionary(@RequestBody @Valid DictionaryModel dictionaryModel) {
        dictionaryModel.setCreatedUserId(getCurrentUserId());
        dictionaryModel.setUpdatedUserId(getCurrentUserId());
        return dictionaryService.createDictionary(dictionaryModel);
    }

    /**
     * 更新数据字典
     * @param id    主键
     * @param dictionaryModel  更新对象参数
     * @return  DictionaryModel   数据字典领域对象
     */
    @ApiOperation(value = "更新数据字典")
    @ActionLog(actionName = "更新数据字典")
    //@RequiresPermissions(value = {PERMISSION_DICTIONARY_UPDATE})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public DictionaryModel updateDictionary(@PathVariable(value = "id") String id,
                           @RequestBody @Valid DictionaryModel dictionaryModel){
        if(StringUtils.isNotBlank(id)){
            dictionaryModel.setId(id);
        }
        dictionaryModel.setCreatedUserId(getCurrentUserId());
        dictionaryModel.setUpdatedUserId(getCurrentUserId());
        dictionaryService.updateDictionary(dictionaryModel);
        return dictionaryService.getOneDictionaryModel(id);
    }

    /**
     *  删除数据字典
     * @param id    主键
     */
    @ApiOperation(value = "删除单个数据字典")
    @ActionLog(actionName = "删除单个数据字典")
    //@RequiresPermissions(value = {PERMISSION_DICTIONARY_DELETE})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteDictionary(@PathVariable(value = "id") String id){
        dictionaryService.deleteDictionary(id);
    }

    /**
     *  删除数据字典
     * @param dictionaryQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除数据字典")
    @ActionLog(actionName = "批量删除数据字典")
    //@RequiresPermissions(value = {PERMISSION_DICTIONARY_DELETE})
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteDictionarys(DictionaryQuery dictionaryQuery){
        dictionaryService.deleteDictionary(dictionaryQuery.getIds());
    }

    /**
     * 导出
     * @param dictionaryQuery    查询参数对象
     * @return ModelAndView JEECG_EXCEL_VIEW Excel报表视图
     */
    @RequestMapping(value="/export", method = RequestMethod.GET)
    public ModelAndView exportDictionarys(DictionaryQuery dictionaryQuery,
            ModelMap modelMap) {
        List<DictionaryModel> dictionaryModelList = dictionaryService.getDictionaryModelList(dictionaryQuery);
        modelMap.put(NormalExcelConstants.FILE_NAME, "数据字典");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, DictionaryModel.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, dictionaryModelList);
        return new ModelAndView(NormalExcelConstants.JEECG_EXCEL_VIEW);
    }


}
