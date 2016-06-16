package com.xmomen.module.permission.controller;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.logger.Log;
import com.xmomen.module.permission.entity.SysPermissions;
import com.xmomen.module.permission.entity.SysPermissionsExample;
import com.xmomen.module.permission.model.CreatePermission;
import com.xmomen.module.permission.model.UpdatePermission;
import com.xmomen.module.permission.service.PermissionService;
import com.xmomen.module.permission.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jeng on 2016/1/5.
 */
@RestController
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @Autowired
    RoleService roleService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 权限权限
     * @return
     */
    @RequestMapping(value = "/user/permissions", method = RequestMethod.GET)
    public Map getPermission(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Set<String> roles = permissionService.findRoles(username);
        Set<String> permissions = permissionService.findPermissions(username);
        Map rolesMap = new HashMap();
        rolesMap.put("roles", roles);
        rolesMap.put("permissions", permissions);
        return rolesMap;
    }

    /**
     * 查看权限列表
     * @param limit 页数（每页最大记录数）
     * @param offset 页码
     * @param keyword 关键字
     * @return 权限资源分页集合对象
     */
    @RequestMapping(value = "/permission", method = RequestMethod.GET)
    @Log(actionName = "查看权限列表")
    public Page<SysPermissions> getPermissionList(@RequestParam(value = "limit") Integer limit,
                                        @RequestParam(value = "offset") Integer offset,
                                        @RequestParam(value = "keyword", required = false) String keyword){
        SysPermissionsExample sysPermissionsExample = new SysPermissionsExample();
        sysPermissionsExample.createCriteria()
                .andPermissionCodeLike("%" + StringUtils.trimToEmpty(keyword) + "%");
        sysPermissionsExample.or()
                .andDescriptionLike("%" + StringUtils.trimToEmpty(keyword) + "%");
        return mybatisDao.selectPageByExample(sysPermissionsExample, limit, offset);
    }

    /**
     * 查询单个权限资源
     * @param id 权限资源ID
     * @return 权限资源信息
     */
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询单个权限资源")
    public SysPermissions getPermission(@PathVariable(value = "id") Integer id){
        return mybatisDao.selectByPrimaryKey(SysPermissions.class, id);
    }

    /**
     * 新增权限
     * @param createPermission 新增权限表单信息
     * @param bindingResult 表单校验信息
     * @return 权限资源
     */
    @RequestMapping(value = "/permission", method = RequestMethod.POST)
    @Log(actionName = "新增权限资源")
    public SysPermissions createPermission(@RequestBody @Valid CreatePermission createPermission, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        SysPermissions sysPermissions = new SysPermissions();
        sysPermissions.setDescription(createPermission.getDescription());
        sysPermissions.setPermissionCode(createPermission.getPermissionCode().toUpperCase());
        sysPermissions.setAvailable(createPermission.getAvailable() != null && createPermission.getAvailable() ? 1 : 0);
        return permissionService.createPermission(sysPermissions);
    }

    /**
     *
     * 新增权限
     * @param id
     * @param updatePermission
     * @param bindingResult
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST)
    @Log(actionName = "新增权限资源")
    public void updatePermission(@PathVariable(value = "id") Integer id,
                                           @RequestBody @Valid UpdatePermission updatePermission, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        permissionService.updatePermission(updatePermission);
    }

    /**
     *  删除权限
     * @param id 权限资源ID
     */
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除权限资源")
    public void deletePermission(@PathVariable(value = "id") Integer id){
        mybatisDao.deleteByPrimaryKey(SysPermissions.class, id);
    }

}
