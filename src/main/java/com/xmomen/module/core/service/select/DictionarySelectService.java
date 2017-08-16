package com.xmomen.module.core.service.select;

import com.github.pagehelper.Page;
import com.xmomen.module.core.model.SelectOptionModel;
import com.xmomen.module.core.model.SelectOptionQuery;
import com.xmomen.module.core.service.SelectService;
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


    @Cacheable(cacheNames = "dictionariesCache")
    @Override
    public List<SelectOptionModel> selectOptionModels(SelectOptionQuery selectOptionQuery) {
        Assert.notNull(selectOptionQuery);
        Assert.notNull(selectOptionQuery.getTypeCode(), "typeCode不能为空");
        DictionaryQuery dictionaryQuery = new DictionaryQuery();
        dictionaryQuery.setType(selectOptionQuery.getTypeCode());
        dictionaryQuery.setParentId(selectOptionQuery.getParentId());
        List<DictionaryModel> dictionaryModels = dictionaryService.getDictionaryModelList(dictionaryQuery);
        if(CollectionUtils.isNotEmpty(dictionaryModels)){
            List<SelectOptionModel> selectOptionModelList = new ArrayList<>();
            for (DictionaryModel dictionaryModel : dictionaryModels) {
                SelectOptionModel selectOptionModel = new SelectOptionModel();
                selectOptionModel.setCode(dictionaryModel.getDictionaryCode());
                selectOptionModel.setName(dictionaryModel.getDictionaryName());
                selectOptionModel.setTypeCode(dictionaryModel.getGroupCode());
                selectOptionModel.setTypeName(dictionaryModel.getGroupName());
                selectOptionModel.setSort(dictionaryModel.getSort());
                selectOptionModelList.add(selectOptionModel);
            }
            return selectOptionModelList;
        }
        return null;
    }
}
