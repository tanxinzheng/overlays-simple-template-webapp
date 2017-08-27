package com.xmomen.module.notification.controller;

import io.swagger.annotations.ApiOperation;
import com.github.pagehelper.Page;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.module.notification.model.NotificationTemplateQuery;
import com.xmomen.module.notification.model.NotificationTemplateModel;
import com.xmomen.module.notification.service.NotificationTemplateService;
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
@RequestMapping(value = "/notification_template")
public class NotificationTemplateController extends BaseRestController {

    private static Logger logger = LoggerFactory.getLogger(NotificationTemplateController.class);

    @Autowired
    NotificationTemplateService notificationTemplateService;

    /**
     * 通知模板列表
     * @param   notificationTemplateQuery    通知模板查询参数对象
     * @return  Page<NotificationTemplateModel> 通知模板领域分页对象
     */
    @ApiOperation(value = "查询通知模板列表")
    @ActionLog(actionName = "查询通知模板列表")
    @PreAuthorize("hasAuthority('NOTIFICATIONTEMPLATE:VIEW')")
    @RequestMapping(method = RequestMethod.GET)
    public Page<NotificationTemplateModel> getNotificationTemplateList(NotificationTemplateQuery notificationTemplateQuery){
        return notificationTemplateService.getNotificationTemplateModelPage(notificationTemplateQuery);
    }

    /**
     * 查询单个通知模板
     * @param   id  主键
     * @return  NotificationTemplateModel   通知模板领域对象
     */
    @ApiOperation(value = "查询通知模板")
    @ActionLog(actionName = "查询通知模板")
    @PreAuthorize("hasAuthority('NOTIFICATIONTEMPLATE:VIEW')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public NotificationTemplateModel getNotificationTemplateById(@PathVariable(value = "id") String id){
        return notificationTemplateService.getOneNotificationTemplateModel(id);
    }

    /**
     * 新增通知模板
     * @param   notificationTemplateModel  新增对象参数
     * @return  NotificationTemplateModel   通知模板领域对象
     */
    @ApiOperation(value = "新增通知模板")
    @ActionLog(actionName = "新增通知模板")
    @PreAuthorize("hasAuthority('NOTIFICATIONTEMPLATE:CREATE')")
    @RequestMapping(method = RequestMethod.POST)
    public NotificationTemplateModel createNotificationTemplate(@RequestBody @Valid NotificationTemplateModel notificationTemplateModel) {
        return notificationTemplateService.createNotificationTemplate(notificationTemplateModel);
    }

    /**
     * 更新通知模板
     * @param id    主键
     * @param notificationTemplateModel  更新对象参数
     * @return  NotificationTemplateModel   通知模板领域对象
     */
    @ApiOperation(value = "更新通知模板")
    @ActionLog(actionName = "更新通知模板")
    @PreAuthorize("hasAuthority('NOTIFICATIONTEMPLATE:UPDATE')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public NotificationTemplateModel updateNotificationTemplate(@PathVariable(value = "id") String id,
                           @RequestBody @Valid NotificationTemplateModel notificationTemplateModel){
        if(StringUtils.isNotBlank(id)){
            notificationTemplateModel.setId(id);
        }
        notificationTemplateService.updateNotificationTemplate(notificationTemplateModel);
        return notificationTemplateService.getOneNotificationTemplateModel(id);
    }

    /**
     *  删除通知模板
     * @param id    主键
     */
    @ApiOperation(value = "删除单个通知模板")
    @ActionLog(actionName = "删除单个通知模板")
    @PreAuthorize("hasAuthority('NOTIFICATIONTEMPLATE:DELETE')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteNotificationTemplate(@PathVariable(value = "id") String id){
        notificationTemplateService.deleteNotificationTemplate(id);
    }

    /**
     *  删除通知模板
     * @param notificationTemplateQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除通知模板")
    @ActionLog(actionName = "批量删除通知模板")
    @PreAuthorize("hasAuthority('NOTIFICATIONTEMPLATE:DELETE')")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteNotificationTemplates(NotificationTemplateQuery notificationTemplateQuery){
        notificationTemplateService.deleteNotificationTemplate(notificationTemplateQuery.getIds());
    }


}
