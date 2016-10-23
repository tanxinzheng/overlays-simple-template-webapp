package com.xmomen.module.notification.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.notification.model.NotificationCreate;
import com.xmomen.module.notification.model.NotificationModel;
import com.xmomen.module.notification.model.NotificationQuery;
import com.xmomen.module.notification.model.NotificationUpdate;
import com.xmomen.module.notification.service.NotificationService;
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
 * @date    2016-10-22 23:34:58
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    /**
     * 通知列表
     * @param   limit           每页结果数
     * @param   offset          页码
     * @param   id              主键
     * @param   ids             主键数组
     * @param   excludeIds      不包含主键数组
     * @return  Page<NotificationModel> 通知领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    //@Log(actionName = "查询通知列表")
    public Page<NotificationModel> getNotificationList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds){
        NotificationQuery notificationQuery = new NotificationQuery();
        notificationQuery.setId(id);
        notificationQuery.setExcludeIds(excludeIds);
        notificationQuery.setIds(ids);
        return notificationService.getNotificationModelPage(limit, offset, notificationQuery);
    }

    /**
     * 查询单个通知
     * @param   id  主键
     * @return  NotificationModel   通知领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    //@Log(actionName = "查询通知")
    public NotificationModel getNotificationById(@PathVariable(value = "id") String id){
        return notificationService.getOneNotificationModel(id);
    }

    /**
     * 新增通知
     * @param   notificationCreate  新增对象参数
     * @param   bindingResult   参数校验结果
     * @return  NotificationModel   通知领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    //@Log(actionName = "新增通知")
    public NotificationModel createNotification(@RequestBody @Valid NotificationCreate notificationCreate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return notificationService.createNotification(notificationCreate);
    }

    /**
     * 更新通知
     * @param id                            主键
     * @param notificationUpdate 更新对象参数
     * @param bindingResult                 参数校验结果
     * @throws ArgumentValidException       参数校验异常类
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    //@Log(actionName = "更新通知")
    public void updateNotification(@PathVariable(value = "id") String id,
                           @RequestBody @Valid NotificationUpdate notificationUpdate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        notificationService.updateNotification(notificationUpdate);
    }

    /**
     *  删除通知
     * @param id    主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    //@Log(actionName = "删除单个通知")
    public void deleteNotification(@PathVariable(value = "id") String id){
        notificationService.deleteNotification(id);
    }

    /**
     *  删除通知
     * @param ids    主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    //@Log(actionName = "批量删除通知")
    public void deleteNotifications(@RequestParam(value = "ids") String[] ids){
        notificationService.deleteNotification(ids);
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
    public ModelAndView exportNotification(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "ids", required = false) String[] ids,
            @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
            ModelMap modelMap) {
        NotificationQuery notificationQuery = new NotificationQuery();
        notificationQuery.setId(id);
        notificationQuery.setExcludeIds(excludeIds);
        notificationQuery.setIds(ids);
        List<NotificationModel> notificationList = notificationService.getNotificationModelList(notificationQuery);
        modelMap.put(NormalExcelConstants.FILE_NAME, "通知信息");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, NotificationModel.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, notificationList);
        return new ModelAndView(NormalExcelConstants.JEECG_EXCEL_VIEW);
    }


}
