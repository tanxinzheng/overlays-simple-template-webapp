package com.xmomen.module.user.controller;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.logger.Log;
import com.xmomen.module.user.entity.SysUsers;
import com.xmomen.module.user.mapper.UserMapper;
import com.xmomen.module.user.model.CreateUser;
import com.xmomen.module.user.model.UpdateUser;
import com.xmomen.module.user.model.User;
import com.xmomen.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeng on 2016/1/5.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    MybatisDao mybatisDao;

    /**
     *  用户列表
     * @param id
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @Log(actionName = "查询用户列表")
    public Page<User> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "id", required = false) Integer id,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "organizationId",required = false) Integer organizationId){
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("id", id);
        map.put("keyword", keyword);
        map.put("organizationId", organizationId);
        return (Page<User>) mybatisDao.selectPage(UserMapper.UserMapperNameSpace + "getUsers", map, limit, offset);
    }

    /**
     *  用户列表
     * @param id
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询用户")
    public SysUsers getUserList(@PathVariable(value = "id") Integer id){
        return mybatisDao.selectByPrimaryKey(SysUsers.class, id);
    }

    /**
     * 新增用户
     * @param createUser
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @Log(actionName = "新增用户")
    public SysUsers createUser(@RequestBody @Valid CreateUser createUser, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        CreateUser user = new CreateUser();
        user.setAge(createUser.getAge());
        user.setOfficeTel(createUser.getOfficeTel());
        user.setPhoneNumber(createUser.getPhoneNumber());
        user.setQq(createUser.getQq());
        user.setRealname(createUser.getRealname());
        user.setSex(createUser.getSex());
        user.setUsername(createUser.getUsername());
        user.setPassword(createUser.getPassword());
        user.setEmail(createUser.getEmail());
        user.setLocked(createUser.getLocked() != null && createUser.getLocked() == true ? true : false);
        user.setUserGroupIds(createUser.getUserGroupIds());
        return userService.createUser(user);
    }

    /**
     * 更新用户
     * @param id
     * @param updateUser
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新用户")
    public void updateUser(@PathVariable(value = "id") Integer id,
                           @RequestBody @Valid UpdateUser updateUser, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        userService.updateUser(updateUser);
    }

    /**
     *  删除用户
     * @param id
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除用户")
    public void deleteUser(@PathVariable(value = "id") Integer id){
        mybatisDao.deleteByPrimaryKey(SysUsers.class, id);
    }

    /**
     * 锁定用户
     * @param id
     * @param locked
     */
    @RequestMapping(value = "/user/{id}/locked", method = RequestMethod.PUT)
    @Log(actionName = "修改用户信息")
    public void lockedUser(@PathVariable(value = "id") Integer id,
                           @RequestParam(value = "locked") Boolean locked){
        SysUsers sysUsers = new SysUsers();
        sysUsers.setLocked(locked ? 1 : 0);
        sysUsers.setId(id);
        mybatisDao.update(sysUsers);
    }

}
