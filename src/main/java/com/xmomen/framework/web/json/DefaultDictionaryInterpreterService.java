package com.xmomen.framework.web.json;

import com.xmomen.module.system.model.DictionaryModel;
import com.xmomen.module.system.model.DictionaryQuery;
import com.xmomen.module.system.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tanxinzheng on 16/10/20.
 */
public class DefaultDictionaryInterpreterService implements DictionaryInterpreterService {

    @Autowired
    DictionaryService dictionaryService;

    /**
     * 翻译
     *
     * @param dictionaryType 字典类型
     * @param dictionaryCode 字典代码
     * @return
     */
    @Override
    public String translate(String dictionaryType, String dictionaryCode) {
        DictionaryQuery dictionaryQuery = new DictionaryQuery();
        dictionaryQuery.setCode(dictionaryCode);
        dictionaryQuery.setType(dictionaryType);
        DictionaryModel dictionaryModel = dictionaryService.getOneDictionaryModel(dictionaryQuery);
        if(dictionaryModel != null){
            return dictionaryModel.getDictionaryName();
        }
        return null;
    }
}
