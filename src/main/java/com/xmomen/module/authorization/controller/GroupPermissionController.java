package com.xmomen.module.authorization.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import com.github.pagehelper.Page;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.module.authorization.model.GroupPermissionQuery;
import com.xmomen.module.authorization.model.GroupPermissionModel;
import com.xmomen.module.authorization.service.GroupPermissionService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.lang3.StringUtils;
import javax.validation.Valid;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/group/permission")
public class GroupPermissionController extends BaseRestController {

    public static final String PERMISSION_GROUPPERMISSION_CREATE = "grouppermission:create";
    public static final String PERMISSION_GROUPPERMISSION_DELETE = "grouppermission:delete";
    public static final String PERMISSION_GROUPPERMISSION_UPDATE = "grouppermission:update";
    public static final String PERMISSION_GROUPPERMISSION_VIEW   = "grouppermission:view";

    @Autowired
    GroupPermissionService groupPermissionService;

    /**
     * 组权限列表
     * @param   groupPermissionQuery    组权限查询参数对象
     * @return  Page<GroupPermissionModel> 组权限领域分页对象
     */
    @ApiOperation(value = "查询组权限列表")
    @ActionLog(actionName = "查询组权限列表")
    //@RequiresPermissions(value = {PERMISSION_GROUPPERMISSION_VIEW})
    @RequestMapping(method = RequestMethod.GET)
    public Page<GroupPermissionModel> getGroupPermissionList(GroupPermissionQuery groupPermissionQuery){
        return groupPermissionService.getGroupPermissionModelPage(groupPermissionQuery);
    }

    /**
     * 查询单个组权限
     * @param   id  主键
     * @return  GroupPermissionModel   组权限领域对象
     */
    @ApiOperation(value = "查询组权限")
    @ActionLog(actionName = "查询组权限")
    //@RequiresPermissions(value = {PERMISSION_GROUPPERMISSION_VIEW})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public GroupPermissionModel getGroupPermissionById(@PathVariable(value = "id") String id){
        return groupPermissionService.getOneGroupPermissionModel(id);
    }

    /**
     * 新增组权限
     * @param   groupPermissionModel  新增对象参数
     * @return  GroupPermissionModel   组权限领域对象
     */
    @ApiOperation(value = "新增组权限")
    @ActionLog(actionName = "新增组权限")
    //@RequiresPermissions(value = {PERMISSION_GROUPPERMISSION_CREATE})
    @RequestMapping(method = RequestMethod.POST)
    public GroupPermissionModel createGroupPermission(@RequestBody @Valid GroupPermissionModel groupPermissionModel) {
        return groupPermissionService.createGroupPermission(groupPermissionModel);
    }

    /**
     * 更新组权限
     * @param id    主键
     * @param groupPermissionModel  更新对象参数
     * @return  GroupPermissionModel   组权限领域对象
     */
    @ApiOperation(value = "更新组权限")
    @ActionLog(actionName = "更新组权限")
    //@RequiresPermissions(value = {PERMISSION_GROUPPERMISSION_UPDATE})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public GroupPermissionModel updateGroupPermission(@PathVariable(value = "id") String id,
                           @RequestBody @Valid GroupPermissionModel groupPermissionModel){
        if(StringUtils.isNotBlank(id)){
            groupPermissionModel.setId(id);
        }
        groupPermissionService.updateGroupPermission(groupPermissionModel);
        return groupPermissionService.getOneGroupPermissionModel(id);
    }

    /**
     *  删除组权限
     * @param id    主键
     */
    @ApiOperation(value = "删除单个组权限")
    @ActionLog(actionName = "删除单个组权限")
    //@RequiresPermissions(value = {PERMISSION_GROUPPERMISSION_DELETE})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteGroupPermission(@PathVariable(value = "id") String id){
        groupPermissionService.deleteGroupPermission(id);
    }

    /**
     *  删除组权限
     * @param groupPermissionQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除组权限")
    @ActionLog(actionName = "批量删除组权限")
    //@RequiresPermissions(value = {PERMISSION_GROUPPERMISSION_DELETE})
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteGroupPermissions(GroupPermissionQuery groupPermissionQuery){
        groupPermissionService.deleteGroupPermission(groupPermissionQuery.getIds());
    }


}
