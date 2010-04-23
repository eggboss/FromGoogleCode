package kk.leech.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Message used in application must be definded here.
 * @author Kary Chien
 *
 */
public class AbstractMessageUtil {
	static String msgFile = "message.properties";
	private static Properties properties = new Properties();

	/**
	 * you must put your static propert mapping here
	 */
	public static final String PROP_SAMPLE_KEY = "key";

	static {
		try {
			properties = PropUtil.getProperties(msgFile);
		} catch (IOException e) {
			LogUtil.writeLog(e.toString());
		}
	}

	public AbstractMessageUtil() {
	}

	/**
	 * get property value with key
	 * @param key
	 * @return value of the key
	 */
	public static String getProperty(String key) {

		String valueStr = "";
		try {
			valueStr = properties.getProperty(key);
		} catch (Exception e) {
			LogUtil.writeLog(e.toString());
		}

		return valueStr;
	}

	public static void main(String[] args) {

		System.out.println(AbstractMessageUtil
				.getProperty(AbstractMessageUtil.PROP_SAMPLE_KEY));
	}
}
