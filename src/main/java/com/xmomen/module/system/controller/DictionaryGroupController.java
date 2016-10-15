package com.xmomen.module.system.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
//import com.xmomen.module.logger.Log;
import com.xmomen.module.system.model.DictionaryGroupCreate;
import com.xmomen.module.system.model.DictionaryGroupQuery;
import com.xmomen.module.system.model.DictionaryGroupUpdate;
import com.xmomen.module.system.model.DictionaryGroupModel;
import com.xmomen.module.system.service.DictionaryGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-15 14:41:37
 * @version 1.0.0
 */
@RestController
public class DictionaryGroupController {

    @Autowired
    DictionaryGroupService dictionaryGroupService;

    /**
     * 数据字典组列表
     * @param   limit           每页结果数
     * @param   offset          页码
     * @param   id              主键
     * @param   ids             主键数组
     * @param   excludeIds      不包含主键数组
     * @param   keyword         关键字
     * @return  Page<DictionaryGroupModel> 数据字典组领域分页对象
     */
    @RequestMapping(value = "/dictionaryGroup", method = RequestMethod.GET)
    //@Log(actionName = "查询数据字典组列表")
    public Page<DictionaryGroupModel> getDictionaryGroupList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
                                  @RequestParam(value = "keyword", required = false) String keyword){
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
    @RequestMapping(value = "/dictionaryGroup/{id}", method = RequestMethod.GET)
    //@Log(actionName = "查询数据字典组")
    public DictionaryGroupModel getDictionaryGroupById(@PathVariable(value = "id") String id){
        return dictionaryGroupService.getOneDictionaryGroupModel(id);
    }

    /**
     * 新增数据字典组
     * @param   DictionaryGroupCreate  新增对象参数
     * @param   bindingResult   参数校验结果
     * @return  DictionaryGroupModel   数据字典组领域对象
     */
    @RequestMapping(value = "/dictionaryGroup", method = RequestMethod.POST)
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
     * @param DictionaryGroupUpdate 更新对象参数
     * @param bindingResult                 参数校验结果
     * @throws ArgumentValidException       参数校验异常类
     */
    @RequestMapping(value = "/dictionaryGroup/{id}", method = RequestMethod.PUT)
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
    @RequestMapping(value = "/dictionaryGroup/{id}", method = RequestMethod.DELETE)
    //@Log(actionName = "删除单个数据字典组")
    public void deleteDictionaryGroup(@PathVariable(value = "id") String id){
        dictionaryGroupService.deleteDictionaryGroup(id);
    }

    /**
     *  删除数据字典组
     * @param ids    主键
     */
    @RequestMapping(value = "/dictionaryGroup", method = RequestMethod.DELETE)
    //@Log(actionName = "批量删除数据字典组")
    public void deleteDictionaryGroups(@RequestParam(value = "ids") String[] ids){
        dictionaryGroupService.deleteDictionaryGroup(ids);
    }

}
