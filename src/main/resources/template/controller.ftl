package ${targetPackage};

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
//import com.xmomen.module.logger.Log;
import ${modulePackage}.model.${domainObjectClassName}Create;
import ${modulePackage}.model.${domainObjectClassName}Query;
import ${modulePackage}.model.${domainObjectClassName}Update;
import ${modulePackage}.model.${domainObjectClassName}Model;
import ${modulePackage}.service.${domainObjectClassName}Service;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

<#include "header.ftl">
@RestController
public class ${domainObjectClassName}Controller {

    @Autowired
    ${domainObjectClassName}Service ${domainObjectName}Service;

    /**
     * ${tableComment}列表
     * @param   limit           每页结果数
     * @param   offset          页码
     * @param   id              主键
     * @param   ids             主键数组
     * @param   excludeIds      不包含主键数组
     * @param   keyword         关键字
     * @return  Page<${domainObjectClassName}Model> ${tableComment}领域分页对象
     */
    @RequestMapping(value = "/${restMapping}", method = RequestMethod.GET)
    //@Log(actionName = "查询${tableComment}列表")
    public Page<${domainObjectClassName}Model> get${domainObjectClassName}List(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "ids", required = false) String[] ids,
                                  @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
                                  @RequestParam(value = "keyword", required = false) String keyword){
        ${domainObjectClassName}Query ${domainObjectName}Query = new ${domainObjectClassName}Query();
        ${domainObjectName}Query.setId(id);
        ${domainObjectName}Query.setExcludeIds(excludeIds);
        ${domainObjectName}Query.setIds(ids);
        ${domainObjectName}Query.setKeyword(keyword);
        return ${domainObjectName}Service.get${domainObjectClassName}ModelPage(limit, offset, ${domainObjectName}Query);
    }

    /**
     * 查询单个${tableComment}
     * @param   id  主键
     * @return  ${domainObjectClassName}Model   ${tableComment}领域对象
     */
    @RequestMapping(value = "/${restMapping}/{id}", method = RequestMethod.GET)
    //@Log(actionName = "查询${tableComment}")
    public ${domainObjectClassName}Model get${domainObjectClassName}ById(@PathVariable(value = "id") String id){
        return ${domainObjectName}Service.getOne${domainObjectClassName}Model(id);
    }

    /**
     * 新增${tableComment}
     * @param   ${domainObjectClassName}Create  新增对象参数
     * @param   bindingResult   参数校验结果
     * @return  ${domainObjectClassName}Model   ${tableComment}领域对象
     */
    @RequestMapping(value = "/${restMapping}", method = RequestMethod.POST)
    //@Log(actionName = "新增${tableComment}")
    public ${domainObjectClassName}Model create${domainObjectClassName}(@RequestBody @Valid ${domainObjectClassName}Create ${domainObjectName}Create, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return ${domainObjectName}Service.create${domainObjectClassName}(${domainObjectName}Create);
    }

    /**
     * 更新${tableComment}
     * @param id                            主键
     * @param ${domainObjectClassName}Update 更新对象参数
     * @param bindingResult                 参数校验结果
     * @throws ArgumentValidException       参数校验异常类
     */
    @RequestMapping(value = "/${restMapping}/{id}", method = RequestMethod.PUT)
    //@Log(actionName = "更新${tableComment}")
    public void update${domainObjectClassName}(@PathVariable(value = "id") String id,
                           @RequestBody @Valid ${domainObjectClassName}Update ${domainObjectName}Update, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        ${domainObjectName}Service.update${domainObjectClassName}(${domainObjectName}Update);
    }

    /**
     *  删除${tableComment}
     * @param id    主键
     */
    @RequestMapping(value = "/${restMapping}/{id}", method = RequestMethod.DELETE)
    //@Log(actionName = "删除单个${tableComment}")
    public void delete${domainObjectClassName}(@PathVariable(value = "id") String id){
        ${domainObjectName}Service.delete${domainObjectClassName}(id);
    }

    /**
     *  删除${tableComment}
     * @param ids    主键
     */
    @RequestMapping(value = "/${restMapping}", method = RequestMethod.DELETE)
    //@Log(actionName = "批量删除${tableComment}")
    public void delete${domainObjectClassName}s(@RequestParam(value = "ids") String[] ids){
        ${domainObjectName}Service.delete${domainObjectClassName}(ids);
    }

    /**
    * 导出
    * @param id
    * @param ids
    * @param excludeIds
    * @param keyword
    * @param modelMap
    * @return
    */
    @RequestMapping(value="/${restMapping}/report", method = RequestMethod.GET)
    public ModelAndView export${domainObjectClassName}(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "ids", required = false) String[] ids,
            @RequestParam(value = "excludeIds", required = false) String[] excludeIds,
            @RequestParam(value = "keyword", required = false) String keyword,
            ModelMap modelMap) {
        ${domainObjectClassName}Query ${domainObjectName}Query = new ${domainObjectClassName}Query();
        ${domainObjectName}Query.setId(id);
        ${domainObjectName}Query.setExcludeIds(excludeIds);
        ${domainObjectName}Query.setIds(ids);
        ${domainObjectName}Query.setKeyword(keyword);
        List<${domainObjectClassName}Model> ${domainObjectName}List = ${domainObjectName}Service.get${domainObjectClassName}ModelList(${domainObjectName}Query);
        modelMap.put(NormalExcelConstants.FILE_NAME, "${tableComment}信息");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, ${domainObjectClassName}Model.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, ${domainObjectName}List);
        return new ModelAndView(NormalExcelConstants.JEECG_EXCEL_VIEW);
    }


}
