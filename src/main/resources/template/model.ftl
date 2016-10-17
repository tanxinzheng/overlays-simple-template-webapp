package ${targetPackage};

import ${modulePackage}.entity.${domainObjectClassName};
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

<#if importClassList?exists>
    <#list importClassList?keys as mykey>
import ${mykey};
    </#list>
</#if>
import java.io.Serializable;

<#include "header.ftl">
@ExcelTarget(value = "${domainObjectClassName}Model")
public @Data class ${domainObjectClassName}Model implements Serializable {

<#if fieldList?exists>
    <#list fieldList as field>
    /** ${field['fieldComment']} */
    <#if !field.primaryKey && !field.hide>
    @Excel(name = "${field['fieldComment']}")
    </#if>
    private ${field['fieldType']} ${field['fieldName']};
    </#list>
</#if>

    public ${domainObjectClassName} getEntity(){
        ${domainObjectClassName} ${domainObjectName} = new ${domainObjectClassName}();
        BeanUtils.copyProperties(this, ${domainObjectName});
        return ${domainObjectName};
    }


}
