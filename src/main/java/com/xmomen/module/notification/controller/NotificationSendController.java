package com.xmomen.module.notification.controller;

import com.github.pagehelper.Page;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.module.notification.model.NotificationSend;
import com.xmomen.module.notification.model.NotificationSendModel;
import com.xmomen.module.notification.model.NotificationSendQuery;
import com.xmomen.module.notification.service.NotificationSendService;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "/notification_send")
public class NotificationSendController extends BaseRestController {

    private static Logger logger = LoggerFactory.getLogger(NotificationSendController.class);

    @Autowired
    NotificationSendService notificationSendService;

    /**
     * 通知发送人列表
     * @param   notificationSendQuery    通知发送人查询参数对象
     * @return  Page<NotificationSendModel> 通知发送人领域分页对象
     */
    @ApiOperation(value = "查询通知发送人列表")
    @ActionLog(actionName = "查询通知发送人列表")
    @PreAuthorize("hasAuthority('NOTIFICATIONSEND:VIEW')")
    @RequestMapping(method = RequestMethod.GET)
    public Page<NotificationSendModel> getNotificationSendList(NotificationSendQuery notificationSendQuery){
        return notificationSendService.getNotificationSendModelPage(notificationSendQuery);
    }

    /**
     * 查询单个通知发送人
     * @param   id  主键
     * @return  NotificationSendModel   通知发送人领域对象
     */
    @ApiOperation(value = "查询通知发送人")
    @ActionLog(actionName = "查询通知发送人")
    @PreAuthorize("hasAuthority('NOTIFICATIONSEND:VIEW')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public NotificationSendModel getNotificationSendById(@PathVariable(value = "id") String id){
        return notificationSendService.getOneNotificationSendModel(id);
    }

    /**
     * 新增通知发送人
     * @param   notificationSendModel  新增对象参数
     * @return  NotificationSendModel   通知发送人领域对象
     */
    @ApiOperation(value = "新增通知发送人")
    @ActionLog(actionName = "新增通知发送人")
    @PreAuthorize("hasAuthority('NOTIFICATIONSEND:CREATE')")
    @RequestMapping(method = RequestMethod.POST)
    public NotificationSend createNotificationSend(@RequestBody @Valid NotificationSendModel notificationSendModel) {
        notificationSendModel.setSender(getCurrentUserId());
        return notificationSendService.createNotificationSend(notificationSendModel.getEntity());
    }

    /**
     * 更新通知发送人
     * @param id    主键
     * @param notificationSendModel  更新对象参数
     * @return  NotificationSendModel   通知发送人领域对象
     */
    @ApiOperation(value = "更新通知发送人")
    @ActionLog(actionName = "更新通知发送人")
    @PreAuthorize("hasAuthority('NOTIFICATIONSEND:UPDATE')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public NotificationSendModel updateNotificationSend(@PathVariable(value = "id") String id,
                           @RequestBody @Valid NotificationSendModel notificationSendModel){
        if(StringUtils.isNotBlank(id)){
            notificationSendModel.setId(id);
        }
        notificationSendService.updateNotificationSend(notificationSendModel);
        return notificationSendService.getOneNotificationSendModel(id);
    }

    /**
     *  删除通知发送人
     * @param id    主键
     */
    @ApiOperation(value = "删除单个通知发送人")
    @ActionLog(actionName = "删除单个通知发送人")
    @PreAuthorize("hasAuthority('NOTIFICATIONSEND:DELETE')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteNotificationSend(@PathVariable(value = "id") String id){
        notificationSendService.deleteNotificationSend(id);
    }

    /**
     *  删除通知发送人
     * @param notificationSendQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除通知发送人")
    @ActionLog(actionName = "批量删除通知发送人")
    @PreAuthorize("hasAuthority('NOTIFICATIONSEND:DELETE')")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteNotificationSends(NotificationSendQuery notificationSendQuery){
        notificationSendService.deleteNotificationSend(notificationSendQuery.getIds());
    }

}
