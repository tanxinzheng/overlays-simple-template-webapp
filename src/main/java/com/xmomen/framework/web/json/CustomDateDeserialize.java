package com.xmomen.framework.web.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.xmomen.commons.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by tanxinzheng on 16/12/11.
 */
public class CustomDateDeserialize extends JsonDeserializer<Date> {


    String DATE_FORMAT_yyyyMMdd = "^\\d{4}(-|\\/|.)\\d{1,2}\\1\\d{1,2}$";


    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Date date = null;
        try {
            String text = jsonParser.getText();
            if(text.matches(DATE_FORMAT_yyyyMMdd)){
                date = DateUtils.parse(text);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
