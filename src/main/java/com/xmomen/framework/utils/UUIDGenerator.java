package com.xmomen.framework.utils;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by tanxinzheng on 17/8/1.
 */
public class UUIDGenerator implements Serializable {

    public static final UUIDGenerator UUID_UTILS = new UUIDGenerator();

    public static UUIDGenerator getInstance(){
        return UUID_UTILS;
    }

    /**
     * generate 32 uuid
     * @return
     */
    public String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * generate length size uuid
     * @return
     */
    public String getUUID(int length){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString().replaceAll("-", "");
        if(str.length() > length){
            return str.substring(0, length);
        }
        while (str.length() < length){
            StringBuilder stringBuilder = new StringBuilder(str);
            stringBuilder.append(getUUID());
            if(stringBuilder.length() >= length){
                str = stringBuilder.substring(0, length);
            }
        }
        return str;
    }
}
