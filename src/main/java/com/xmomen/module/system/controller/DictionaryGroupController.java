package com.xmomen.module.system.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.system.model.DictionaryGroupCreate;
import com.xmomen.module.system.model.DictionaryGroupModel;
import com.xmomen.module.system.model.DictionaryGroupQuery;
import com.xmomen.module.system.model.DictionaryGroupUpdate;
import com.xmomen.module.system.service.DictionaryGroupService;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

//import com.xmomen.module.logger.Log;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:19
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/dictionary/group")
public class DictionaryGroupController {

    @Autowired
    DictionaryGroupService dictionaryGroupService;

    /**
     * 数据字典组列表
     * @param   limit           每页结果数
     * @param   offset          页码
     * @param   keyword         关键字
     * @param   id              主键
     * @param   ids             主键数组
     * @param   excludeIds      不包含主键数组
     * @return  Page<DictionaryGroupModel> 数据字典组领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    //@Log(actionName = "查询数据字典组列表")
    public Page<DictionaryGroupModel> getDictionaryGroupList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds){
        DictionaryGroupQuery dictionaryGroupQuery = new DictionaryGroupQuery();
        dictionaryGroupQuery.setId(id);
        dictionaryGroupQuery.setExcludeIds(excludeIds);
        dictionaryGroupQuery.setIds(ids);
        dictionaryGroupQuery.setKeyword(keyword);
        return dictionaryGroupService.getDictionaryGroupModelPage(limit, offset, dictionaryGroupQuery);
    }

    /**
     * 查询单个数据字典组
     * @param   id  主键
     * @return  DictionaryGroupModel   数据字典组领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    //@Log(actionName = "查询数据字典组")
    public DictionaryGroupModel getDictionaryGroupById(@PathVariable(value = "id") String id){
        return dictionaryGroupService.getOneDictionaryGroupModel(id);
    }

    /**
     * 新增数据字典组
     * @param   dictionaryGroupCreate  新增对象参数
     * @param   bindingResult   参数校验结果
     * @return  DictionaryGroupModel   数据字典组领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    //@Log(actionName = "新增数据字典组")
    public DictionaryGroupModel createDictionaryGroup(@RequestBody @Valid DictionaryGroupCreate dictionaryGroupCreate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return dictionaryGroupService.createDictionaryGroup(dictionaryGroupCreate);
    }

    /**
     * 更新数据字典组
     * @param id                            主键
     * @param dictionaryGroupUpdate 更新对象参数
     * @param bindingResult                 参数校验结果
     * @throws ArgumentValidException       参数校验异常类
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    //@Log(actionName = "更新数据字典组")
    public void updateDictionaryGroup(@PathVariable(value = "id") String id,
                           @RequestBody @Valid DictionaryGroupUpdate dictionaryGroupUpdate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        dictionaryGroupService.updateDictionaryGroup(dictionaryGroupUpdate);
    }

    /**
     *  删除数据字典组
     * @param id    主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    //@Log(actionName = "删除单个数据字典组")
    public void deleteDictionaryGroup(@PathVariable(value = "id") String id){
        dictionaryGroupService.deleteDictionaryGroup(id);
    }

    /**
     *  删除数据字典组
     * @param ids    主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    //@Log(actionName = "批量删除数据字典组")
    public void deleteDictionaryGroups(@RequestParam(value = "ids") String[] ids){
        dictionaryGroupService.deleteDictionaryGroup(ids);
    }

    /**
    * 导出
    * @param id     主键
    * @param ids    包含的主键数组
    * @param excludeIds     排除的主键数组
    * @param keyword    关键字
    * @param modelMap   modelMap对象
    * @return ModelAndView JEECG_EXCEL_VIEW Excel报表视图
    */
    @RequestMapping(value="/export", method = RequestMethod.GET)
    public ModelAndView exportDictionaryGroup(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "ids", required = false) String[] ids,
            @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
            @RequestParam(value = "keyword", required = false) String keyword,
            ModelMap modelMap) {
        DictionaryGroupQuery dictionaryGroupQuery = new DictionaryGroupQuery();
        dictionaryGroupQuery.setId(id);
        dictionaryGroupQuery.setExcludeIds(excludeIds);
        dictionaryGroupQuery.setIds(ids);
        dictionaryGroupQuery.setKeyword(keyword);
        List<DictionaryGroupModel> dictionaryGroupList = dictionaryGroupService.getDictionaryGroupModelList(dictionaryGroupQuery);
        modelMap.put(NormalExcelConstants.FILE_NAME, "数据字典组信息");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, DictionaryGroupModel.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, dictionaryGroupList);
        return new ModelAndView(NormalExcelConstants.JEECG_EXCEL_VIEW);
    }


}
