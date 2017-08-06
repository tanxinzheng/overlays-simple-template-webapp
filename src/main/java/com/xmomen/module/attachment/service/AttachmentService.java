package com.xmomen.module.attachment.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.attachment.model.AttachmentQuery;
import com.xmomen.module.attachment.model.AttachmentModel;
import com.xmomen.module.attachment.model.Attachment;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-6 15:56:07
 * @version 1.0.0
 */
public interface AttachmentService {

    /**
     * 新增附件
     * @param  attachmentModel   新增附件对象参数
     * @return  AttachmentModel    附件领域对象
     */
    public AttachmentModel createAttachment(AttachmentModel attachmentModel);

    /**
     * 新增附件实体对象
     * @param   attachment 新增附件实体对象参数
     * @return  Attachment 附件实体对象
     */
    public Attachment createAttachment(Attachment attachment);

    /**
    * 批量新增附件
    * @param attachmentModels     新增附件对象集合参数
    * @return List<AttachmentModel>    附件领域对象集合
    */
    List<AttachmentModel> createAttachments(List<AttachmentModel> attachmentModels);

    /**
    * 更新附件
    *
    * @param attachmentModel 更新附件对象参数
    * @param attachmentQuery 过滤附件对象参数
    */
    public void updateAttachment(AttachmentModel attachmentModel, AttachmentQuery attachmentQuery);

    /**
     * 更新附件
     * @param attachmentModel    更新附件对象参数
     */
    public void updateAttachment(AttachmentModel attachmentModel);

    /**
     * 更新附件实体对象
     * @param   attachment 新增附件实体对象参数
     * @return  Attachment 附件实体对象
     */
    public void updateAttachment(Attachment attachment);

    /**
     * 批量删除附件
     * @param ids   主键数组
     */
    public void deleteAttachment(String[] ids);

    /**
     * 删除附件
     * @param id   主键
     */
    public void deleteAttachment(String id);

    /**
     * 通过附件key删除附件
     * @param attachmentKey
     */
    public void deleteAttachmentByKey(String attachmentKey);

    /**
     * 查询附件领域分页对象（带参数条件）
     * @param attachmentQuery 查询参数
     * @return Page<AttachmentModel>   附件参数对象
     */
    public Page<AttachmentModel> getAttachmentModelPage(AttachmentQuery attachmentQuery);

    /**
     * 查询附件领域集合对象（带参数条件）
     * @param attachmentQuery 查询参数对象
     * @return List<AttachmentModel> 附件领域集合对象
     */
    public List<AttachmentModel> getAttachmentModelList(AttachmentQuery attachmentQuery);

    /**
     * 查询附件实体对象
     * @param id 主键
     * @return Attachment 附件实体对象
     */
    public Attachment getOneAttachment(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return AttachmentModel 附件领域对象
     */
    public AttachmentModel getOneAttachmentModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param attachmentQuery 附件查询参数对象
     * @return AttachmentModel 附件领域对象
     */
    public AttachmentModel getOneAttachmentModel(AttachmentQuery attachmentQuery) throws TooManyResultsException;

    /**
     * 缓存查询附件实体对象
     * @param attachmentKey 文件Key
     * @return Attachment 附件实体对象
     */
    public AttachmentModel getOneAttachmentModelCache(String attachmentKey);

}
