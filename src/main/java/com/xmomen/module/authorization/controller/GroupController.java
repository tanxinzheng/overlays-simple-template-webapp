package com.xmomen.module.authorization.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.authorization.entity.GroupPermission;
import com.xmomen.module.authorization.model.*;
import com.xmomen.module.authorization.service.GroupPermissionService;
import com.xmomen.module.authorization.service.GroupService;
import com.xmomen.module.authorization.service.PermissionService;
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
 * @date    2016-10-23 12:15:20
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired
    GroupPermissionService groupPermissionService;

    @Autowired
    PermissionService permissionService;

    /**
     * 组列表
     * @param   limit           每页结果数
     * @param   offset          页码
     * @param   keyword         关键字
     * @param   id              主键
     * @param   ids             主键数组
     * @param   excludeIds      不包含主键数组
     * @return  Page<GroupModel> 组领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    //@Log(actionName = "查询组列表")
    public Page<GroupModel> getGroupList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds){
        GroupQuery groupQuery = new GroupQuery();
        groupQuery.setId(id);
        groupQuery.setExcludeIds(excludeIds);
        groupQuery.setIds(ids);
        groupQuery.setKeyword(keyword);
        return groupService.getGroupModelPage(limit, offset, groupQuery);
    }

    /**
     * 查询单个组
     * @param   id  主键
     * @return  GroupModel   组领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    //@Log(actionName = "查询组")
    public GroupModel getGroupById(@PathVariable(value = "id") String id){
        return groupService.getOneGroupModel(id);
    }

    /**
     * 新增组
     * @param   groupCreate  新增对象参数
     * @param   bindingResult   参数校验结果
     * @return  GroupModel   组领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    //@Log(actionName = "新增组")
    public GroupModel createGroup(@RequestBody @Valid GroupCreate groupCreate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return groupService.createGroup(groupCreate);
    }

    /**
     * 更新组
     * @param id                            主键
     * @param groupUpdate 更新对象参数
     * @param bindingResult                 参数校验结果
     * @throws ArgumentValidException       参数校验异常类
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    //@Log(actionName = "更新组")
    public void updateGroup(@PathVariable(value = "id") String id,
                           @RequestBody @Valid GroupUpdate groupUpdate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        groupService.updateGroup(groupUpdate);
    }

    /**
     *  删除组
     * @param id    主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    //@Log(actionName = "删除单个组")
    public void deleteGroup(@PathVariable(value = "id") String id){
        groupService.deleteGroup(id);
    }

    /**
     *  删除组
     * @param ids    主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    //@Log(actionName = "批量删除组")
    public void deleteGroups(@RequestParam(value = "ids") String[] ids){
        groupService.deleteGroup(ids);
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
    public ModelAndView exportGroup(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "ids", required = false) String[] ids,
            @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
            @RequestParam(value = "keyword", required = false) String keyword,
            ModelMap modelMap) {
        GroupQuery groupQuery = new GroupQuery();
        groupQuery.setId(id);
        groupQuery.setExcludeIds(excludeIds);
        groupQuery.setIds(ids);
        groupQuery.setKeyword(keyword);
        List<GroupModel> groupList = groupService.getGroupModelList(groupQuery);
        modelMap.put(NormalExcelConstants.FILE_NAME, "组信息");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, GroupModel.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, groupList);
        return new ModelAndView(NormalExcelConstants.JEECG_EXCEL_VIEW);
    }

    /**
     * 查询用户组权限
     * @param groupId
     * @param limit
     * @param offset
     * @return
     */
//    @Log(actionName = "查询用户组所属权限")
    @RequestMapping(value = "/{id}/permission", method = RequestMethod.GET)
    public Page<PermissionModel> findPermissionByGroup(
            @PathVariable(value = "id") String groupId,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "hasPermission", required = false) Boolean hasPermission,
            @RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "offset") Integer offset){
        PermissionQuery permissionQuery = new PermissionQuery();
        permissionQuery.setGroupId(groupId);
        permissionQuery.setKeyword(keyword);
        permissionQuery.setHasPermission(hasPermission);
        return permissionService.getPermissionModelPage(limit, offset, permissionQuery);
    }

    /**
     * 批量新增组权限
     * @param groupId   组主键
     * @param permissionIds     权限主键集
     * @return List<GroupPermission>    组权限对象集
     */
    @RequestMapping(value = "/{id}/permission", method = RequestMethod.POST)
    public List<GroupPermission> createGroupPermission(
            @PathVariable(value = "id") String groupId,
            @RequestParam(value = "permissionIds") String[] permissionIds){
        return groupPermissionService.createGroupPermissions(groupId, permissionIds);
    }


}
