package com.xmomen.module.authorization.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.module.authorization.model.UserGroupQuery;
import com.xmomen.module.authorization.model.UserGroupModel;
import com.xmomen.module.authorization.service.UserGroupService;

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
@RequestMapping(value = "/user/group")
public class UserGroupController extends BaseRestController {

    public static final String PERMISSION_USERGROUP_CREATE = "usergroup:create";
    public static final String PERMISSION_USERGROUP_DELETE = "usergroup:delete";
    public static final String PERMISSION_USERGROUP_UPDATE = "usergroup:update";
    public static final String PERMISSION_USERGROUP_VIEW   = "usergroup:view";

    @Autowired
    UserGroupService userGroupService;

    /**
     * 用户组关联列表
     * @param   userGroupQuery    用户组关联查询参数对象
     * @return  Page<UserGroupModel> 用户组关联领域分页对象
     */
    @ApiOperation(value = "查询用户组关联列表")
    @ActionLog(actionName = "查询用户组关联列表")
    @RequiresPermissions(value = {PERMISSION_USERGROUP_VIEW})
    @RequestMapping(method = RequestMethod.GET)
    public Page<UserGroupModel> getUserGroupList(UserGroupQuery userGroupQuery){
        if(userGroupQuery.isPaging()){
            return userGroupService.getUserGroupModelPage(userGroupQuery);
        }
        List<UserGroupModel> userGroupList = userGroupService.getUserGroupModelList(userGroupQuery);
        return new Page(userGroupList);
    }

    /**
     * 查询单个用户组关联
     * @param   id  主键
     * @return  UserGroupModel   用户组关联领域对象
     */
    @ApiOperation(value = "查询用户组关联")
    @ActionLog(actionName = "查询用户组关联")
    @RequiresPermissions(value = {PERMISSION_USERGROUP_VIEW})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserGroupModel getUserGroupById(@PathVariable(value = "id") String id){
        return userGroupService.getOneUserGroupModel(id);
    }

    /**
     * 新增用户组关联
     * @param   userGroupModel  新增对象参数
     * @return  UserGroupModel   用户组关联领域对象
     */
    @ApiOperation(value = "新增用户组关联")
    @ActionLog(actionName = "新增用户组关联")
    @RequiresPermissions(value = {PERMISSION_USERGROUP_CREATE})
    @RequestMapping(method = RequestMethod.POST)
    public UserGroupModel createUserGroup(@RequestBody @Valid UserGroupModel userGroupModel) {
        return userGroupService.createUserGroup(userGroupModel);
    }

    /**
     * 更新用户组关联
     * @param id    主键
     * @param userGroupModel  更新对象参数
     * @return  UserGroupModel   用户组关联领域对象
     */
    @ApiOperation(value = "更新用户组关联")
    @ActionLog(actionName = "更新用户组关联")
    @RequiresPermissions(value = {PERMISSION_USERGROUP_UPDATE})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public UserGroupModel updateUserGroup(@PathVariable(value = "id") String id,
                           @RequestBody @Valid UserGroupModel userGroupModel){
        if(StringUtils.isNotBlank(id)){
            userGroupModel.setId(id);
        }
        userGroupService.updateUserGroup(userGroupModel);
        return userGroupService.getOneUserGroupModel(id);
    }

    /**
     *  删除用户组关联
     * @param id    主键
     */
    @ApiOperation(value = "删除单个用户组关联")
    @ActionLog(actionName = "删除单个用户组关联")
    @RequiresPermissions(value = {PERMISSION_USERGROUP_DELETE})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUserGroup(@PathVariable(value = "id") String id){
        userGroupService.deleteUserGroup(id);
    }

    /**
     *  删除用户组关联
     * @param userGroupQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除用户组关联")
    @ActionLog(actionName = "批量删除用户组关联")
    @RequiresPermissions(value = {PERMISSION_USERGROUP_DELETE})
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteUserGroups(UserGroupQuery userGroupQuery){
        userGroupService.deleteUserGroup(userGroupQuery.getIds());
    }


}
