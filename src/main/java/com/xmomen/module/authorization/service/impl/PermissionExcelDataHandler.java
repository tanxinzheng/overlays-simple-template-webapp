package com.xmomen.module.authorization.service.impl;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.jeecgframework.poi.handler.inter.IExcelDataHandler;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by tanxinzheng on 16/12/10.
 */
@Repository
public class PermissionExcelDataHandler implements IExcelDataHandler {
    /**
     * 导出处理方法
     *
     * @param obj   当前对象
     * @param name  当前字段名称
     * @param value 当前值
     * @return
     */
    @Override
    public Object exportHandler(Object obj, String name, Object value) {
        return null;
    }

    /**
     * 获取需要处理的字段,导入和导出统一处理了, 减少书写的字段
     *
     * @return
     */
    @Override
    public String[] getNeedHandlerFields() {
        return new String[]{};
    }

    /**
     * 导入处理方法 当前对象,当前字段名称,当前值
     *
     * @param obj   当前对象
     * @param name  当前字段名称
     * @param value 当前值
     * @return
     */
    @Override
    public Object importHandler(Object obj, String name, Object value) {
        if(name.equalsIgnoreCase("权限代码")){
            if(value.equals("USER_VIEW")){
                return "用户视图查询";
            }
        }
        return null;
    }

    /**
     * 设置需要处理的属性列表
     *
     * @param fields
     */
    @Override
    public void setNeedHandlerFields(String[] fields) {
        //fields = new String[]{"permissionCode"};
    }

    /**
     * 获取这个字段的 Hyperlink ,07版本需要,03版本不需要
     *
     * @param creationHelper
     * @param obj
     * @param name
     * @param value
     * @return
     */
    @Override
    public Hyperlink getHyperlink(CreationHelper creationHelper, Object obj, String name, Object value) {
        return null;
    }

    /**
     * 设置Map导入,自定义 put
     *
     * @param map
     * @param originKey
     * @param value
     */
    @Override
    public void setMapValue(Map map, String originKey, Object value) {

    }
}
