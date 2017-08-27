package com.xmomen.module.core.service.select;

import com.xmomen.framework.web.json.DictionaryIndex;
import com.xmomen.module.authorization.model.GroupModel;
import com.xmomen.module.authorization.model.GroupQuery;
import com.xmomen.module.authorization.model.UserModel;
import com.xmomen.module.authorization.model.UserQuery;
import com.xmomen.module.authorization.service.GroupService;
import com.xmomen.module.authorization.service.UserService;
import com.xmomen.module.core.model.SelectOptionModel;
import com.xmomen.module.core.model.SelectOptionQuery;
import com.xmomen.module.core.service.SelectService;
import com.xmomen.module.notification.model.NotificationTemplateModel;
import com.xmomen.module.notification.model.NotificationTemplateQuery;
import com.xmomen.module.notification.service.NotificationTemplateService;
import com.xmomen.module.system.model.DictionaryModel;
import com.xmomen.module.system.model.DictionaryQuery;
import com.xmomen.module.system.service.DictionaryService;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanxinzheng on 17/6/28.
 */
@Service
public class DictionarySelectService implements SelectService {

    @Autowired
    DictionaryService dictionaryService;
    @Autowired
    NotificationTemplateService notificationTemplateService;
    @Autowired
    UserService userService;
    @Autowired
    GroupService groupService;

    @Cacheable(cacheNames = DictionaryIndex.DICTIONARY_CACHE_NAME_KEY)
    @Override
    public List<SelectOptionModel> selectOptionModels(SelectOptionQuery selectOptionQuery) {
        Assert.notNull(selectOptionQuery);
        Assert.notNull(selectOptionQuery.getTypeCode(), "typeCode不能为空");
        List<SelectOptionModel> selectOptionModelList = new ArrayList<>();
        if(DictionaryIndex.NOTIFICATION_TEMPLATE.name().equals(selectOptionQuery.getTypeCode())){
            NotificationTemplateQuery notificationTemplateQuery = new NotificationTemplateQuery();
            notificationTemplateQuery.setActive(true);
            List<NotificationTemplateModel> notificationTemplateModelList = notificationTemplateService.getNotificationTemplateModelList(notificationTemplateQuery);
            if(CollectionUtils.isEmpty(notificationTemplateModelList)){
               return null;
            }
            int i = 0;
            for (NotificationTemplateModel notificationTemplateModel : notificationTemplateModelList) {
                SelectOptionModel selectOptionModel = new SelectOptionModel(
                        DictionaryIndex.NOTIFICATION_TEMPLATE.name(),
                        "通知模板",
                        notificationTemplateModel.getTemplateCode(),
                        notificationTemplateModel.getTemplateName(),
                        i++
                );
                selectOptionModelList.add(selectOptionModel);
            }
            return selectOptionModelList;
        }else if(DictionaryIndex.USER_ID.name().equals(selectOptionQuery.getTypeCode())){
            UserQuery userQuery = new UserQuery();
            List<UserModel> userModelList = userService.getUserModelList(userQuery);
            if(CollectionUtils.isEmpty(userModelList)){
                return null;
            }
            int i = 0;
            for (UserModel userModel : userModelList) {
                SelectOptionModel selectOptionModel = new SelectOptionModel(
                        DictionaryIndex.USER_ID.name(),
                        "用户",
                        userModel.getId(),
                        userModel.getNickname(),
                        i++
                );
                selectOptionModelList.add(selectOptionModel);
            }
            return selectOptionModelList;
        }else if(DictionaryIndex.GROUP_ID.name().equals(selectOptionQuery.getTypeCode())){
            GroupQuery groupQuery = new GroupQuery();
            List<GroupModel> groupModelList = groupService.getGroupModelList(groupQuery);
            if(CollectionUtils.isEmpty(groupModelList)){
                return null;
            }
            int i = 0;
            for (GroupModel groupModel : groupModelList) {
                SelectOptionModel selectOptionModel = new SelectOptionModel(
                        DictionaryIndex.GROUP_ID.name(),
                        "用户组",
                        groupModel.getId(),
                        groupModel.getGroupName(),
                        i++
                );
                selectOptionModelList.add(selectOptionModel);
            }
            return selectOptionModelList;
        }else{
            DictionaryQuery dictionaryQuery = new DictionaryQuery();
            dictionaryQuery.setType(selectOptionQuery.getTypeCode());
            dictionaryQuery.setParentId(selectOptionQuery.getParentId());
            List<DictionaryModel> dictionaryModels = dictionaryService.getDictionaryModelList(dictionaryQuery);
            if(CollectionUtils.isNotEmpty(dictionaryModels)){
                for (DictionaryModel dictionaryModel : dictionaryModels) {
                    SelectOptionModel selectOptionModel = new SelectOptionModel(
                            dictionaryModel.getGroupCode(),
                            dictionaryModel.getGroupName(),
                            dictionaryModel.getDictionaryCode(),
                            dictionaryModel.getDictionaryName(),
                            dictionaryModel.getSort()
                            );
                    selectOptionModelList.add(selectOptionModel);
                }
                return selectOptionModelList;
            }
        }
        return null;
    }
}
