package com.xmomen.framework.web.support;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.validator.routines.DateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by tanxinzheng on 17/6/11.
 */
public class DateConverter implements Converter<String, Date> {

    private static Logger logger = LoggerFactory.getLogger(DateConverter.class);

    private FastDateFormat[] datePatterns = {
            DateFormatUtils.ISO_DATE_FORMAT,
            DateFormatUtils.ISO_DATETIME_FORMAT,
            DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT,
            DateFormatUtils.ISO_DATE_TIME_ZONE_FORMAT
    };

    @Override
    public Date convert(String source) {
        try {
            for (FastDateFormat datePattern : datePatterns) {
                if(DateValidator.getInstance().isValid(source, datePattern.getPattern())){
                    return datePattern.parse(source);
                }
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < datePatterns.length; i++) {
                FastDateFormat datePattern = datePatterns[i];
                stringBuffer.append(datePattern.getPattern());
                if(datePatterns.length != i){
                    stringBuffer.append(",");
                }
            }
            throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Date.class), source,
                    new IllegalArgumentException(MessageFormat.format("Only support date format [{0}].", stringBuffer)));
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
