package com.xmomen.module.attachment.controller;

import com.github.pagehelper.Page;
import com.xmomen.framework.logger.ActionLog;
import com.xmomen.framework.web.controller.BaseRestController;
import com.xmomen.module.attachment.model.AttachmentModel;
import com.xmomen.module.attachment.model.AttachmentQuery;
import com.xmomen.module.attachment.service.AttachmentService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author  tanxinzheng
 * @date    2017-8-6 15:56:07
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/attachment")
public class AttachmentController extends BaseRestController {

    @Autowired
    AttachmentService attachmentService;

    /**
     * 附件列表
     * @param   attachmentQuery    附件查询参数对象
     * @return  Page<AttachmentModel> 附件领域分页对象
     */
    @ApiOperation(value = "查询附件列表")
    @ActionLog(actionName = "查询附件列表")
    @PreAuthorize(value = "hasAnyAuthority('ATTACHMENT:VIEW')")
    @RequestMapping(method = RequestMethod.GET)
    public Page<AttachmentModel> getAttachmentList(AttachmentQuery attachmentQuery){
        return attachmentService.getAttachmentModelPage(attachmentQuery);
    }

    /**
     * 查询单个附件
     * @param   id  主键
     * @return  AttachmentModel   附件领域对象
     */
    @ApiOperation(value = "查询附件")
    @ActionLog(actionName = "查询附件")
    @PreAuthorize("hasAuthority('ATTACHMENT:VIEW')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AttachmentModel getAttachmentById(@PathVariable(value = "id") String id){
        return attachmentService.getOneAttachmentModel(id);
    }

    /**
     * 新增附件
     * @param   attachmentModel  新增对象参数
     * @return  AttachmentModel   附件领域对象
     */
    @ApiOperation(value = "新增附件")
    @ActionLog(actionName = "新增附件")
    @PreAuthorize("hasAuthority('PERMISSION:CREATE')")
    @RequestMapping(method = RequestMethod.POST)
    public AttachmentModel createAttachment(@RequestBody @Valid AttachmentModel attachmentModel) {
        return attachmentService.createAttachment(attachmentModel);
    }

    /**
     * 更新附件
     * @param id    主键
     * @param attachmentModel  更新对象参数
     * @return  AttachmentModel   附件领域对象
     */
    @ApiOperation(value = "更新附件")
    @ActionLog(actionName = "更新附件")
    @PreAuthorize("hasAuthority('PERMISSION:UPDATE')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public AttachmentModel updateAttachment(@PathVariable(value = "id") String id,
                           @RequestBody @Valid AttachmentModel attachmentModel){
        if(StringUtils.isNotBlank(id)){
            attachmentModel.setId(id);
        }
        attachmentService.updateAttachment(attachmentModel);
        return attachmentService.getOneAttachmentModel(id);
    }

    /**
     *  删除附件
     * @param id    主键
     */
    @ApiOperation(value = "删除单个附件")
    @ActionLog(actionName = "删除单个附件")
    @PreAuthorize("hasAuthority('PERMISSION:DELETE')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAttachment(@PathVariable(value = "id") String id){
        attachmentService.deleteAttachment(id);
    }

    /**
     *  删除附件
     * @param attachmentQuery    查询参数对象
     */
    @ApiOperation(value = "批量删除附件")
    @ActionLog(actionName = "批量删除附件")
    @PreAuthorize("hasAuthority('PERMISSION:DELETE')")
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAttachments(AttachmentQuery attachmentQuery){
        attachmentService.deleteAttachment(attachmentQuery.getIds());
    }


}
