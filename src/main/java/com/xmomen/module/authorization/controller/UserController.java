package com.xmomen.module.authorization.controller;

import com.github.pagehelper.Page;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.module.authorization.model.*;
import com.xmomen.module.authorization.service.PermissionService;
import com.xmomen.module.authorization.service.UserGroupService;
import com.xmomen.module.authorization.service.UserPermissionService;
import com.xmomen.module.authorization.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-6-16 22:59:54
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseRestController {

    @Autowired
    UserService userService;

    /**
     * 用户列表
     * @param   userQuery    用户查询参数对象
     * @return  Page<UserModel> 用户领域分页对象
     */
    @ApiOperation(value = "查询用户列表")
    @PreAuthorize(value = "hasAnyAuthority('USER:VIEW')")
    @RequestMapping(method = RequestMethod.GET)
    public Page<UserModel> getUserList(UserQuery userQuery){
        return userService.getUserModelPage(userQuery);
    }

    /**
     * 查询单个用户
     * @param   id  主键
     * @return  UserModel   用户领域对象
     */
    @ApiOperation(value = "查询用户")
    @PreAuthorize(value = "hasAnyAuthority('USER:VIEW')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserModel getUserById(@PathVariable(value = "id") String id){
        return userService.getOneUserModel(id);
    }

    /**
     * 新增用户
     * @param   userModel  新增对象参数
     * @return  UserModel   用户领域对象
     */
    @ApiOperation(value = "新增用户")
    @ActionLog(actionName = "新增用户")
    @PreAuthorize(value = "hasAnyAuthority('USER:CREATE')")
    @RequestMapping(method = RequestMethod.POST)
    public UserModel createUser(@RequestBody @Valid UserModel userModel) {
        return userService.createUser(userModel);
    }

    /**
     * 更新用户
     * @param id    主键
     * @param userModel  更新对象参数
     * @return  UserModel   用户领域对象
     */
    @ApiOperation(value = "更新用户")
    @ActionLog(actionName = "更新用户")
    @PreAuthorize(value = "hasAnyAuthority('USER:UPDATE')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public UserModel updateUser(@PathVariable(value = "id") String id,
                           @RequestBody @Valid UserModel userModel){
        if(StringUtils.isNotBlank(id)){
            userModel.setId(id);
        }
        userService.updateUser(userModel);
        return userService.getOneUserModel(id);
    }

    /**
     *  删除用户
     * @param id    主键
     */
    @ApiOperation(value = "删除单个用户")
    @ActionLog(actionName = "删除单个用户")
    @PreAuthorize(value = "hasAnyAuthority('USER:DELETE')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(value = "id") String id){
        userService.deleteUser(id);
    }

    /**
     *  删除用户
     * @param ids    查询参数对象
     */
    @ApiOperation(value = "批量删除用户")
    @ActionLog(actionName = "批量删除用户")
    @PreAuthorize(value = "hasAnyAuthority('USER:DELETE')")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteUsers(@RequestParam(value = "ids[]") String[] ids){
        userService.deleteUser(ids);
    }

    @Autowired
    PermissionService permissionService;

    @Autowired
    UserPermissionService userPermissionService;

    /**
     * 查询用户组权限
     * @param userId    用户主键
     * @return
     */
    @ActionLog(actionName = "查询用户组所属权限")
    @RequestMapping(value = "/{userId}/permission", method = RequestMethod.GET)
    public List<PermissionModel> getUserPermission(
            @PathVariable(value = "userId") String userId,
            UserPermissionQuery userPermissionQuery){
        userPermissionQuery.setUserId(userId);
        return userPermissionService.getUserPermissions(userPermissionQuery);
    }

    /**
     * 查询用户组权限
     * @param userId    用户主键
     * @param userGroupQuery    查询参数
     * @return
     */
    @ActionLog(actionName = "查询用户组所属权限")
    @RequestMapping(value = "/{userId}/group", method = RequestMethod.GET)
    public Page<GroupModel> getUserGroup(
            @PathVariable(value = "userId") String userId,
            UserGroupQuery userGroupQuery){
        userGroupQuery.setUserId(userId);
        return userGroupService.getUserGroupsPage(userGroupQuery);
    }

    @Autowired
    UserGroupService userGroupService;

    /**
     * 批量新增用户组
     * @param userId   用户主键
     * @param groupIds     组主键集
     * @return List<UserGroup>    用户组对象集
     */
    @RequestMapping(value = "/{userId}/group", method = RequestMethod.POST)
    public void createUserGroup(
            @PathVariable(value = "userId") String userId,
            @RequestParam(value = "groupIds") String[] groupIds){
        userGroupService.createUserGroups(userId, groupIds);
    }

}
