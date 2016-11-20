package com.xmomen.module.user.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.authorization.entity.UserGroup;
import com.xmomen.module.authorization.entity.UserPermission;
import com.xmomen.module.authorization.model.GroupModel;
import com.xmomen.module.authorization.model.GroupQuery;
import com.xmomen.module.authorization.model.PermissionModel;
import com.xmomen.module.authorization.model.PermissionQuery;
import com.xmomen.module.authorization.service.GroupService;
import com.xmomen.module.authorization.service.PermissionService;
import com.xmomen.module.authorization.service.UserGroupService;
import com.xmomen.module.authorization.service.UserPermissionService;
import com.xmomen.module.logger.Log;
import com.xmomen.module.user.model.UserCreate;
import com.xmomen.module.user.model.UserModel;
import com.xmomen.module.user.model.UserQuery;
import com.xmomen.module.user.model.UserUpdate;
import com.xmomen.module.user.service.UserService;
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
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户列表
     * @param   limit           每页结果数
     * @param   offset          页码
     * @param   keyword         关键字
     * @param   id              主键
     * @param   ids             主键数组
     * @param   excludeIds      不包含主键数组
     * @return  Page<UserModel> 用户领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    //@Log(actionName = "查询用户列表")
    public Page<UserModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds){
        UserQuery userQuery = new UserQuery();
        userQuery.setId(id);
        userQuery.setExcludeIds(excludeIds);
        userQuery.setIds(ids);
        userQuery.setKeyword(keyword);
        return userService.getUserModelPage(limit, offset, userQuery);
    }

    /**
     * 查询单个用户
     * @param   id  主键
     * @return  UserModel   用户领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    //@Log(actionName = "查询用户")
    public UserModel getUserById(@PathVariable(value = "id") String id){
        return userService.getOneUserModel(id);
    }

    /**
     * 新增用户
     * @param   userCreate  新增对象参数
     * @param   bindingResult   参数校验结果
     * @return  UserModel   用户领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    //@Log(actionName = "新增用户")
    public UserModel createUser(@RequestBody @Valid UserCreate userCreate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return userService.createUser(userCreate);
    }

    /**
     * 更新用户
     * @param id                            主键
     * @param userUpdate 更新对象参数
     * @param bindingResult                 参数校验结果
     * @throws ArgumentValidException       参数校验异常类
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    //@Log(actionName = "更新用户")
    public void updateUser(@PathVariable(value = "id") String id,
                           @RequestBody @Valid UserUpdate userUpdate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        userService.updateUser(userUpdate);
    }

    /**
     *  删除用户
     * @param id    主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    //@Log(actionName = "删除单个用户")
    public void deleteUser(@PathVariable(value = "id") String id){
        userService.deleteUser(id);
    }

    /**
     *  删除用户
     * @param ids    主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    //@Log(actionName = "批量删除用户")
    public void deleteUsers(@RequestParam(value = "ids") String[] ids){
        userService.deleteUser(ids);
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
    public ModelAndView exportUser(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "ids", required = false) String[] ids,
            @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
            @RequestParam(value = "keyword", required = false) String keyword,
            ModelMap modelMap) {
        UserQuery userQuery = new UserQuery();
        userQuery.setId(id);
        userQuery.setExcludeIds(excludeIds);
        userQuery.setIds(ids);
        userQuery.setKeyword(keyword);
        List<UserModel> userList = userService.getUserModelList(userQuery);
        modelMap.put(NormalExcelConstants.FILE_NAME, "用户信息");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, UserModel.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, userList);
        return new ModelAndView(NormalExcelConstants.JEECG_EXCEL_VIEW);
    }

    @Autowired
    PermissionService permissionService;

    /**
     * 查询用户组权限
     * @param userId    用户主键
     * @param limit     最大页数
     * @param offset    页码
     * @return
     */
    @Log(actionName = "查询用户组所属权限")
    @RequestMapping(value = "/{id}/permission", method = RequestMethod.GET)
    public Page<PermissionModel> getUserPermission(
            @PathVariable(value = "id") String userId,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "hasPermission", required = false) Boolean hasPermission,
            @RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "offset") Integer offset){
        PermissionQuery permissionQuery = new PermissionQuery();
        permissionQuery.setUserId(userId);
        permissionQuery.setKeyword(keyword);
        permissionQuery.setHasPermission(hasPermission);
        return permissionService.getPermissionModelPage(limit, offset, permissionQuery);
    }

    @Autowired
    UserPermissionService userPermissionService;

    /**
     * 批量新增组权限
     * @param userId   用户主键
     * @param permissionIds     权限主键集
     * @return List<UserPermission>    用户权限对象集
     */
    @RequestMapping(value = "/{id}/permission", method = RequestMethod.POST)
    public List<UserPermission> createGroupPermission(
            @PathVariable(value = "id") String userId,
            @RequestParam(value = "permissionIds") String[] permissionIds){
        return userPermissionService.createUserPermissions(userId, permissionIds);
    }

    @Autowired
    GroupService groupService;

    /**
     * 查询用户组权限
     * @param userId    用户主键
     * @param keyword   关键字
     * @param hasGroup     是否查询已有组
     * @param limit     每页数量
     * @param offset    页码
     * @return
     */
//    @Log(actionName = "查询用户组所属权限")
    @RequestMapping(value = "/{id}/group", method = RequestMethod.GET)
    public Page<GroupModel> getUserGroup(
            @PathVariable(value = "id") String userId,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "hasGroup", required = false) Boolean hasGroup,
            @RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "offset") Integer offset){
        GroupQuery groupQuery = new GroupQuery();
        groupQuery.setUserId(userId);
        groupQuery.setKeyword(keyword);
        groupQuery.setHasGroup(hasGroup);
        return groupService.getGroupModelPage(limit, offset, groupQuery);
    }

    @Autowired
    UserGroupService userGroupService;

    /**
     * 批量新增用户组
     * @param userId   用户主键
     * @param groupIds     组主键集
     * @return List<UserGroup>    用户组对象集
     */
    @RequestMapping(value = "/{id}/group", method = RequestMethod.POST)
    public List<UserGroup> createUserGroup(
            @PathVariable(value = "id") String userId,
            @RequestParam(value = "groupIds") String[] groupIds){
        return userGroupService.createUserGroups(userId, groupIds);
    }

}
