package com.xmomen.module.notification.controller;

import com.github.pagehelper.Page;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.module.notification.model.Notification;
import com.xmomen.module.notification.model.NotificationModel;
import com.xmomen.module.notification.model.NotificationQuery;
import com.xmomen.module.notification.model.SendNotification;
import com.xmomen.module.notification.service.NotificationService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/notification")
public class NotificationController extends BaseRestController {

    private static Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    NotificationService notificationService;

    /**
     * 通知列表
     * @param   notificationQuery    通知查询参数对象
     * @return  Page<NotificationModel> 通知领域分页对象
     */
    @ApiOperation(value = "查询通知列表")
    @ActionLog(actionName = "查询通知列表")
    @PreAuthorize("hasAuthority('NOTIFICATION:VIEW')")
    @RequestMapping(method = RequestMethod.GET)
    public Page<NotificationModel> getNotificationList(NotificationQuery notificationQuery){
        return notificationService.getNotificationModelPage(notificationQuery);
    }

    /**
     * 查询单个通知
     * @param   id  主键
     * @return  NotificationModel   通知领域对象
     */
    @ApiOperation(value = "查询通知")
    @ActionLog(actionName = "查询通知")
    @PreAuthorize("hasAuthority('NOTIFICATION:VIEW')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public NotificationModel getNotificationById(@PathVariable(value = "id") String id){
        return notificationService.getOneNotificationModel(id);
    }

    /**
     * 新增通知
     * @param   notificationModel  新增对象参数
     * @return  NotificationModel   通知领域对象
     */
    @ApiOperation(value = "新增通知")
    @ActionLog(actionName = "新增通知")
    @PreAuthorize("hasAuthority('NOTIFICATION:CREATE')")
    @RequestMapping(method = RequestMethod.POST)
    public void createNotification(@RequestBody @Valid SendNotification sendNotification) {
        notificationService.send(sendNotification);
    }

    /**
     * 更新通知
     * @param id    主键
     * @param notificationModel  更新对象参数
     * @return  NotificationModel   通知领域对象
     */
    @ApiOperation(value = "更新通知")
    @ActionLog(actionName = "更新通知")
    @PreAuthorize("hasAuthority('NOTIFICATION:UPDATE')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public NotificationModel updateNotification(@PathVariable(value = "id") String id,
                           @RequestBody @Valid NotificationModel notificationModel){
        if(StringUtils.isNotBlank(id)){
            notificationModel.setId(id);
        }
        notificationService.updateNotification(notificationModel);
        return notificationService.getOneNotificationModel(id);
    }

    /**
     * 已读消息
     * @param id
     */
    @ApiOperation(value = "更新通知")
    @PreAuthorize("hasAuthority('NOTIFICATION:UPDATE')")
    @RequestMapping(value = "/{id}/read", method = RequestMethod.PUT)
    public void readNotification(@PathVariable(value = "id") String id){
        notificationService.read(id, getCurrentUserId());
    }

    /**
     *  删除通知
     * @param id    主键
     */
    @ApiOperation(value = "删除单个通知")
    @ActionLog(actionName = "删除单个通知")
    @PreAuthorize("hasAuthority('NOTIFICATION:DELETE')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteNotification(@PathVariable(value = "id") String id){
        notificationService.deleteNotification(id);
    }

    /**
     *  删除通知
     * @param notificationQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除通知")
    @ActionLog(actionName = "批量删除通知")
    @PreAuthorize("hasAuthority('NOTIFICATION:DELETE')")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteNotifications(NotificationQuery notificationQuery){
        if(notificationQuery == null || ArrayUtils.isEmpty(notificationQuery.getIds())){
            return;
        }
        notificationService.deleteNotification(notificationQuery.getIds());
    }


}
