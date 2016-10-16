package com.xmomen.module.user.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
//import com.xmomen.module.logger.Log;
import com.xmomen.module.user.model.UserCreate;
import com.xmomen.module.user.model.UserQuery;
import com.xmomen.module.user.model.UserUpdate;
import com.xmomen.module.user.model.UserModel;
import com.xmomen.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-10-16 20:34:14
 * @version 1.0.0
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户列表
     * @param   limit           每页结果数
     * @param   offset          页码
     * @param   id              主键
     * @param   ids             主键数组
     * @param   excludeIds      不包含主键数组
     * @param   keyword         关键字
     * @return  Page<UserModel> 用户领域分页对象
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    //@Log(actionName = "查询用户列表")
    public Page<UserModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
                                  @RequestParam(value = "keyword", required = false) String keyword){
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
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    //@Log(actionName = "查询用户")
    public UserModel getUserById(@PathVariable(value = "id") String id){
        return userService.getOneUserModel(id);
    }

    /**
     * 新增用户
     * @param   UserCreate  新增对象参数
     * @param   bindingResult   参数校验结果
     * @return  UserModel   用户领域对象
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
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
     * @param UserUpdate 更新对象参数
     * @param bindingResult                 参数校验结果
     * @throws ArgumentValidException       参数校验异常类
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
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
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    //@Log(actionName = "删除单个用户")
    public void deleteUser(@PathVariable(value = "id") String id){
        userService.deleteUser(id);
    }

    /**
     *  删除用户
     * @param ids    主键
     */
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    //@Log(actionName = "批量删除用户")
    public void deleteUsers(@RequestParam(value = "ids") String[] ids){
        userService.deleteUser(ids);
    }

}
