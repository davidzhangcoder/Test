package com.example.demo.converter;


import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        if( s != null ) {
            try {
                date = sdf.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }
}
