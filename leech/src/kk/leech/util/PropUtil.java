package kk.leech.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Get a Property object with a very properties file
 * 
 * @author Kary
 * 
 */
public class PropUtil {

	private static Properties prop = new Properties();

	/**
	 * 
	 * @param propFile
	 *            Properties file path.
	 * @param check
	 *            Properties file need hash check.
	 * @return
	 * @throws IOException
	 */
	public static Properties getProperties(String propFileName, boolean check)
			throws IOException {
		String configPath = "/config/";

		if (check) {
			if (!hashCheck(propFileName)) {
				throw new IOException(MsgUtil
						.getProperty(MsgUtil.PROP_FILE_READ_ERROR));
			}
		}
		prop
				.load(PropUtil.class.getResourceAsStream(configPath
						+ propFileName));

		return prop;
	}

	public static Properties getProperties(String propFile) throws IOException {
		return getProperties(propFile, false);
	}

	// Prevent properties file from being modified.
	private static boolean hashCheck(String propFile) throws IOException {
		// TODO unimplemented, finish this some day.
		return true;
	}

	// unit test
	public static void main(String[] args) {
		try {
			Properties p = PropUtil.getProperties("test.properties");
			System.out.println(p.getProperty("key"));
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
}
