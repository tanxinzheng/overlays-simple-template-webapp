package com.xmomen.framework.web.json;

/**
 * Created by tanxinzheng on 16/10/20.
 */
public class DefaultDictionaryInterpreterService implements DictionaryInterpreterService {
    /**
     * 翻译
     *
     * @param dictionaryType 字典类型
     * @param dictionaryCode 字典代码
     * @return
     */
    @Override
    public String translate(String dictionaryType, String dictionaryCode) {
        return "成功";
    }
}
