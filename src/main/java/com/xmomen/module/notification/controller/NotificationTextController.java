package com.xmomen.module.notification.controller;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.notification.model.NotificationTextCreate;
import com.xmomen.module.notification.model.NotificationTextModel;
import com.xmomen.module.notification.model.NotificationTextQuery;
import com.xmomen.module.notification.model.NotificationTextUpdate;
import com.xmomen.module.notification.service.NotificationTextService;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

//import com.xmomen.module.logger.Log;

/**
 * @author  tanxinzheng
 * @date    2016-10-22 23:34:58
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/notificationText")
public class NotificationTextController {

    @Autowired
    NotificationTextService notificationTextService;

    /**
     * 通知内容列表
     * @param   limit           每页结果数
     * @param   offset          页码
     * @param   id              主键
     * @param   ids             主键数组
     * @param   excludeIds      不包含主键数组
     * @return  Page<NotificationTextModel> 通知内容领域分页对象
     */
    @RequestMapping(method = RequestMethod.GET)
    //@Log(actionName = "查询通知内容列表")
    public Page<NotificationTextModel> getNotificationTextList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds){
        NotificationTextQuery notificationTextQuery = new NotificationTextQuery();
        notificationTextQuery.setId(id);
        notificationTextQuery.setExcludeIds(excludeIds);
        notificationTextQuery.setIds(ids);
        return notificationTextService.getNotificationTextModelPage(limit, offset, notificationTextQuery);
    }

    /**
     * 查询单个通知内容
     * @param   id  主键
     * @return  NotificationTextModel   通知内容领域对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    //@Log(actionName = "查询通知内容")
    public NotificationTextModel getNotificationTextById(@PathVariable(value = "id") String id){
        return notificationTextService.getOneNotificationTextModel(id);
    }

    /**
     * 新增通知内容
     * @param   notificationTextCreate  新增对象参数
     * @param   bindingResult   参数校验结果
     * @return  NotificationTextModel   通知内容领域对象
     */
    @RequestMapping(method = RequestMethod.POST)
    //@Log(actionName = "新增通知内容")
    public NotificationTextModel createNotificationText(@RequestBody @Valid NotificationTextCreate notificationTextCreate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return notificationTextService.createNotificationText(notificationTextCreate);
    }

    /**
     * 更新通知内容
     * @param id                            主键
     * @param notificationTextUpdate 更新对象参数
     * @param bindingResult                 参数校验结果
     * @throws ArgumentValidException       参数校验异常类
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    //@Log(actionName = "更新通知内容")
    public void updateNotificationText(@PathVariable(value = "id") String id,
                           @RequestBody @Valid NotificationTextUpdate notificationTextUpdate, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        notificationTextService.updateNotificationText(notificationTextUpdate);
    }

    /**
     *  删除通知内容
     * @param id    主键
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    //@Log(actionName = "删除单个通知内容")
    public void deleteNotificationText(@PathVariable(value = "id") String id){
        notificationTextService.deleteNotificationText(id);
    }

    /**
     *  删除通知内容
     * @param ids    主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    //@Log(actionName = "批量删除通知内容")
    public void deleteNotificationTexts(@RequestParam(value = "ids") String[] ids){
        notificationTextService.deleteNotificationText(ids);
    }

    /**
    * 导出
    * @param id     主键
    * @param ids    包含的主键数组
    * @param excludeIds     排除的主键数组
    * @param modelMap   modelMap对象
    * @return ModelAndView JEECG_EXCEL_VIEW Excel报表视图
    */
    @RequestMapping(value="/export", method = RequestMethod.GET)
    public ModelAndView exportNotificationText(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "ids", required = false) String[] ids,
            @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
            ModelMap modelMap) {
        NotificationTextQuery notificationTextQuery = new NotificationTextQuery();
        notificationTextQuery.setId(id);
        notificationTextQuery.setExcludeIds(excludeIds);
        notificationTextQuery.setIds(ids);
        List<NotificationTextModel> notificationTextList = notificationTextService.getNotificationTextModelList(notificationTextQuery);
        modelMap.put(NormalExcelConstants.FILE_NAME, "通知内容信息");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, NotificationTextModel.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, notificationTextList);
        return new ModelAndView(NormalExcelConstants.JEECG_EXCEL_VIEW);
    }


}
