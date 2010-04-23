package kk.leech.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

public class LogUtil {

	public static void writeLog(String logMessage) {
		//C:/HYError/yyyy/MMdd/yyyyMMdd.log
		System.out.println("<hySysLog>" + logMessage + "</hySysLog>");
		Calendar calendar = Calendar.getInstance();
		StringBuffer logPath = new StringBuffer(PathUtil
				.getProperty(PathUtil.HY_BACKUP_PATH)
				+ "errorLog/");
		logPath.append(DateUtil.calendarFormater(calendar, "yyyy/MMdd/"));
		PathUtil.createFolder(logPath.toString());

		logPath.append(DateUtil.calendarFormater(calendar, "yyyyMMdd")).append(
				".log");
		File file = new File(logPath.toString());

		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);

			pw.write((new Date()).toString() + "   " + logMessage + "\r\n");
			pw.close();
			bw.close();
			fw.close();
		} catch (IOException ioe) {
			System.out.println("log file not fount!");
		}
	}

	public static void main(String[] args) {
		LogUtil.writeLog("Error Test by Kary");
	}
}
