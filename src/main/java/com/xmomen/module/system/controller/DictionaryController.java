package com.xmomen.module.system.controller;

import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.poi.ExcelUtils;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.framework.web.rest.ImportExcelResponse;
import com.xmomen.module.system.model.DictionaryModel;
import com.xmomen.module.system.model.DictionaryQuery;
import com.xmomen.module.system.service.DictionaryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-6-11 18:57:11
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/dictionary")
@Slf4j
public class DictionaryController extends BaseRestController {

    @Autowired
    DictionaryService dictionaryService;

    /**
     * 数据字典列表
     * @param   dictionaryQuery    数据字典查询参数对象
     * @return  Page<DictionaryModel> 数据字典领域分页对象
     */
    @ApiOperation(value = "查询数据字典列表")
//    @ActionLog(actionName = "查询数据字典列表")
    @PreAuthorize("hasAuthority('DICTIONARY:VIEW')")
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
    @PreAuthorize("hasAuthority('DICTIONARY:VIEW')")
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
    @PreAuthorize("hasAuthority('DICTIONARY:CREATE')")
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
    @ActionLog(actionName = "更新数据字典，字典编号：${DictionaryModel.dictionaryCode}")
    @PreAuthorize("hasAuthority('DICTIONARY:UPDATE')")
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
    @PreAuthorize("hasAuthority('DICTIONARY:DELETE')")
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
    @PreAuthorize("hasAuthority('DICTIONARY:DELETE')")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteDictionaries(DictionaryQuery dictionaryQuery){
        dictionaryService.deleteDictionary(dictionaryQuery.getIds());
    }

    /**
     * 下载Excel模板
     */
    @ActionLog(actionName = "下载数据字典导入模板")
    @RequestMapping(value="/template", method = RequestMethod.GET)
    public void downloadTemplate(HttpServletRequest request,
                                 HttpServletResponse response) {
        List<DictionaryModel> dictionaryModelList = Lists.newArrayList();
        ExcelUtils.export(request, response, DictionaryModel.class, dictionaryModelList, "数据字典_模板");
    }

    /**
     * 导出Excel
     * @param dictionaryQuery    查询参数对象
     */
    @ActionLog(actionName = "导出数据字典")
    @PreAuthorize("hasAuthority('DICTIONARY:VIEW')")
    @RequestMapping(value="/export", method = RequestMethod.GET)
    public void exportDictionaries(DictionaryQuery dictionaryQuery,
                                             HttpServletRequest request,
                                             HttpServletResponse response) {
        List<DictionaryModel> dictionaryModelList = dictionaryService.getDictionaryModelList(dictionaryQuery);
        ExcelUtils.export(request, response, DictionaryModel.class, dictionaryModelList, "数据字典");
    }

    /**
     * 导入Excel
     * @param file
     */
    @ActionLog(actionName = "导入数据字典")
    @PreAuthorize("hasAuthority('DICTIONARY:CREATE')")
    @RequestMapping(value="/import", method = RequestMethod.POST)
    public ImportExcelResponse importDictionaries(@RequestParam("file") MultipartFile file) {
        List<DictionaryModel> list = ExcelUtils.transform(file, DictionaryModel.class);
        if(CollectionUtils.isEmpty(list)){
            return ImportExcelResponse.fail();
        }
        dictionaryService.createDictionaries(list);
        return ImportExcelResponse.success(list.size());
    }

}
