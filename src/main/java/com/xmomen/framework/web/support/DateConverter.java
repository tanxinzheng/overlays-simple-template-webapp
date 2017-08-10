package com.xmomen.framework.web.support;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tanxinzheng on 17/6/11.
 */
public class DateConverter implements Converter<String, Date> {


    @Override
    public Date convert(String source) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DDTHH:MM:SSZ");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
