package com.xmomen.module.notification.service.impl;

import com.github.pagehelper.Page;
import com.xmomen.framework.exception.BusinessException;
import com.xmomen.framework.mybatis.page.PageInterceptor;
import com.xmomen.framework.web.json.DictionaryIndex;
import com.xmomen.framework.web.json.DictionaryInterpreterService;
import com.xmomen.module.core.model.SelectIndex;
import com.xmomen.module.core.model.SelectOptionModel;
import com.xmomen.module.core.model.SelectOptionQuery;
import com.xmomen.module.core.service.SelectService;
import com.xmomen.module.notification.mapper.NotificationTemplateMapper;
import com.xmomen.module.notification.model.NotificationTemplate;
import com.xmomen.module.notification.model.NotificationTemplateModel;
import com.xmomen.module.notification.model.NotificationTemplateQuery;
import com.xmomen.module.notification.service.NotificationTemplateService;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-8-24 17:42:48
 * @version 1.0.0
 */
@Service
public class NotificationTemplateServiceImpl implements NotificationTemplateService, DictionaryInterpreterService, SelectService {

    @Autowired
    NotificationTemplateMapper notificationTemplateMapper;

    /**
     * 新增通知模板
     *
     * @param notificationTemplateModel 新增通知模板对象参数
     * @return NotificationTemplateModel    通知模板领域对象
     */
    @Override
    @Transactional
    public NotificationTemplateModel createNotificationTemplate(NotificationTemplateModel notificationTemplateModel) {
        NotificationTemplate notificationTemplate = createNotificationTemplate(notificationTemplateModel.getEntity());
        if(notificationTemplate != null){
            return getOneNotificationTemplateModel(notificationTemplate.getId());
        }
        return null;
    }

    /**
     * 新增通知模板实体对象
     *
     * @param notificationTemplate 新增通知模板实体对象参数
     * @return NotificationTemplate 通知模板实体对象
     */
    @Override
    @Transactional
    public NotificationTemplate createNotificationTemplate(NotificationTemplate notificationTemplate) {
        notificationTemplateMapper.insertSelective(notificationTemplate);
        return notificationTemplate;
    }

    /**
    * 批量新增通知模板
    *
    * @param notificationTemplateModels 新增通知模板对象集合参数
    * @return List<NotificationTemplateModel>    通知模板领域对象集合
    */
    @Override
    @Transactional
    public List<NotificationTemplateModel> createNotificationTemplates(List<NotificationTemplateModel> notificationTemplateModels) {
        List<NotificationTemplateModel> notificationTemplateModelList = null;
        for (NotificationTemplateModel notificationTemplateModel : notificationTemplateModels) {
            notificationTemplateModel = createNotificationTemplate(notificationTemplateModel);
            if(notificationTemplateModel != null){
                if(notificationTemplateModelList == null){
                    notificationTemplateModelList = new ArrayList<>();
                }
                notificationTemplateModelList.add(notificationTemplateModel);
            }
        }
        return notificationTemplateModelList;
    }

    /**
    * 更新通知模板
    *
    * @param notificationTemplateModel 更新通知模板对象参数
    * @param notificationTemplateQuery 过滤通知模板对象参数
    */
    @Override
    @Transactional
    public void updateNotificationTemplate(NotificationTemplateModel notificationTemplateModel, NotificationTemplateQuery notificationTemplateQuery) {
        notificationTemplateMapper.updateSelectiveByQuery(notificationTemplateModel.getEntity(), notificationTemplateQuery);
    }

    /**
     * 更新通知模板
     *
     * @param notificationTemplateModel 更新通知模板对象参数
     */
    @Override
    @Transactional
    public void updateNotificationTemplate(NotificationTemplateModel notificationTemplateModel) {
        updateNotificationTemplate(notificationTemplateModel.getEntity());
    }

    /**
     * 更新通知模板实体对象
     *
     * @param notificationTemplate 新增通知模板实体对象参数
     * @return NotificationTemplate 通知模板实体对象
     */
    @Override
    @Transactional
    public void updateNotificationTemplate(NotificationTemplate notificationTemplate) {
        notificationTemplateMapper.updateSelective(notificationTemplate);
    }

    /**
     * 删除通知模板
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteNotificationTemplate(String[] ids) {
        notificationTemplateMapper.deletesByPrimaryKey(Arrays.asList(ids));
    }

    /**
    * 删除通知模板
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void deleteNotificationTemplate(String id) {
        notificationTemplateMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询通知模板领域分页对象（带参数条件）
     *
     * @param notificationTemplateQuery 查询参数
     * @return Page<NotificationTemplateModel>   通知模板参数对象
     */
    @Override
    public Page<NotificationTemplateModel> getNotificationTemplateModelPage(NotificationTemplateQuery notificationTemplateQuery) {
        PageInterceptor.startPage(notificationTemplateQuery);
        notificationTemplateMapper.selectModel(notificationTemplateQuery);
        return PageInterceptor.endPage();
    }

    /**
     * 查询通知模板领域集合对象（带参数条件）
     *
     * @param notificationTemplateQuery 查询参数对象
     * @return List<NotificationTemplateModel> 通知模板领域集合对象
     */
    @Override
    public List<NotificationTemplateModel> getNotificationTemplateModelList(NotificationTemplateQuery notificationTemplateQuery) {
        return notificationTemplateMapper.selectModel(notificationTemplateQuery);
    }

    /**
     * 查询通知模板实体对象
     *
     * @param id 主键
     * @return NotificationTemplate 通知模板实体对象
     */
    @Override
    public NotificationTemplate getOneNotificationTemplate(String id) {
        return notificationTemplateMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return NotificationTemplateModel 通知模板领域对象
     */
    @Override
    public NotificationTemplateModel getOneNotificationTemplateModel(String id) {
        return notificationTemplateMapper.selectModelByPrimaryKey(id);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param notificationTemplateQuery 通知模板查询参数对象
     * @return NotificationTemplateModel 通知模板领域对象
     */
    @Override
    public NotificationTemplateModel getOneNotificationTemplateModel(NotificationTemplateQuery notificationTemplateQuery) {
        List<NotificationTemplateModel> notificationTemplateModelList = notificationTemplateMapper.selectModel(notificationTemplateQuery);
        if(CollectionUtils.isEmpty(notificationTemplateModelList)){
            return null;
        }
        if(notificationTemplateModelList.size() > 1){
            throw new BusinessException();
        }
        return notificationTemplateModelList.get(0);
    }

    /**
     * 翻译
     *
     * @param dictionaryType 字典类型
     * @param dictionaryCode 字典代码
     * @return
     */
    @Override
    public Object translateDictionary(DictionaryIndex dictionaryType, String dictionaryCode) {
        NotificationTemplateQuery notificationTemplateQuery = new NotificationTemplateQuery();
        notificationTemplateQuery.setTemplateCode(dictionaryCode);
        NotificationTemplateModel notificationTemplateModel = getOneNotificationTemplateModel(notificationTemplateQuery);
        if(notificationTemplateModel == null){
            return null;
        }
        return notificationTemplateModel.getTemplateName();
    }

    /**
     * 字典索引
     *
     * @return
     */
    @Override
    public DictionaryIndex getDictionaryIndex() {
        return DictionaryIndex.NOTIFICATION_TEMPLATE;
    }

    /**
     * 查询option数据
     *
     * @param selectOptionQuery
     * @return
     */
    @Override
    public List<SelectOptionModel> selectOptionModels(SelectOptionQuery selectOptionQuery) {
        NotificationTemplateQuery notificationTemplateQuery = new NotificationTemplateQuery();
        notificationTemplateQuery.setActive(Boolean.TRUE);
        List<NotificationTemplateModel> notificationTemplateModelList = getNotificationTemplateModelList(notificationTemplateQuery);
        if(CollectionUtils.isEmpty(notificationTemplateModelList)){
            return Lists.newArrayList();
        }
        List<SelectOptionModel> selectOptionModelList = Lists.newArrayList();
        int i = 0;
        for (NotificationTemplateModel notificationTemplateModel : notificationTemplateModelList) {
            SelectOptionModel selectOptionModel = new SelectOptionModel(
                    SelectIndex.NOTIFICATION_TEMPLATE.name(),
                    "通知模板",
                    notificationTemplateModel.getTemplateCode(),
                    notificationTemplateModel.getTemplateName(),
                    i++
            );
            selectOptionModelList.add(selectOptionModel);
        }
        return selectOptionModelList;
    }

    /**
     * 获取select index
     *
     * @return
     */
    @Override
    public SelectIndex getSelectIndex() {
        return SelectIndex.NOTIFICATION_TEMPLATE;
    }
}
