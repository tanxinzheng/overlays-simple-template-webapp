package com.xmomen.module.attachment.mapper;

import com.xmomen.module.attachment.model.Attachment;
import com.xmomen.module.attachment.model.AttachmentModel;
import com.xmomen.module.attachment.model.AttachmentQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-6 15:56:07
 * @version 1.0.0
 */
public interface AttachmentMapper {

    List<Attachment> select(AttachmentQuery attachmentQuery);

    List<AttachmentModel> selectModel(AttachmentQuery attachmentQuery);

    Attachment selectByPrimaryKey(String primaryKey);

    AttachmentModel selectModelByPrimaryKey(String primaryKey);

    int deleteByPrimaryKey(String primaryKey);

    int deletesByPrimaryKey(@Param("ids") List<String> primaryKeys);

    int insertSelective(Attachment record);

    int updateSelective(Attachment record);

    int updateSelectiveByQuery(@Param("record") Attachment record, @Param("query") AttachmentQuery example);
}
