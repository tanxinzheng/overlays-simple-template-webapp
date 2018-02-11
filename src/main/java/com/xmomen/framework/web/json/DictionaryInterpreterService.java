package com.xmomen.framework.web.json;

/**
 * Created by tanxinzheng on 16/10/20.
 */
public interface DictionaryInterpreterService {

    /**
     * 翻译
     * @param dictionaryType    字典类型
     * @param dictionaryCode    字典代码
     * @return
     */
    String translateDictionary(DictionaryIndex dictionaryType, String dictionaryCode);

    /**
     * 字典索引
     * @return
     */
    DictionaryIndex getDictionaryIndex();
}
