package com.xmomen.module.authorization.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.authorization.model.PermissionCreate;
import com.xmomen.module.authorization.model.PermissionModel;
import com.xmomen.module.authorization.model.PermissionQuery;
import com.xmomen.module.authorization.model.PermissionUpdate;
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
@RequestMapping(value = "/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    /**
     * 权限列表
     * @param   limit           每页结果数
     * @param   offset          页码
     * @param   keyword         关键字
     * @param   id              主键
     * @param   ids             主键数组
     * @param   excludeIds      不包含主键数组
     * @return  Page<PermissionModel> 权限领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    //@Log(actionName = "查询权限列表")
    public Page<PermissionModel> getPermissionList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds){
        PermissionQuery permissionQuery = new PermissionQuery();
        permissionQuery.setId(id);
        permissionQuery.setExcludeIds(excludeIds);
        permissionQuery.setIds(ids);
        permissionQuery.setKeyword(keyword);
        return permissionService.getPermissionModelPage(limit, offset, permissionQuery);
    }

    /**
     * 查询单个权限
     * @param   id  主键
     * @return  PermissionModel   权限领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    //@Log(actionName = "查询权限")
    public PermissionModel getPermissionById(@PathVariable(value = "id") String id){
        return permissionService.getOnePermissionModel(id);
    }

    /**
     * 新增权限
     * @param   permissionCreate  新增对象参数
     * @param   bindingResult   参数校验结果
     * @return  PermissionModel   权限领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    //@Log(actionName = "新增权限")
    public PermissionModel createPermission(@RequestBody @Valid PermissionCreate permissionCreate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return permissionService.createPermission(permissionCreate);
    }

    /**
     * 更新权限
     * @param id                            主键
     * @param permissionUpdate 更新对象参数
     * @param bindingResult                 参数校验结果
     * @throws ArgumentValidException       参数校验异常类
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    //@Log(actionName = "更新权限")
    public void updatePermission(@PathVariable(value = "id") String id,
                           @RequestBody @Valid PermissionUpdate permissionUpdate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        permissionService.updatePermission(permissionUpdate);
    }

    /**
     *  删除权限
     * @param id    主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    //@Log(actionName = "删除单个权限")
    public void deletePermission(@PathVariable(value = "id") String id){
        permissionService.deletePermission(id);
    }

    /**
     *  删除权限
     * @param ids    主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    //@Log(actionName = "批量删除权限")
    public void deletePermissions(@RequestParam(value = "ids") String[] ids){
        permissionService.deletePermission(ids);
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
    public ModelAndView exportPermission(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "ids", required = false) String[] ids,
            @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
            @RequestParam(value = "keyword", required = false) String keyword,
            ModelMap modelMap) {
        PermissionQuery permissionQuery = new PermissionQuery();
        permissionQuery.setId(id);
        permissionQuery.setExcludeIds(excludeIds);
        permissionQuery.setIds(ids);
        permissionQuery.setKeyword(keyword);
        List<PermissionModel> permissionList = permissionService.getPermissionModelList(permissionQuery);
        modelMap.put(NormalExcelConstants.FILE_NAME, "权限信息");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, PermissionModel.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, permissionList);
        return new ModelAndView(NormalExcelConstants.JEECG_EXCEL_VIEW);
    }


}
