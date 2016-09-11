package com.xmomen.module.user.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
//import com.xmomen.module.logger.Log;
import com.xmomen.module.user.model.CreateUser;
import com.xmomen.module.user.model.QueryUser;
import com.xmomen.module.user.model.UpdateUser;
import com.xmomen.module.user.model.UserModel;
import com.xmomen.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2016-9-11 18:43:01
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
     * @return  Page<UserModel>      用户领域分页对象
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    //@Log(actionName = "查询用户列表")
    public Page<UserModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
                                  @RequestParam(value = "keyword", required = false) String keyword){
        QueryUser queryUser = new QueryUser();
        queryUser.setId(id);
        queryUser.setExcludeIds(excludeIds);
        queryUser.setIds(ids);
        queryUser.setKeyword(keyword);
        return userService.getUserModelPage(limit, offset, queryUser);
    }

    /**
     * 查询单个用户
     * @param   id      主键
     * @return  UserModel    用户领域对象
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    //@Log(actionName = "查询用户")
    public UserModel getUserById(@PathVariable(value = "id") String id){
        return userService.getOneUserModel(id);
    }

    /**
     * 新增用户
     * @param   createUser          新增对象参数
     * @param   bindingResult       参数校验结果
     * @return  UserModel                用户领域对象
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    //@Log(actionName = "新增用户")
    public UserModel createUser(@RequestBody @Valid CreateUser createUser, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return userService.createUser(createUser);
    }

    /**
     * 更新用户
     * @param id                            主键
     * @param updateUser                    更新对象参数
     * @param bindingResult                 参数校验结果
     * @throws ArgumentValidException       参数校验异常类
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    //@Log(actionName = "更新用户")
    public void updateUser(@PathVariable(value = "id") String id,
                           @RequestBody @Valid UpdateUser updateUser, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        userService.updateUser(updateUser);
    }

    /**
     *  删除用户
     * @param id    主键
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    //@Log(actionName = "删除单个用户")
    public void deleteUser(@PathVariable(value = "id") String id){
        String[] ids = {id};
        userService.deleteUser(ids);
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
