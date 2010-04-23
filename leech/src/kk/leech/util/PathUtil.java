package kk.leech.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * 
 * ??��?��?��?�路�? 以�?�建立Directory
 *
 */
public class PathUtil {

	static String propFile = "path.properties";
	private static Properties properties = new Properties();
	public static final String HY_BACKUP_PATH = "backUpPath";
	public static final String PDF_TO_TIFF_PATH = "pdfToTiff";

	static {
		try {
			properties = PropUtil.getProperties(propFile);
		} catch (IOException e) {
			LogUtil.writeLog(e.toString());
		}
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

	/**
	 *??��?? root ??��?��?�路�?
	 * @return rootPath
	 */
	public static String getRootPath() {

		URL url = (new PathUtil()).getClass().getResource("");
		StringBuffer str = new StringBuffer(url.toString());
		str.replace(0, 6, "");
		String rootPath = str.toString()
				.replaceAll("WEB-INF/classes/util/", "");

		return rootPath;
	}

	/**
	 * �? folder??�相對路�? ??��?? folder??��?��?�路�?
	 * @param folderName ?��對路�?
	 * @return folderPath 絕�?�路�?
	 */
	public static String getAbsolutePath(String folderName) {

		folderName = folderName.replace('\\', '/').replace('.', '/');

		if (folderName.charAt(0) == '/') {

			folderName = folderName.replaceFirst("/", "");
		}
		if (folderName.charAt(folderName.length() - 1) != '/') {

			folderName = folderName + "/";
		}

		String folderPath = getRootPath() + folderName;

		return folderPath;
	}

	/**�? 絕�?�路�? ??��?? ?��對路�?
	 * @param AbsolutePath 絕�?�路�?
	 * @return folder ?��對路�?
	 */
	public static String getRelativePath(String AbsolutePath) {

		String folderName = AbsolutePath.replace('\\', '/');
		String rootPath = getRootPath();

		return folderName.substring(rootPath.length());
	}

	/**建�?�目???
	 * @param folderPath 絕�?�路�?
	 * @return 
	 */
	public static File createFolder(String folderPath) {

		File file = new File(folderPath);
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (!parent.exists()) {
				createFolder(parent.getPath());
			}
			file.mkdir();
		}
		return file;
	}

	/**
	 * Get absolute path of data folder
	 * @return String
	 */
	public static String getAbsDataPath() {

		return getAbsolutePath("data/");
	}

	public static void main(String args[]) {

		PathUtil.createFolder("C:\\tmp\\kary\\test\\");

	}
}
