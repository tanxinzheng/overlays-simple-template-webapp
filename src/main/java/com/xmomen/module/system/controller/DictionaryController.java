package com.xmomen.module.system.controller;

import com.google.common.collect.Lists;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xmomen.framework.exception.BusinessException;
import com.github.pagehelper.Page;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.poi.ExcelUtils;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.module.system.model.DictionaryQuery;
import com.xmomen.module.system.model.DictionaryModel;
import com.xmomen.module.system.service.DictionaryService;

import org.apache.shiro.util.CollectionUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
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
        return dictionaryService.getDictionaryModelPage(dictionaryQuery);
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
    public void deleteDictionaries(DictionaryQuery dictionaryQuery){
        dictionaryService.deleteDictionary(dictionaryQuery.getIds());
    }

    /**
     * 下载Excel模板
     * @return ModelAndView JEECG_EXCEL_VIEW Excel报表视图
     */
    @ActionLog(actionName = "下载数据字典导入模板")
    @RequestMapping(value="/template", method = RequestMethod.GET)
    public ModelAndView downloadTemplate(ModelMap modelMap) {
        List<DictionaryModel> dictionaryModelList = Lists.newArrayList();
        return ExcelUtils.export(modelMap, DictionaryModel.class, dictionaryModelList, "数据字典_模板");
    }

    /**
     * 导出Excel
     * @param dictionaryQuery    查询参数对象
     * @return ModelAndView JEECG_EXCEL_VIEW Excel报表视图
     */
    @ActionLog(actionName = "导出数据字典")
    @RequestMapping(value="/export", method = RequestMethod.GET)
    public ModelAndView exportDictionaries(DictionaryQuery dictionaryQuery,
                                           ModelMap modelMap) {
        List<DictionaryModel> dictionaryModelList = dictionaryService.getDictionaryModelList(dictionaryQuery);
        return ExcelUtils.export(modelMap, DictionaryModel.class, dictionaryModelList, "数据字典");
    }

    /**
     * 导入Excel
     * @param file
     */
    @ActionLog(actionName = "导入数据字典")
    @RequestMapping(value="/import", method = RequestMethod.POST)
    public void importDictionaries(@RequestParam("file") MultipartFile file) throws IOException {
        List<DictionaryModel> list = ExcelUtils.transform(file, DictionaryModel.class);
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        dictionaryService.createDictionaries(list);
    }

}
