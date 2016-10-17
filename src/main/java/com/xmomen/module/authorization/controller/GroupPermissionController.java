package com.xmomen.module.authorization.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
//import com.xmomen.module.logger.Log;
import com.xmomen.module.authorization.model.GroupPermissionCreate;
import com.xmomen.module.authorization.model.GroupPermissionQuery;
import com.xmomen.module.authorization.model.GroupPermissionUpdate;
import com.xmomen.module.authorization.model.GroupPermissionModel;
import com.xmomen.module.authorization.service.GroupPermissionService;
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
public class GroupPermissionController {

    @Autowired
    GroupPermissionService groupPermissionService;

    /**
     * 组权限列表
     * @param   limit           每页结果数
     * @param   offset          页码
     * @param   id              主键
     * @param   ids             主键数组
     * @param   excludeIds      不包含主键数组
     * @param   keyword         关键字
     * @return  Page<GroupPermissionModel> 组权限领域分页对象
     */
    @RequestMapping(value = "/groupPermission", method = RequestMethod.GET)
    //@Log(actionName = "查询组权限列表")
    public Page<GroupPermissionModel> getGroupPermissionList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
                                  @RequestParam(value = "keyword", required = false) String keyword){
        GroupPermissionQuery groupPermissionQuery = new GroupPermissionQuery();
        groupPermissionQuery.setId(id);
        groupPermissionQuery.setExcludeIds(excludeIds);
        groupPermissionQuery.setIds(ids);
        groupPermissionQuery.setKeyword(keyword);
        return groupPermissionService.getGroupPermissionModelPage(limit, offset, groupPermissionQuery);
    }

    /**
     * 查询单个组权限
     * @param   id  主键
     * @return  GroupPermissionModel   组权限领域对象
     */
    @RequestMapping(value = "/groupPermission/{id}", method = RequestMethod.GET)
    //@Log(actionName = "查询组权限")
    public GroupPermissionModel getGroupPermissionById(@PathVariable(value = "id") String id){
        return groupPermissionService.getOneGroupPermissionModel(id);
    }

    /**
     * 新增组权限
     * @param   GroupPermissionCreate  新增对象参数
     * @param   bindingResult   参数校验结果
     * @return  GroupPermissionModel   组权限领域对象
     */
    @RequestMapping(value = "/groupPermission", method = RequestMethod.POST)
    //@Log(actionName = "新增组权限")
    public GroupPermissionModel createGroupPermission(@RequestBody @Valid GroupPermissionCreate groupPermissionCreate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return groupPermissionService.createGroupPermission(groupPermissionCreate);
    }

    /**
     * 更新组权限
     * @param id                            主键
     * @param GroupPermissionUpdate 更新对象参数
     * @param bindingResult                 参数校验结果
     * @throws ArgumentValidException       参数校验异常类
     */
    @RequestMapping(value = "/groupPermission/{id}", method = RequestMethod.PUT)
    //@Log(actionName = "更新组权限")
    public void updateGroupPermission(@PathVariable(value = "id") String id,
                           @RequestBody @Valid GroupPermissionUpdate groupPermissionUpdate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        groupPermissionService.updateGroupPermission(groupPermissionUpdate);
    }

    /**
     *  删除组权限
     * @param id    主键
     */
    @RequestMapping(value = "/groupPermission/{id}", method = RequestMethod.DELETE)
    //@Log(actionName = "删除单个组权限")
    public void deleteGroupPermission(@PathVariable(value = "id") String id){
        groupPermissionService.deleteGroupPermission(id);
    }

    /**
     *  删除组权限
     * @param ids    主键
     */
    @RequestMapping(value = "/groupPermission", method = RequestMethod.DELETE)
    //@Log(actionName = "批量删除组权限")
    public void deleteGroupPermissions(@RequestParam(value = "ids") String[] ids){
        groupPermissionService.deleteGroupPermission(ids);
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
    @RequestMapping(value="/groupPermission/report", method = RequestMethod.GET)
    public ModelAndView exportGroupPermission(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "ids", required = false) String[] ids,
            @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
            @RequestParam(value = "keyword", required = false) String keyword,
            ModelMap modelMap) {
        GroupPermissionQuery groupPermissionQuery = new GroupPermissionQuery();
        groupPermissionQuery.setId(id);
        groupPermissionQuery.setExcludeIds(excludeIds);
        groupPermissionQuery.setIds(ids);
        groupPermissionQuery.setKeyword(keyword);
        List<GroupPermissionModel> groupPermissionList = groupPermissionService.getGroupPermissionModelList(groupPermissionQuery);
        modelMap.put(NormalExcelConstants.FILE_NAME, "组权限信息");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, GroupPermissionModel.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, groupPermissionList);
        return new ModelAndView(NormalExcelConstants.JEECG_EXCEL_VIEW);
    }


}
