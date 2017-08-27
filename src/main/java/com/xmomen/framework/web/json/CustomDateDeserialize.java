package com.xmomen.framework.web.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.validator.routines.DateValidator;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by tanxinzheng on 16/12/11.
 */
public class CustomDateDeserialize extends JsonDeserializer<Date> {

    public static final FastDateFormat ISO_DATE_MINUTE_ZONE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Date date = null;
        try {
            String source = jsonParser.getText();
            if(DateValidator.getInstance().isValid(source, DateFormatUtils.ISO_DATE_FORMAT.getPattern())){
                return DateFormatUtils.ISO_DATE_FORMAT.parse(source);
            }else if(DateValidator.getInstance().isValid(source, DateFormatUtils.ISO_DATETIME_FORMAT.getPattern())){
                return DateFormatUtils.ISO_DATETIME_FORMAT.parse(source);
            }else if(DateValidator.getInstance().isValid(source, ISO_DATE_MINUTE_ZONE_FORMAT.getPattern())){
                return ISO_DATE_MINUTE_ZONE_FORMAT.parse(source);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
