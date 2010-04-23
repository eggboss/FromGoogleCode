import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
/**
 * <br> Description : 提供方法來驗證或過濾欄位的值。<verify開頭的是格式確認功能；check開頭的是驗證功能；filter結尾的是過濾功能>
 * <br>  				 1)全數字的格式
 * <br> 				 2)信用卡格式
 * <br>  				 3)E-mail格式
 * <br>  				 4)電話號碼格式
 * <br>  				 5)身分證格式				
 * <br>  				 一)信用卡驗證
 * <br>  				 二)身分證驗證
 * <br>  				 A)Xss Injection 過濾
 * <br>  				 B)SQL Injection 過濾
 * <br>  				 C)Resource Injection 過濾
 * <br> 				 D)HTTP Response Splitting 過濾
 * <br>  @author Hugh
 * <br>
 * <br>  @build_Time 2008/10/16 
 */
public class SecurityUtil {
	
	/**
	 * <br> Description	判定是否為有效的身分證號碼
	 * <br> @param str	要驗證的字串
	 * <br> @return		是否有效
	 */
	public static boolean checkForIdentityCard(String str) {
		if(verifyForIdentityCard(str)) {
			String sequence = "ABCDEFJHJKLMNPQRSTUVWXYZIO";
			str = str.toUpperCase();
			int total =0;
			int CityNum = 10 + sequence.indexOf(str.substring(0,1));
			total = CityNum/10 + CityNum%10*9;				
			char[] temp = str.substring(1).toCharArray();
			for (int i = 0; i < temp.length; i++) {						
				if(i==temp.length-1) {					
					total += (Character.valueOf(temp[i])-48);				
				} else {					
					total += (Character.valueOf(temp[i])-48)* (8-i);				
				}
			}
			if (total%10 == 0) {
				return true;
			}
		}		 
		return false;
	}
		
	/**
	 * <br> Description	判斷是否符合身分證的格式
	 * <br> @param str	要驗證的字串
	 * <br> @return		是否有效
	 */
	public static boolean verifyForIdentityCard(String str) {
		boolean flag = false;
		if(Pattern.matches("^[a-zA-Z][1-2][0-9]{8}$", str)) {
			flag = true;
		}
		System.out.println("是否通過驗證?" + flag);
		return flag;
	}
	
	/**
	 * <br> Description 		使用Luhn演算法來驗證信用卡是否有效
	 * <br> @param cardNumber	要驗證的字串
	 * <br> @return				是否有效
	 */	
	public static boolean verifyForCreditCard(String cardNumber) {		
		String digitsOnly = cardNumber;
		if (Pattern.matches("\\D",cardNumber)) {
			digitsOnly = cardNumber.replaceAll("\\D", "");
		}
		int sum = 0;
		int digit = 0;
		int addend = 0;
		boolean timesTwo = false;

		for (int i = digitsOnly.length () - 1; i >= 0; i--) {
			digit = Integer.parseInt (digitsOnly.substring (i, i + 1));
			if (timesTwo) {
				addend = digit * 2;
				if (addend > 9) {
					addend -= 9;
				}
			} else {
				addend = digit;
			}
			sum += addend;
			timesTwo = !timesTwo;
		}				
		boolean flag = (sum % 10 == 0? true:false);
		System.out.println("是否通過驗證?" + flag);
		return flag;
	}
	
	/**
	 * <br> Description 		用來驗證字串是屬於哪家信用卡銀行
	 * <br> @param cardNumber	要驗證的字串
	 * <br> @return				正確傳回：MASTER 或 VISA 或 JCB 或 AMERICAN EXPRESS 或 DISCOVERc 或 DINER'S CLUB/CARTE BLANCHE
	 * <br> 					錯誤傳回：ERROR
	 */	
	public static String checkForCreditCardBank(String cardNumber) {
		String digitsOnly = "";
		if (Pattern.matches("\\D",cardNumber)) {
			digitsOnly = cardNumber.replaceAll("\\D", "");
		} else {
			digitsOnly = cardNumber;
		}
		if(verifyForCreditCard(digitsOnly)) {
			String estimationStr = digitsOnly.substring(0,4);			
			//	51xxxx-55xxxx
			if(Pattern.matches("^5[1-5][0-9]{2}$", estimationStr)) {
				return "MASTER";
			}
			//	4xxxxx
			if(Pattern.matches("^4[0-9{3}]$", estimationStr)) {
				return "VISA";
			}
			//	3528xx—3589xx,2131,1800 
			if(Pattern.matches("^(35(([2][8-9])|([3-8][0-9]))|(2131|1800))$", estimationStr)) {
				return "JCB";
			}
			//	34xxxx,37xxxx
			if(Pattern.matches("^3(4|7)[0-9]{2}$", estimationStr)) {
				return "AMERICAN EXPRESS";
			}
			//	6011xx
			if(Pattern.matches("^6011$", estimationStr)) {
				return "DISCOVER";
			}
			//	300xxx~305xxx,36xxxx,38xxxx
			if(Pattern.matches("^3((0[0-5])|(6|8)[0-9])[0-9]$", estimationStr)) {
				return "DINER'S CLUB/CARTE BLANCHE";
			}
		}
		return "ERROR";
	}
		
	/**
	 * <br> Description 用來驗證整個字串都必須是數字的狀況
	 * <br> @param str	要驗證的字串
	 * <br> @return		是否符合
	 */
	public static boolean verifyForNumbers(String str) {
		System.out.print("驗證的字串為：" + str + ",");
		boolean flag = false;
		String ALL_NUMBER = "\\d*";
		if("".equals(str)||Pattern.matches(ALL_NUMBER, str)) {
			flag = true;
		}		
		System.out.println("是否通過驗證?" + flag);
		return flag;
	}
	
	/**
	 * <br> Description	驗證電話號碼的欄位，可驗證的格式為	(049)123456，049-123456，049123456，
	 * <br> 									(02)21234567，02-21234567，0221234567，
	 * <br> 									(0922)123456，0922-123456，0922123456
	 * <br> @param str	要驗證的字串
	 * <br> @return		true  符合 
	 * 					false 不合
	 */
	public static boolean verifyForPHONE_NUMBER(String str) {
		System.out.print("驗證的字串為：" + str + ",");
		boolean flag = false;
		String PHONE_NUMBER = "[(]?(([0-9]{2}[-)]?[0-9]{7})|([0-9]{2}[-)]?[0-9]{8})|([0-9]{3}[-)]?[0-9]{6})|([0-9]{4}[-)]?[0-9]{6}))";
		if("".equals(str)||Pattern.matches(PHONE_NUMBER, str)) {
			flag = true;
		}		
		System.out.println("是否通過驗證?" + flag);
		return flag;
	}
	
	/**
	 * <br> Description	驗證E-mail的欄位，可驗證的格式為	用戶名@伺服器
	 * <br> 						用戶名：可以含有大小寫字母阿拉伯數字,句號 ('.'), 減號('-'), and 下劃線 ('_')
	 * <br> 	 					伺服器：可以含有大小寫字母阿拉伯數字,句號 ('.'), 減號('-') 
	 * <br> @param str	要驗證的字串
	 * <br> @return		true 符合  false 不合
	 */
	public static boolean verifyForEMAIL(String str) {
		System.out.print("驗證的字串為：" + str + ",");
		boolean flag = false;
		String EMAIL = "^[-]?[_a-zA-Z0-9.]+[-]?@[-]?[a-zA-Z0-9.]+[-]?$";
		if("".equals(str)||Pattern.matches(EMAIL, str)) {
			flag = true;
		}		
		System.out.println("是否通過驗證?" + flag);
		return flag;
	}
	
	/**
	 * <br> Description	針對使用者輸入較自由的欄位，如留言板。將符合黑名單內的關鍵字替換成""
	 * <br> @param str	要驗證的字串
	 * <br> @return		過濾後的字串
	 */
	public static String xssfilter(String str) {
		str = str.toLowerCase();
		System.out.println("原始字串為：" + str);
		str = StringEscapeUtils.escapeHtml(str);
		System.out.println("經StringEscapeUtils.escapeHtml()處理後字串為：" + str);
		String xssBlackList="(javascript|onclick|ondblclick|onkeypress|onkeyup|onblur|onfocus|onmouseup|onkeydown|onmouseover|onmouseout|onmousemove|a\\shref=|\\ssrc)+";
		if(Pattern.matches(".*"+xssBlackList+".*", str)) {
			str = str.replaceAll(xssBlackList, "");
		}		
		System.out.println("過濾後的字串為：" + str);
		return str;
	}
	
	/**
	 * <br> Description 防止SQL Injection	
	 * <br> @param str	要驗證的字串
	 * <br> @return		過濾後的字串
	 */
	public static String sqlInjectionFilter(String str) {
		str = str.toLowerCase();
		String specilSymbol = ".*('|;|!|--)+.*";
		System.out.println("原始字串為：" + str);
		//第一部分  去除特殊字元
		if(Pattern.matches(specilSymbol, str)) {
			str = str.replaceAll("\\\\", "\\\\\\\\");	//將/取代為\\
			str = str.replaceAll("/", "\\\\/");			//將/取代為\/
			str = str.replaceAll("'", "\\\\'");			//將'取代為\'			
			str = str.replaceAll("\"", "\\\\\"");		//將"取代為\"
			str = str.replaceAll("--", "\\\\-\\\\-");	//將--取代為\-\-
			str = str.replaceAll(";", "\\\\;");			//將;取代為\;			
			System.out.println("去除特殊字元：" + str);
		}
		String specilKeyWord="(select\\s|delete\\s|create\\s|grant\\s|update\\s|exec\\s|drop\\s|truncate\\s)+";
		//第二部分  去除關鍵字
		if(Pattern.matches(".*"+specilKeyWord+".*",str)) {			
			str = str.replaceAll(specilKeyWord, "");
			System.out.println("去除黑名單內關鍵字元：" + str);
		}
		System.out.println("過濾後的字串為：" + str);
		return str;		
	}
	
	/**
	 * <br> Description	用來處理由使用者輸入的檔案名稱。去除名稱中含有 . .. " \ / ( ) & 等特殊符號 
	 * <br> @param str	要驗證的字串
	 * <br> @return		過濾後的字串
	 */
	public static String resourceInjectionFilter(String str) {
		System.out.println("原始字串為：" + str);
		if(Pattern.matches(".*[.\"\\%;()&/]+.*", str)) {
			str = str.replaceAll("[.\"\\%;()&/]", "_");
			System.out.println("過濾後的字串為：" + str);
			return str;
		}
		return str;
	} 
	
	/**
	 * <br> Description	從將要加入Header的參數中 拿掉 換行字元 CR（Carriage Return，亦由%0d或\r指定）和LF（Line Feed，亦由%0a或\n指定）
	 * <br> @param str	要驗證的字串
	 * <br> @return		過濾後的字串
	 */
	public static String httpResponseSplittingFilter (String str) {
		System.out.println("原始字串為：" + str);
		
		str = str.replaceAll("%0d", "_");
		str = str.replaceAll("%0a", "_");
		str = str.replaceAll("\\r", "_");
		str = str.replaceAll("\\n", "_");
		System.out.println("過濾後的字串為：" + str);
				
		return str;
	}
	
	public static void main(String[] args) {
		//測試電話號碼格式
		System.out.println("==========================測試電話號碼格式=========================");
		String phone_situation1="0922-123456";
		SecurityUtil.verifyForPHONE_NUMBER(phone_situation1);
		String phone_situation2="(02)21234567";
		SecurityUtil.verifyForPHONE_NUMBER(phone_situation2);
		String phone_situation3="0922123456' <iframe src=''></iframe>";
		SecurityUtil.verifyForPHONE_NUMBER(phone_situation3);
		System.out.println("=============================================================");
		//測試E-mail格式
		System.out.println("=========================測試E-mail格式=========================");
		String mail_situation1="weqzxw@yahoo.com.tw";
		SecurityUtil.verifyForEMAIL(mail_situation1);
		String mail_situation2="wqex_eqw@yahoo.com.tw'";
		SecurityUtil.verifyForEMAIL(mail_situation2);
		String mail_situation3="fasfaf@fasfd ' <iframe src=''></iframe>";
		SecurityUtil.verifyForEMAIL(mail_situation3);
		System.out.println("=============================================================");
		//測試全數字格式
		System.out.println("==========================測試全數字格式===========================");
		String allNbr_situation1="0000000000";
		SecurityUtil.verifyForNumbers(allNbr_situation1);
		String allNbr_situation2="0a00000000";
		SecurityUtil.verifyForNumbers(allNbr_situation2);
		String allNbr_situation3="0000-00000";
		SecurityUtil.verifyForNumbers(allNbr_situation3);
		System.out.println("=============================================================");
		//測試xss
		System.out.println("==========================測試xss==========================");
		String xssBL_situation1="'<img src='127.0.0.1/cracker.exe'>--";
		SecurityUtil.xssfilter(xssBL_situation1);
		String xssBL_situation2="'javascript:fff();--";
		SecurityUtil.xssfilter(xssBL_situation2);
		System.out.println("=============================================================");
		//測試sqlInjection
		System.out.println("=====================測試sqlInjection======================");
		String sqlInjectionBL_situation1="' ; exec sp_addlogin 'hacker' , '1234' --";
		SecurityUtil.sqlInjectionFilter(sqlInjectionBL_situation1);
		String sqlInjectionBL_situation2="' ; exec sp_addsrvrolemember 'hacker' , 'sysadmin' --";
		SecurityUtil.sqlInjectionFilter(sqlInjectionBL_situation2);
		String sqlInjectionBL_situation3="' ; UPDATE employee SET empname = 'userName<iframe src=\"http://192.168.123.101/muma/06014.htm width=0 height=0\"></iframe>' where id=6945--";
		SecurityUtil.sqlInjectionFilter(sqlInjectionBL_situation3);		
		System.out.println("=============================================================");
		//測試Http Response Splitting
		System.out.println("=====================測試Http Response Splitting======================");
		String headStr = "Wiley Hacker\r\nHTTP/1.1 200 OK\r\n...";
		SecurityUtil.httpResponseSplittingFilter(headStr);
		System.out.println("=============================================================");
		//測試Resource Injection
		System.out.println("=====================測試Resource Injection======================");
		String fileName = "../../test.html";
		SecurityUtil.resourceInjectionFilter(fileName);
		System.out.println("=============================================================");
		//測試信用卡格式
		System.out.println("=====================測試信用卡格式======================");
		String cardNumber = "3560687233581305";
		SecurityUtil.verifyForCreditCard(cardNumber);		
		System.out.println(SecurityUtil.checkForCreditCardBank(cardNumber));
		cardNumber = "3560687233581304";
		SecurityUtil.verifyForCreditCard(cardNumber);
		System.out.println(SecurityUtil.checkForCreditCardBank(cardNumber));
		System.out.println("=============================================================");
		//測試身分證格式
		System.out.println("=====================測試身分證格式======================");
		String IDCardNbr = "A123456789";
		SecurityUtil.verifyForIdentityCard(IDCardNbr);
		System.out.println("是否正確? " + SecurityUtil.checkForIdentityCard(IDCardNbr));
		IDCardNbr = "A123456780";
		SecurityUtil.verifyForIdentityCard(IDCardNbr);
		System.out.println("是否正確? " + SecurityUtil.checkForIdentityCard(IDCardNbr));
		System.out.println("=============================================================");		
	}
}