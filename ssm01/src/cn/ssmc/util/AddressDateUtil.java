package cn.ssmc.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AddressDateUtil {

	//获取正确格式的生日
	public static String getBirthday(Date birthday){
		String date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date = sdf.format(birthday);
		return date;
	}
}
