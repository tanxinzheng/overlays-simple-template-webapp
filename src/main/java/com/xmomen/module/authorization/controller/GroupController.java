package com.xmomen.module.authorization.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import com.github.pagehelper.Page;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.module.authorization.model.*;
import com.xmomen.module.authorization.service.GroupPermissionService;
import com.xmomen.module.authorization.service.GroupService;

import com.xmomen.module.authorization.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.lang3.StringUtils;
import javax.validation.Valid;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 0:51:13
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/group")
public class GroupController extends BaseRestController {

    public static final String PERMISSION_GROUP_CREATE = "group:create";
    public static final String PERMISSION_GROUP_DELETE = "group:delete";
    public static final String PERMISSION_GROUP_UPDATE = "group:update";
    public static final String PERMISSION_GROUP_VIEW   = "group:view";

    @Autowired
    GroupService groupService;

    @Autowired
    GroupPermissionService groupPermissionService;

    @Autowired
    PermissionService permissionService;

    /**
     * 用户组列表
     * @param   groupQuery    用户组查询参数对象
     * @return  Page<GroupModel> 用户组领域分页对象
     */
    @ApiOperation(value = "查询用户组列表")
    @ActionLog(actionName = "查询用户组列表")
    //@RequiresPermissions(value = {PERMISSION_GROUP_VIEW})
    @RequestMapping(method = RequestMethod.GET)
    public Page<GroupModel> getGroupList(GroupQuery groupQuery){
        return groupService.getGroupModelPage(groupQuery);
    }

    /**
     * 查询单个用户组
     * @param   id  主键
     * @return  GroupModel   用户组领域对象
     */
    @ApiOperation(value = "查询用户组")
    @ActionLog(actionName = "查询用户组")
    //@RequiresPermissions(value = {PERMISSION_GROUP_VIEW})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public GroupModel getGroupById(@PathVariable(value = "id") String id){
        return groupService.getOneGroupModel(id);
    }

    /**
     * 新增用户组
     * @param   groupModel  新增对象参数
     * @return  GroupModel   用户组领域对象
     */
    @ApiOperation(value = "新增用户组")
    @ActionLog(actionName = "新增用户组")
    //@RequiresPermissions(value = {PERMISSION_GROUP_CREATE})
    @RequestMapping(method = RequestMethod.POST)
    public GroupModel createGroup(@RequestBody @Valid GroupModel groupModel) {
        return groupService.createGroup(groupModel);
    }

    /**
     * 更新用户组
     * @param id    主键
     * @param groupModel  更新对象参数
     * @return  GroupModel   用户组领域对象
     */
    @ApiOperation(value = "更新用户组")
    @ActionLog(actionName = "更新用户组")
    //@RequiresPermissions(value = {PERMISSION_GROUP_UPDATE})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public GroupModel updateGroup(@PathVariable(value = "id") String id,
                           @RequestBody @Valid GroupModel groupModel){
        if(StringUtils.isNotBlank(id)){
            groupModel.setId(id);
        }
        groupService.updateGroup(groupModel);
        return groupService.getOneGroupModel(id);
    }

    /**
     *  删除用户组
     * @param id    主键
     */
    @ApiOperation(value = "删除单个用户组")
    @ActionLog(actionName = "删除单个用户组")
    //@RequiresPermissions(value = {PERMISSION_GROUP_DELETE})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteGroup(@PathVariable(value = "id") String id){
        groupService.deleteGroup(id);
    }

    /**
     *  删除用户组
     * @param groupQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除用户组")
    @ActionLog(actionName = "批量删除用户组")
    //@RequiresPermissions(value = {PERMISSION_GROUP_DELETE})
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteGroups(@RequestBody GroupQuery groupQuery){
        groupService.deleteGroup(groupQuery.getIds());
    }

    /**
     * 批量新增用户组所属权限
     * @param groupId   组主键
     * @param permissionIds     权限主键集
     * @return List<GroupPermission>    组权限对象集
     */
    @ApiOperation(value = "批量删除用户组")
    @ActionLog(actionName = "新增组权限")
    @RequestMapping(value = "/{groupId}/permission", method = RequestMethod.POST)
    public void createGroupPermission(
            @PathVariable(value = "groupId") String groupId,
            @RequestParam(value = "permissionIds") String[] permissionIds){
        groupPermissionService.createGroupPermissions(groupId, permissionIds);
    }

    /**
     * 查询用户组所属权限
     * @param groupId
     * @param groupPermissionQuery
     * @return
     */
    @ApiOperation(value = "查询用户组所属权限")
    @ActionLog(actionName = "查询用户组所属权限")
    @RequestMapping(value = "/{groupId}/permission", method = RequestMethod.GET)
    public Page<PermissionModel> findPermissionByGroup(@PathVariable(value = "groupId") String groupId,
                                                       GroupPermissionQuery groupPermissionQuery){
        return groupPermissionService.getGroupPermissions(groupPermissionQuery);
    }
}
