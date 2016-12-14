package com.xmomen.module.authorization.service.impl;

import com.xmomen.module.authorization.model.PermissionCreate;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.handler.inter.IExcelVerifyHandler;
import org.springframework.stereotype.Repository;

/**
 * Created by tanxinzheng on 16/12/12.
 */
@Repository
public class PermissionExcelValidHandler implements IExcelVerifyHandler<PermissionCreate>{
    /**
     * 导入校验方法
     *
     * @param obj 当前对象
     * @return
     */
    @Override
    public ExcelVerifyHanlderResult verifyHandler(PermissionCreate obj) {
        ExcelVerifyHanlderResult excelVerifyHanlderResult = new ExcelVerifyHanlderResult();
        excelVerifyHanlderResult.setSuccess(true);
        StringBuilder stringBuilder = new StringBuilder();
        if(obj.getPermissionCode() != null){
            if(!obj.getPermissionCode().equals("UUUU")){
                stringBuilder.append("未找到匹配代码");
            }
        }
        if(stringBuilder.length() > 0){
            excelVerifyHanlderResult.setSuccess(false);
            excelVerifyHanlderResult.setMsg(stringBuilder.toString());
        }
        return excelVerifyHanlderResult;
    }
}
