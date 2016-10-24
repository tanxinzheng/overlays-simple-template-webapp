package com.xmomen.module.notification.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.notification.model.SystemNotificationCreate;
import com.xmomen.module.notification.model.SystemNotificationModel;
import com.xmomen.module.notification.model.SystemNotificationQuery;
import com.xmomen.module.notification.model.SystemNotificationUpdate;
import com.xmomen.module.notification.service.SystemNotificationService;
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
@RequestMapping(value = "/systemNotification")
public class SystemNotificationController {

    @Autowired
    SystemNotificationService systemNotificationService;

    /**
     * 系统通知列表
     * @param   limit           每页结果数
     * @param   offset          页码
     * @param   id              主键
     * @param   ids             主键数组
     * @param   excludeIds      不包含主键数组
     * @return  Page<SystemNotificationModel> 系统通知领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    //@Log(actionName = "查询系统通知列表")
    public Page<SystemNotificationModel> getSystemNotificationList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds){
        SystemNotificationQuery systemNotificationQuery = new SystemNotificationQuery();
        systemNotificationQuery.setId(id);
        systemNotificationQuery.setExcludeIds(excludeIds);
        systemNotificationQuery.setIds(ids);
        return systemNotificationService.getSystemNotificationModelPage(limit, offset, systemNotificationQuery);
    }

    /**
     * 查询单个系统通知
     * @param   id  主键
     * @return  SystemNotificationModel   系统通知领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    //@Log(actionName = "查询系统通知")
    public SystemNotificationModel getSystemNotificationById(@PathVariable(value = "id") String id){
        return systemNotificationService.getOneSystemNotificationModel(id);
    }

    /**
     * 新增系统通知
     * @param   systemNotificationCreate  新增对象参数
     * @param   bindingResult   参数校验结果
     * @return  SystemNotificationModel   系统通知领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    //@Log(actionName = "新增系统通知")
    public SystemNotificationModel createSystemNotification(@RequestBody @Valid SystemNotificationCreate systemNotificationCreate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return systemNotificationService.createSystemNotification(systemNotificationCreate);
    }

    /**
     * 更新系统通知
     * @param id                            主键
     * @param systemNotificationUpdate 更新对象参数
     * @param bindingResult                 参数校验结果
     * @throws ArgumentValidException       参数校验异常类
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    //@Log(actionName = "更新系统通知")
    public void updateSystemNotification(@PathVariable(value = "id") String id,
                           @RequestBody @Valid SystemNotificationUpdate systemNotificationUpdate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        systemNotificationService.updateSystemNotification(systemNotificationUpdate);
    }

    /**
     *  删除系统通知
     * @param id    主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    //@Log(actionName = "删除单个系统通知")
    public void deleteSystemNotification(@PathVariable(value = "id") String id){
        systemNotificationService.deleteSystemNotification(id);
    }

    /**
     *  删除系统通知
     * @param ids    主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    //@Log(actionName = "批量删除系统通知")
    public void deleteSystemNotifications(@RequestParam(value = "ids") String[] ids){
        systemNotificationService.deleteSystemNotification(ids);
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
    public ModelAndView exportSystemNotification(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "ids", required = false) String[] ids,
            @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
            ModelMap modelMap) {
        SystemNotificationQuery systemNotificationQuery = new SystemNotificationQuery();
        systemNotificationQuery.setId(id);
        systemNotificationQuery.setExcludeIds(excludeIds);
        systemNotificationQuery.setIds(ids);
        List<SystemNotificationModel> systemNotificationList = systemNotificationService.getSystemNotificationModelList(systemNotificationQuery);
        modelMap.put(NormalExcelConstants.FILE_NAME, "系统通知信息");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, SystemNotificationModel.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, systemNotificationList);
        return new ModelAndView(NormalExcelConstants.JEECG_EXCEL_VIEW);
    }


}
