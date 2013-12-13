package kirk.sample.struts.download;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kirk.poi.sample.User;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DownloadExcelTestAction extends DownloadExcelAction {

	@Override
	protected HSSFWorkbook getExcelData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 利用apache的poi包來生成excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 創建一個對應於excel表格中的sheet;
		HSSFSheet sheet = workbook.createSheet("sheet1");
		// 創建excel表格中的一行
		HSSFRow row = sheet.createRow(0);
		
		createCell(row, (short)0, "序號");
		createCell(row, (short)1, "姓");
		createCell(row, (short)2, "名");
		createCell(row, (short)3, "年齡");
		
		// 取得要匯出的資料集合
		List<User> users = findAllUser();
		
		User user = null;
		for (int i = 0; i < users.size(); i++) {
			// 對於每一個user
			user = users.get(i);
			// 對於每一個user都創建一行
			row = sheet.createRow(i + 1);

			createCell(row, (short)0, user.getId()); // 一般欄位
			createCell(row, (short)1, user.getFirstname());
			createCell(row, (short)2, user.getLastname());
			createAmountCell(row, (short)3, user.getId(), "$", workbook); // 金額欄位
			
		}
		return workbook;
	}
	
	private List<User> findAllUser() {
		List<User> userList = new ArrayList<User>();

		User user1 = new User();
		user1.setId("1111111111111111");
		user1.setFirstname("kkkkkkk");
		user1.setLastname("oooooooo");

		User user2 = new User();
		user2.setId("22222222222222222222");
		user2.setFirstname("rrrrrrrrrrr");
		user2.setLastname("tttttttttttttt");

		userList.add(user1);
		userList.add(user2);

		return userList;
	}

}
