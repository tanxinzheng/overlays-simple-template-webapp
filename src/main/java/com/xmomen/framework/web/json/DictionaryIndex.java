package com.xmomen.framework.web.json;

/**
 * Created by tanxinzheng on 17/8/7.
 */
public enum DictionaryIndex {
    DICTIONARY,
    USER_ID,//用户ID -》 用户姓名
    GROUP_ID,//用户组
    TRIGGER_STATE,// 触发器状态
    NOTIFICATION_TEMPLATE,// 通知模板
    NOTIFICATION_TYPE, // 通知类型
    NOTIFICATION_DATA_STATE, // 通知状态
    ATTACHMENT_KEY  // 附件KEY -》 附件url
    ;

    public static final String DICTIONARY_CACHE_NAME_KEY = "dictionariesCache";
}
