package com.xmomen.framework.web.json;

import com.google.common.collect.Maps;
import com.xmomen.module.system.model.DictionaryModel;
import com.xmomen.module.system.model.DictionaryQuery;
import com.xmomen.module.system.service.DictionaryService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by tanxinzheng on 16/10/20.
 */
@Component
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
    public Map<String, Object> translateDictionary(DictionaryIndex dictionaryType, String dictionaryCode) {
        DictionaryQuery dictionaryQuery = new DictionaryQuery();
        dictionaryQuery.setType(dictionaryType.name());
        List<DictionaryModel> dictionaryModelList = dictionaryService.getDictionaryModelListCache(dictionaryQuery);
        if(CollectionUtils.isNotEmpty(dictionaryModelList)){
            return dictionaryModelList.stream().collect(Collectors.toMap(DictionaryModel::getDictionaryCode, DictionaryModel::getDictionaryName));
        }
        return Maps.newHashMap();
    }

    /**
     * 字典索引
     *
     * @return
     */
    @Override
    public DictionaryIndex getDictionaryIndex() {
        return DictionaryIndex.DICTIONARY;
    }
}
