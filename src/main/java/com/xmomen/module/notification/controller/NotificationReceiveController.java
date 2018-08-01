package com.xmomen.module.notification.controller;

import io.swagger.annotations.ApiOperation;
import com.github.pagehelper.Page;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.module.notification.model.NotificationReceiveQuery;
import com.xmomen.module.notification.model.NotificationReceiveModel;
import com.xmomen.module.notification.service.NotificationReceiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.apache.commons.lang3.StringUtils;
import javax.validation.Valid;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/notification_receive")
public class NotificationReceiveController extends BaseRestController {

    private static Logger logger = LoggerFactory.getLogger(NotificationReceiveController.class);

    @Autowired
    NotificationReceiveService notificationReceiveService;

    /**
     * 通知接收人列表
     * @param   notificationReceiveQuery    通知接收人查询参数对象
     * @return  Page<NotificationReceiveModel> 通知接收人领域分页对象
     */
    @ApiOperation(value = "查询通知接收人列表")
    @ActionLog(actionName = "查询通知接收人列表")
    @PreAuthorize("hasAuthority('NOTIFICATIONRECEIVE:VIEW')")
    @RequestMapping(method = RequestMethod.GET)
    public Page<NotificationReceiveModel> getNotificationReceiveList(NotificationReceiveQuery notificationReceiveQuery){
        return notificationReceiveService.getNotificationReceiveModelPage(notificationReceiveQuery);
    }

    /**
     * 查询单个通知接收人
     * @param   id  主键
     * @return  NotificationReceiveModel   通知接收人领域对象
     */
    @ApiOperation(value = "查询通知接收人")
    @ActionLog(actionName = "查询通知接收人")
    @PreAuthorize("hasAuthority('NOTIFICATIONRECEIVE:VIEW')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public NotificationReceiveModel getNotificationReceiveById(@PathVariable(value = "id") String id){
        return notificationReceiveService.getOneNotificationReceiveModel(id);
    }

    /**
     * 新增通知接收人
     * @param   notificationReceiveModel  新增对象参数
     * @return  NotificationReceiveModel   通知接收人领域对象
     */
    @ApiOperation(value = "新增通知接收人")
    @ActionLog(actionName = "新增通知接收人")
    @PreAuthorize("hasAuthority('NOTIFICATIONRECEIVE:CREATE')")
    @RequestMapping(method = RequestMethod.POST)
    public NotificationReceiveModel createNotificationReceive(@RequestBody @Valid NotificationReceiveModel notificationReceiveModel) {
        return notificationReceiveService.createNotificationReceive(notificationReceiveModel);
    }

    /**
     * 更新通知接收人
     * @param id    主键
     * @param notificationReceiveModel  更新对象参数
     * @return  NotificationReceiveModel   通知接收人领域对象
     */
    @ApiOperation(value = "更新通知接收人")
    @ActionLog(actionName = "更新通知接收人")
    @PreAuthorize("hasAuthority('NOTIFICATIONRECEIVE:UPDATE')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public NotificationReceiveModel updateNotificationReceive(@PathVariable(value = "id") String id,
                           @RequestBody @Valid NotificationReceiveModel notificationReceiveModel){
        if(StringUtils.isNotBlank(id)){
            notificationReceiveModel.setId(id);
        }
        notificationReceiveService.updateNotificationReceive(notificationReceiveModel);
        return notificationReceiveService.getOneNotificationReceiveModel(id);
    }

    /**
     *  删除通知接收人
     * @param id    主键
     */
    @ApiOperation(value = "删除单个通知接收人")
    @ActionLog(actionName = "删除单个通知接收人")
    @PreAuthorize("hasAuthority('NOTIFICATIONRECEIVE:DELETE')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteNotificationReceive(@PathVariable(value = "id") String id){
        notificationReceiveService.deleteNotificationReceive(id);
    }

    /**
     *  删除通知接收人
     * @param notificationReceiveQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除通知接收人")
    @ActionLog(actionName = "批量删除通知接收人")
    @PreAuthorize("hasAuthority('NOTIFICATIONRECEIVE:DELETE')")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteNotificationReceives(NotificationReceiveQuery notificationReceiveQuery){
        notificationReceiveService.deleteNotificationReceive(notificationReceiveQuery.getIds());
    }


}
