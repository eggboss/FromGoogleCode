package kirk.poi.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class PoiTest {
	public InputStream getAllUserAsExcel() {
		// 利用apache的poi包來生成excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 創建一個對應於excel表格中的sheet;
		HSSFSheet sheet = workbook.createSheet("sheet1");
		// 創建excel表格中的一行
		HSSFRow row = sheet.createRow(0);

		// 創建excel表中一個一行的一個單元格
		HSSFCell cell1 = row.createCell((short) 0);
		cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell1.setCellValue("序號");

		// 創建excel表中一個一行的一個單元格
		HSSFCell cell2 = row.createCell((short) 1);
		cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell2.setCellValue("姓");

		// 創建excel表中一個一行的一個單元格
		HSSFCell cell3 = row.createCell((short) 2);
		cell3.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell3.setCellValue("名");

		// 創建excel表中一個一行的一個單元格
		HSSFCell cell4 = row.createCell((short) 3);
		cell4.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell4.setCellValue("年齡");

		List<User> users = findAllUser();
		User user = null;
		for (int i = 0; i < users.size(); i++) {
			// 對於每一個user
			user = users.get(i);
			// 對於每一個user都創建一行
			row = sheet.createRow(i + 1);

			// 創建excel表中一個一行的一個單元格
			cell1 = row.createCell((short) 0);
			cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell1.setCellValue(user.getId());

			// 創建excel表中一個一行的一個單元格
			cell2 = row.createCell((short) 1);
			cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell2.setCellValue(user.getFirstname());

			// 創建excel表中一個一行的一個單元格
			cell3 = row.createCell((short) 2);
			cell3.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell3.setCellValue(user.getLastname());

			// 創建excel表中一個一行的一個單元格
			cell4 = row.createCell((short) 3);
			cell4.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell4.setCellValue(user.getId());
		}
		
		// 這個臨時文件將保存在${TOMCAT_HOME}\bin目錄下
		File tempFile = new File("temp.xls");
		InputStream is = null;
		try {
			OutputStream os = new FileOutputStream(tempFile);
			workbook.write(os);
			is = new FileInputStream(tempFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	private List<User> findAllUser() {
		List<User> userList = new ArrayList<User>();

		User user1 = new User();
		user1.setId("11111");
		user1.setFirstname("kkkkkkk");
		user1.setLastname("oooooooo");

		User user2 = new User();
		user2.setId("222222");
		user2.setFirstname("rrrrrrrrrrr");
		user2.setLastname("tttttttttttttt");

		userList.add(user1);
		userList.add(user2);

		return userList;
	}
}
