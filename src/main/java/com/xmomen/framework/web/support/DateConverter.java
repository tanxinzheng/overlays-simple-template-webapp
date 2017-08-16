package com.xmomen.framework.web.support;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.validator.routines.DateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by tanxinzheng on 17/6/11.
 */
public class DateConverter implements Converter<String, Date> {

    private static Logger logger = LoggerFactory.getLogger(DateConverter.class);

    @Override
    public Date convert(String source) {
        try {
//            String dateTimePattern = DateFormatUtils.ISO_DATETIME_FORMAT.getPattern();
//            String datePattern = DateFormatUtils.ISO_DATE_FORMAT.getPattern();
            if(DateValidator.getInstance().isValid(source, DateFormatUtils.ISO_DATE_FORMAT.getPattern())){
                return DateFormatUtils.ISO_DATE_FORMAT.parse(source);
            }else if(DateValidator.getInstance().isValid(source, DateFormatUtils.ISO_DATETIME_FORMAT.getPattern())){
                return DateFormatUtils.ISO_DATETIME_FORMAT.parse(source);
            }
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
