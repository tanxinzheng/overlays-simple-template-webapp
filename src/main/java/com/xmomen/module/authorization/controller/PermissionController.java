package com.xmomen.module.authorization.controller;

import com.google.common.collect.Lists;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.poi.ExcelUtils;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.module.authorization.model.PermissionQuery;
import com.xmomen.module.authorization.model.PermissionModel;
import com.xmomen.module.authorization.service.PermissionService;

import com.xmomen.module.system.model.DictionaryModel;
import com.xmomen.module.system.model.DictionaryQuery;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-7-25 1:52:35
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/permission")
public class PermissionController extends BaseRestController {

    public static final String PERMISSION_PERMISSION_CREATE = "permission:create";
    public static final String PERMISSION_PERMISSION_DELETE = "permission:delete";
    public static final String PERMISSION_PERMISSION_UPDATE = "permission:update";
    public static final String PERMISSION_PERMISSION_VIEW   = "permission:view";

    @Autowired
    private PermissionService permissionService;

    /**
     * 权限列表.
     * @param   permissionQuery    权限查询参数对象
     * @return  Page<PermissionModel> 权限领域分页对象
     */
    @ApiOperation(value = "查询权限列表")
    @ActionLog(actionName = "查询权限列表")
    //@RequiresPermissions(value = {PERMISSION_PERMISSION_VIEW})
    @RequestMapping(method = RequestMethod.GET)
    public Page<PermissionModel> getPermissionList(final PermissionQuery permissionQuery) {
        if (permissionQuery.isPaging()) {
            return permissionService.getPermissionModelPage(permissionQuery);
        }
        List<PermissionModel> permissionList = permissionService.getPermissionModelList(permissionQuery);
        return new Page(permissionList);
    }

    /**
     * 查询单个权限.
     * @param   id  主键
     * @return  PermissionModel   权限领域对象
     */
    @ApiOperation(value = "查询权限")
    @ActionLog(actionName = "查询权限")
    //@RequiresPermissions(value = {PERMISSION_PERMISSION_VIEW})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PermissionModel getPermissionById(@PathVariable(value = "id") String id) {
        return permissionService.getOnePermissionModel(id);
    }

    /**
     * 新增权限
     * @param   permissionModel  新增对象参数
     * @return  PermissionModel   权限领域对象
     */
    @ApiOperation(value = "新增权限")
    @ActionLog(actionName = "新增权限")
    //@RequiresPermissions(value = {PERMISSION_PERMISSION_CREATE})
    @RequestMapping(method = RequestMethod.POST)
    public PermissionModel createPermission(@RequestBody @Valid PermissionModel permissionModel) {
        return permissionService.createPermission(permissionModel);
    }

    /**
     * 更新权限
     * @param id    主键
     * @param permissionModel  更新对象参数
     * @return  PermissionModel   权限领域对象
     */
    @ApiOperation(value = "更新权限")
    @ActionLog(actionName = "更新权限")
    //@RequiresPermissions(value = {PERMISSION_PERMISSION_UPDATE})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public PermissionModel updatePermission(@PathVariable(value = "id") String id,
                           @RequestBody @Valid PermissionModel permissionModel){
        if(StringUtils.isNotBlank(id)){
            permissionModel.setId(id);
        }
        permissionService.updatePermission(permissionModel);
        return permissionService.getOnePermissionModel(id);
    }

    /**
     *  删除权限
     * @param id    主键
     */
    @ApiOperation(value = "删除单个权限")
    @ActionLog(actionName = "删除单个权限")
    //@RequiresPermissions(value = {PERMISSION_PERMISSION_DELETE})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePermission(@PathVariable(value = "id") String id){
        permissionService.deletePermission(id);
    }

    /**
     *  删除权限
     * @param permissionQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除权限")
    @ActionLog(actionName = "批量删除权限")
    //@RequiresPermissions(value = {PERMISSION_PERMISSION_DELETE})
    @RequestMapping(method = RequestMethod.DELETE)
    public void deletePermissions(final PermissionQuery permissionQuery){
        permissionService.deletePermission(permissionQuery.getIds());
    }

    /**
     * 下载Excel模板
     * @return ModelAndView JEECG_EXCEL_VIEW Excel报表视图
     */
    @RequestMapping(value="/template", method = RequestMethod.GET)
    public ModelAndView downloadTemplate(ModelMap modelMap) {
        List<PermissionModel> list = Lists.newArrayList();
        return ExcelUtils.export(modelMap, PermissionModel.class, list, "权限_模板");
    }

    /**
     * 导出Excel
     * @param permissionQuery    查询参数对象
     * @return ModelAndView JEECG_EXCEL_VIEW Excel报表视图
     */
    @RequestMapping(value="/export", method = RequestMethod.GET)
    public ModelAndView exportDictionaries(PermissionQuery permissionQuery,
                                           ModelMap modelMap) {
        List<PermissionModel> list = permissionService.getPermissionModelList(permissionQuery);
        return ExcelUtils.export(modelMap, PermissionModel.class, list, "权限");
    }

    /**
     * 导入Excel
     * @param file
     */
    @RequestMapping(value="/import", method = RequestMethod.POST)
    public void importDictionaries(@RequestParam("file") MultipartFile file) throws IOException {
        List<PermissionModel> list = ExcelUtils.transform(file, PermissionModel.class);
        if(CollectionUtils.isEmpty(list)){
            return;
        }
        permissionService.createPermissions(list, getCurrentUserId());
    }


}
