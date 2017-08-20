package com.xmomen.framework.web.json;

import com.xmomen.module.attachment.model.AttachmentModel;
import com.xmomen.module.attachment.service.AttachmentService;
import com.xmomen.module.authorization.model.User;
import com.xmomen.module.authorization.service.UserService;
import com.xmomen.module.system.model.DictionaryModel;
import com.xmomen.module.system.model.DictionaryQuery;
import com.xmomen.module.system.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by tanxinzheng on 16/10/20.
 */
@Component
public class DefaultDictionaryInterpreterService implements DictionaryInterpreterService {

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    UserService userService;
    @Autowired
    AttachmentService attachmentService;

    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.bucketName}")
    private String bucketName;

    /**
     * 翻译
     *
     * @param dictionaryType 字典类型
     * @param dictionaryCode 字典代码
     * @return
     */
    @Override
    public String translate(DictionaryIndex dictionaryType, String dictionaryCode) {
        if(dictionaryType.equals(DictionaryIndex.USER_ID)){
            User user = userService.getOneUserCache(dictionaryCode);
            if(user == null){
                return null;
            }
            return user.getNickname();
        }else if(dictionaryType.equals(DictionaryIndex.ATTACHMENT_KEY)){
            AttachmentModel attachmentModel = attachmentService.getOneAttachmentModelCache(dictionaryCode);
            if(attachmentModel == null){
                return null;
            }
            String fileUrl = File.separator + File.separator +
                    bucketName + "." +
                    endpoint + File.separator +
                    attachmentModel.getAttachmentPath() + File.separator +
                    attachmentModel.getAttachmentKey();
            return fileUrl;
        }
        DictionaryQuery dictionaryQuery = new DictionaryQuery();
        dictionaryQuery.setCode(dictionaryCode);
        dictionaryQuery.setType(dictionaryType.name());
        DictionaryModel dictionaryModel = dictionaryService.getOneDictionaryModel(dictionaryQuery);
        if(dictionaryModel != null){
            return dictionaryModel.getDictionaryName();
        }
        return null;
    }
}
