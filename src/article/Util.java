package article;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String getRegDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date time = new Date();
		String nowTime = format.format(time);
		return nowTime;
	}
}
