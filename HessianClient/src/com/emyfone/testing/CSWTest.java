package com.emyfone.testing;

import com.caucho.hessian.client.HessianProxyFactory;
import com.emyfone.service.ICSWService;
import com.sti.service.bean.IGiftServiceBean;

public class CSWTest {
	static public void main(String[] args) throws Exception{
		System.out.println("Hession Local Test Start...");
		
//		String url = "http://172.30.12.195:9003/emyfone/hessian/cswService";
		String url = "http://172.30.12.94:7005/emyfone/hessian/cswService";
//		String url = "http://localhost:7001//myfone/hessian/cswService";
//		String url = "http://www.myfone.com.tw/myfone/hessian/cswService";
		
		System.out.print("  get Factory start......");
		HessianProxyFactory factory = new HessianProxyFactory();
		System.out.println("get Factory Success！");
			
		System.out.print("  get CSWService start......");
		ICSWService cSWService =(ICSWService)factory.create(ICSWService.class,url); 
		System.out.println("get CSWService Success！");

//		String orderType = "N";
//		String payWay = "1";
//		String getGoodWay = "1"; // method2
//		String sex = "2";
//		String addrRegion = "台北市";
//		String charge = "N";
//		String modelCode = "H0GA-001";
//		String colorId = "BK";
//		String currDate = "20091101220750"; // yyyyMMddHHmmss 2009/12/28 下午 03:43:10
//		String projectNbr = "A1819";
//		String userAccount = "0912345678";
//		int promotionExchangeSerialId = 0;
//		String accessoryModelCodes = null;
//		String vasProjectNbrs = null;
//		String combinedModelCode = null;
		
		String orderType = "C";
		String payWay = "2"; // 2:無須付款
		String getGoodWay = "1"; // method2
		String sex = "1";
		String addrRegion = "台北市";
		String charge = "K2";
		String modelCode = null;
		String colorId = null;
		String currDate = "20100223203042"; // yyyyMMddHHmmss 2009/12/28 下午 03:43:10  2010/1/2 下午 08:30:42
		String projectNbr = "VC218";
		String userAccount = "0939017479";
		int promotionExchangeSerialId = 0;
		String accessoryModelCodes = null;
		String vasProjectNbrs = "VC183";//"VC183";
		String combinedModelCode = null;
		
		System.out.print("  call CSWService Method start......");
		IGiftServiceBean[] giftServiceBeans = (IGiftServiceBean[])cSWService.recalculateGift(orderType, payWay, getGoodWay, sex, addrRegion, charge, modelCode, colorId, currDate, projectNbr, userAccount, promotionExchangeSerialId, accessoryModelCodes, vasProjectNbrs, combinedModelCode);
		System.out.println("call CSWService Method Success！");
		
		System.out.println("======show test data start...=======");
		System.out.println("共有"+giftServiceBeans.length+"個贈品：");
		for(int i=0; i<giftServiceBeans.length; i++){
			System.out.print((i+1)+".");
			System.out.println(giftServiceBeans[i].getGiftName());
		}
		System.out.println("======show test data end.===========");

		System.out.println("End Of Hession Local Test.");
	}
}
