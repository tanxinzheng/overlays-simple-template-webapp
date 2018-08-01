package com.xmomen.module.authorization.controller;

import com.github.pagehelper.Page;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.module.authorization.model.GroupPermissionModel;
import com.xmomen.module.authorization.model.GroupPermissionQuery;
import com.xmomen.module.authorization.service.GroupPermissionService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/group/permission")
public class GroupPermissionController extends BaseRestController {

    @Autowired
    GroupPermissionService groupPermissionService;

    /**
     * 组权限列表
     * @param   groupPermissionQuery    组权限查询参数对象
     * @return  Page<GroupPermissionModel> 组权限领域分页对象
     */
    @ApiOperation(value = "查询组权限列表")
    @PreAuthorize(value = "hasAnyAuthority('GROUPPERMISSION:VIEW')")
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
    @PreAuthorize(value = "hasAnyAuthority('GROUPPERMISSION:VIEW')")
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
    @PreAuthorize(value = "hasAnyAuthority('GROUPPERMISSION:CREATE')")
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
    @PreAuthorize(value = "hasAnyAuthority('GROUPPERMISSION:UPDATE')")
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
    @PreAuthorize(value = "hasAnyAuthority('GROUPPERMISSION:DELETE')")
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
    @PreAuthorize(value = "hasAnyAuthority('GROUPPERMISSION:DELETE')")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteGroupPermissions(GroupPermissionQuery groupPermissionQuery){
        Assert.notNull(groupPermissionQuery.getGroupId(), "groupId must be not null");
        Assert.notEmpty(groupPermissionQuery.getPermissionIds(), "permissionIds must be not empty");
        groupPermissionService.deleteGroupPermissions(groupPermissionQuery.getGroupId(), groupPermissionQuery.getPermissionIds());
    }


}
