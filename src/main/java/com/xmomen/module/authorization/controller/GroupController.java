package com.xmomen.module.authorization.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
//import com.xmomen.module.logger.Log;
import com.xmomen.module.authorization.model.GroupCreate;
import com.xmomen.module.authorization.model.GroupQuery;
import com.xmomen.module.authorization.model.GroupUpdate;
import com.xmomen.module.authorization.model.GroupModel;
import com.xmomen.module.authorization.service.GroupService;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-17 0:59:11
 * @version 1.0.0
 */
@RestController
public class GroupController {

    @Autowired
    GroupService groupService;

    /**
     * 组列表
     * @param   limit           每页结果数
     * @param   offset          页码
     * @param   id              主键
     * @param   ids             主键数组
     * @param   excludeIds      不包含主键数组
     * @param   keyword         关键字
     * @return  Page<GroupModel> 组领域分页对象
     */
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    //@Log(actionName = "查询组列表")
    public Page<GroupModel> getGroupList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
                                  @RequestParam(value = "keyword", required = false) String keyword){
        GroupQuery groupQuery = new GroupQuery();
        groupQuery.setId(id);
        groupQuery.setExcludeIds(excludeIds);
        groupQuery.setIds(ids);
        groupQuery.setKeyword(keyword);
        return groupService.getGroupModelPage(limit, offset, groupQuery);
    }

    /**
     * 查询单个组
     * @param   id  主键
     * @return  GroupModel   组领域对象
     */
    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    //@Log(actionName = "查询组")
    public GroupModel getGroupById(@PathVariable(value = "id") String id){
        return groupService.getOneGroupModel(id);
    }

    /**
     * 新增组
     * @param   GroupCreate  新增对象参数
     * @param   bindingResult   参数校验结果
     * @return  GroupModel   组领域对象
     */
    @RequestMapping(value = "/group", method = RequestMethod.POST)
    //@Log(actionName = "新增组")
    public GroupModel createGroup(@RequestBody @Valid GroupCreate groupCreate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return groupService.createGroup(groupCreate);
    }

    /**
     * 更新组
     * @param id                            主键
     * @param GroupUpdate 更新对象参数
     * @param bindingResult                 参数校验结果
     * @throws ArgumentValidException       参数校验异常类
     */
    @RequestMapping(value = "/group/{id}", method = RequestMethod.PUT)
    //@Log(actionName = "更新组")
    public void updateGroup(@PathVariable(value = "id") String id,
                           @RequestBody @Valid GroupUpdate groupUpdate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        groupService.updateGroup(groupUpdate);
    }

    /**
     *  删除组
     * @param id    主键
     */
    @RequestMapping(value = "/group/{id}", method = RequestMethod.DELETE)
    //@Log(actionName = "删除单个组")
    public void deleteGroup(@PathVariable(value = "id") String id){
        groupService.deleteGroup(id);
    }

    /**
     *  删除组
     * @param ids    主键
     */
    @RequestMapping(value = "/group", method = RequestMethod.DELETE)
    //@Log(actionName = "批量删除组")
    public void deleteGroups(@RequestParam(value = "ids") String[] ids){
        groupService.deleteGroup(ids);
    }

    /**
    * 导出
    * @param id
    * @param ids
    * @param excludeIds
    * @param keyword
    * @param modelMap
    * @return
    */
    @RequestMapping(value="/group/report", method = RequestMethod.GET)
    public ModelAndView exportGroup(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "ids", required = false) String[] ids,
            @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
            @RequestParam(value = "keyword", required = false) String keyword,
            ModelMap modelMap) {
        GroupQuery groupQuery = new GroupQuery();
        groupQuery.setId(id);
        groupQuery.setExcludeIds(excludeIds);
        groupQuery.setIds(ids);
        groupQuery.setKeyword(keyword);
        List<GroupModel> groupList = groupService.getGroupModelList(groupQuery);
        modelMap.put(NormalExcelConstants.FILE_NAME, "组信息");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, GroupModel.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, groupList);
        return new ModelAndView(NormalExcelConstants.JEECG_EXCEL_VIEW);
    }


}
