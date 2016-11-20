package com.xmomen.module.authorization.controller;

import com.xmomen.commons.StringUtilsExt;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.authorization.model.UserGroupCreate;
import com.xmomen.module.authorization.model.UserGroupModel;
import com.xmomen.module.authorization.model.UserGroupQuery;
import com.xmomen.module.authorization.model.UserGroupUpdate;
import com.xmomen.module.authorization.service.UserGroupService;
import com.xmomen.module.logger.Log;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2016-10-23 12:15:20
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/user/group")
public class UserGroupController {

    @Autowired
    UserGroupService userGroupService;

    /**
     * 用户组列表
     * @param   limit           每页结果数
     * @param   offset          页码
     * @param   id              主键
     * @param   ids             主键数组
     * @param   excludeIds      不包含主键数组
     * @return  Page<UserGroupModel> 用户组领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    @Log(actionName = "查询用户组列表")
    public Page<UserGroupModel> getUserGroupList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds){
        UserGroupQuery userGroupQuery = new UserGroupQuery();
        userGroupQuery.setId(id);
        userGroupQuery.setExcludeIds(excludeIds);
        userGroupQuery.setIds(ids);
        return userGroupService.getUserGroupModelPage(limit, offset, userGroupQuery);
    }

    /**
     * 查询单个用户组
     * @param   id  主键
     * @return  UserGroupModel   用户组领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询用户组")
    public UserGroupModel getUserGroupById(@PathVariable(value = "id") String id){
        return userGroupService.getOneUserGroupModel(id);
    }

    /**
     * 新增用户组
     * @param   userGroupCreate  新增对象参数
     * @param   bindingResult   参数校验结果
     * @return  UserGroupModel   用户组领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    @Log(actionName = "新增用户组")
    public UserGroupModel createUserGroup(@RequestBody @Valid UserGroupCreate userGroupCreate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return userGroupService.createUserGroup(userGroupCreate);
    }

    /**
     * 更新用户组
     * @param id                            主键
     * @param userGroupUpdate 更新对象参数
     * @param bindingResult                 参数校验结果
     * @throws ArgumentValidException       参数校验异常类
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新用户组")
    public void updateUserGroup(@PathVariable(value = "id") String id,
                           @RequestBody @Valid UserGroupUpdate userGroupUpdate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        userGroupService.updateUserGroup(userGroupUpdate);
    }

    /**
     *  删除用户组
     * @param id    主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除单个用户组")
    public void deleteUserGroup(@PathVariable(value = "id") String id){
        userGroupService.deleteUserGroup(id);
    }

    /**
     *  删除用户组
     * @param ids    主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @Log(actionName = "批量删除用户组")
    public void deleteUserGroups(@RequestParam(value = "ids", required = false) String[] ids,
                                 @RequestParam(value = "groupIds", required = false) String[] groupIds,
                                 @RequestParam(value = "userId", required = false) String userId){
        if(StringUtilsExt.trimToNull(userId) != null && groupIds != null && groupIds.length > 0){
            UserGroupQuery userGroupQuery = new UserGroupQuery();
            userGroupQuery.setGroupIds(groupIds);
            userGroupQuery.setUserId(userId);
            List<UserGroupModel> userGroupModelList = userGroupService.getUserGroupModelList(userGroupQuery);
            if(ids == null){
                List<String> list = new ArrayList<>();
                for (UserGroupModel userGroupModel : userGroupModelList) {
                    list.add(userGroupModel.getId());
                }
                ids = list.toArray(new String[list.size()]);
            }
        }
        if(ids != null && ids.length > 0) {
            userGroupService.deleteUserGroup(ids);
        }
    }

    /**
    * 导出
    * @param id     主键
    * @param ids    包含的主键数组
    * @param excludeIds     排除的主键数组
    * @param modelMap   modelMap对象
    * @return ModelAndView JEECG_EXCEL_VIEW Excel报表视图
    */
    @RequestMapping(value="/export", method = RequestMethod.GET)
    public ModelAndView exportUserGroup(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "ids", required = false) String[] ids,
            @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
            ModelMap modelMap) {
        UserGroupQuery userGroupQuery = new UserGroupQuery();
        userGroupQuery.setId(id);
        userGroupQuery.setExcludeIds(excludeIds);
        userGroupQuery.setIds(ids);
        List<UserGroupModel> userGroupList = userGroupService.getUserGroupModelList(userGroupQuery);
        modelMap.put(NormalExcelConstants.FILE_NAME, "用户组信息");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, UserGroupModel.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, userGroupList);
        return new ModelAndView(NormalExcelConstants.JEECG_EXCEL_VIEW);
    }


}
