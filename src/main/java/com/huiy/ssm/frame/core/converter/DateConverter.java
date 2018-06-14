package com.huiy.ssm.frame.core.converter;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		if (StringUtils.isEmpty(source) ){
			return null;
		}
		try {
			if (source.length()==7) {//用于只有年月的日期--by 许灿灿
				return DateUtils.parseDate(source, "yyyy-MM", "yyyy-MM-dd hh:mm:ss");
			} else {
				return DateUtils.parseDate(source, "yyyy-MM-dd", "yyyy-MM-dd hh:mm:ss");
			}
		} catch (ParseException e) {
			Logger.getLogger(this.getClass()).error(e.getMessage(), e);
			return null;
		}
	}
}
