package com.emyfone.service;


import com.sti.portal.business.bo.IPriceInstallment;
import com.sti.service.InvalidInputException;
import com.sti.service.MsisdnCancelReservationException;
import com.sti.service.MsisdnReservationException;
import com.sti.service.ServiceException;
import com.sti.service.SystemException;
import com.sti.service.bean.IChargeServiceBean;
import com.sti.service.bean.IDataChargeServiceBean;
import com.sti.service.bean.IGiftServiceBean;
import com.sti.service.bean.IHLRMsisdnServiceBean;
import com.sti.service.bean.IPhoneServiceBean;
import com.sti.service.bean.IProjectServiceBean;
/**
 * Web Service Interface - 提供CSW使用的Web Service介面
 * @author james
 * Implements in CSWServiceImpl
 */
public interface ICSWService {
	/**
	 * 取得手機
	 * @param cOrderApplyType 現在訂單種類<=>專案型態(N:新申裝/C:續約/P:N新申裝)
	 * @param userAccount 用戶代碼(0開頭為用戶,用以當續約時取得用戶等級)(若訂單種類為新申裝或NP，則忽略此參數)
	 * @param promotionExchangeSerialId 異業兌換流水號(NULL表非異業訂單)
	 * @return 可用手機集合
	 * @throws InvalidInputException 錯誤輸入(無效參數)
	 * @throws SystemException 系統錯誤
	 * @throws ServiceException 服務異常
	 */
	public IPhoneServiceBean[] getPhones(String cOrderApplyType,String userAccount,int promotionExchangeSerialId) throws InvalidInputException,SystemException,ServiceException;
	/**
	 * 取得手機可用專案
	 * @param modelCode 料號
	 * @param cOrderApplyType 現在訂單種類<=>專案型態(N:新申裝/C:續約/P:N新申裝)
	 * @param userAccount 用戶代碼(續約時 用戶用以取得用戶等級)(若訂單種類為新申裝或NP，則忽略此參數)
	 * @param promotionExchangeSerialId 異業兌換流水號(NULL表非異業訂單)
	 * @return 可用專案集合
	 * @throws InvalidInputException 錯誤輸入(無效參數)
	 * @throws SystemException 系統錯誤
	 * @throws ServiceException 服務異常
	 */
	public IProjectServiceBean[] getHandsetContractProjects(String modelCode,String cOrderApplyType,String userAccount,int promotionExchangeSerialId) throws InvalidInputException,SystemException,ServiceException;
	/**
	 * 取得手機專案可使用之語音資費
	 * @param modelCode 料號
	 * @param projectNbr 專案代碼
	 * @param promotionExchangeSerialId 異業兌換流水號(NULL表非異業訂單)
	 * @return 可用語音資費集合
	 * @throws InvalidInputException 錯誤輸入(無效參數)
	 * @throws SystemException 系統錯誤
	 * @throws ServiceException 服務異常
	 */
	public IChargeServiceBean[] getHandsetContractCharges(String modelCode,String projectNbr,int promotionExchangeSerialId) throws InvalidInputException,SystemException,ServiceException;
	/**
	 * 取得手機專案資費可使用之數據資費
	 * @param modelCode 料號
	 * @param projectNbr 專案代碼
	 * @param chargeId 資費代碼
	 * @param promotionExchangeSerialId 異業兌換流水號(NULL表非異業訂單)
	 * @return 可用數據資費集合
	 * @throws InvalidInputException 錯誤輸入(無效參數)
	 * @throws SystemException 系統錯誤
	 * @throws ServiceException 服務異常
	 */
	public IDataChargeServiceBean[] getHandsetContractDataCharges(String modelCode,String projectNbr,String chargeId,int promotionExchangeSerialId) throws InvalidInputException,SystemException,ServiceException;
	/**
	 * 取得手機價格
	 * @param modelCode 料號(NULL表通話約?那取啥手機價格)
	 * @param modelColor 顏色
	 * @param userAccount 用戶代碼(戶用以取得用戶等級)(若訂單種類為新申裝或NP，則忽略此參數)
	 * @param projectNbr 專案代碼
	 * @param chargeId 資費代碼
	 * @param npNbr NP來源門號(NULL表非NP訂單)
	 * @param generation 世代別(2:2G/3:3G)
	 * @param deliveryType 配送方式 0：直營，1：宅配，2：無須運送。
	 * @param promotionExchangeSerialId 異業兌換流水號(NULL表非異業訂單)
	 * @return 價格
	 * @throws InvalidInputException 錯誤輸入(無效參數)
	 * @throws SystemException 系統錯誤
	 * @throws ServiceException 服務異常
	 */
	public IPriceInstallment[] getPriceInstallments(String modelCode,String modelColor,String userAccount,String projectNbr,String chargeId,String npNbr,int generation,String deliveryType,int promotionExchangeSerialId) throws InvalidInputException,SystemException,ServiceException;
	/**
	 * 重新計算贈品(僅手機禮使用系統日期查詢,其他禮則使用元成單日期查詢)
	 * @param orderType 訂單種類 新申裝：N 續約：C NP申裝：P VAS申裝：V
	 * @param payWay 付款方式 刷卡分期:2 刷卡一次付清:1 現金:0
	 * @param getGoodWay 取貨方式 宅配:1 門市:0
	 * @param sex 性別 男:2 女:1
	 * @param addrRegion 地區
	 * @param charge 資費
	 * @param modelCode 料號(NULL表通話約)
	 * @param colorId 手機顏色代碼
	 * @param currDate 原成單時間(NULL：系統時間) 2009/3/13配合CSW修改為String字串EX:20090311120001 yyyyMMddHHmmss
	 * @param projectNbr 專案代碼
	 * @param userAccount 用戶代碼(戶用以取得用戶等級)(新申裝或NP，則忽略此參數)
	 * @param promotionExchangeSerialId 異業兌換流水號(NULL表非異業訂單)
	 * @param accessoryModelCodes 加購配件料號集合(加購配件,相同配件用多筆表示ex: {ATI-005,00959,ATI-005,03831})
	 * @param vasProjectNbrs 加值服務名稱集合(逗點分隔)
	 * @param combinedModelCode 組合商品料號
	 * @return 可用贈品
	 * @throws InvalidInputException 錯誤輸入(無效參數)
	 * @throws SystemException 系統錯誤
	 * @throws ServiceException 服務異常
	 */
	public IGiftServiceBean[] recalculateGift(String orderType,String payWay,String getGoodWay,String sex,String addrRegion,String charge,String modelCode,String colorId,String currDate,String projectNbr,String userAccount,int promotionExchangeSerialId,String accessoryModelCodes,String vasProjectNbrs,String combinedModelCode) throws InvalidInputException,SystemException,ServiceException;
	/**
	 * 取得門號約所有可用資費
	 * @param cOrderApplyType 現在訂單種類<=>專案型態(N:新申裝/C:續約/P:N新申裝)
	 * @param userAccount 用戶代碼(續約時 用戶用以取得用戶等級)(新申裝或NP，則忽略此參數)
	 * @return 可用語音資費集合
	 * @throws InvalidInputException 錯誤輸入(無效參數)
	 * @throws SystemException 系統錯誤
	 * @throws ServiceException 服務異常
	 */
	public IChargeServiceBean[] getNonHandsetContractCharges(String cOrderApplyType,String userAccount) throws InvalidInputException,SystemException,ServiceException;
	/**
	 * 取得門號約可用之專案
	 * @param chargeId 資費代碼
	 * @param cOrderApplyType 現在訂單種類<=>專案型態(N:新申裝/C:續約/P:N新申裝)
	 * @param userAccount 用戶代碼(續約時 用戶用以取得用戶等級)(新申裝或NP，則忽略此參數)
	 * @return 可用專案集合
	 * @throws InvalidInputException 錯誤輸入(無效參數)
	 * @throws SystemException 系統錯誤
	 * @throws ServiceException 服務異常
	 */
	public IProjectServiceBean[] getNonHandsetContractProjects(String chargeId,String cOrderApplyType,String userAccount) throws InvalidInputException,SystemException,ServiceException;
	/**
	 * 取得門號約之可用數據資費
	 * @param chargeId 語音資費代碼
	 * @param projectNbr 專案代碼
	 * @return 可用數據資費集合
	 * @throws InvalidInputException 錯誤輸入(無效參數)
	 * @throws SystemException 系統錯誤
	 * @throws ServiceException 服務異常
	 */
	public IDataChargeServiceBean[] getNonHandsetContractDataCharges(String chargeId,String projectNbr) throws InvalidInputException,SystemException,ServiceException;
	/**
	 * 取消舊門號之預約並使其返回門號倉,並預約新門號<br/>
	 * 1)預約新門號(LOCAL& GSMBS)
	 * 2)取消預約舊門號(LOCAL& GSMBS)
	 * 3)寫入預約紀錄
	 * 4)無更新訂單資料表內門號資訊
	 * @param userIdentification 身份證號
	 * @param orderApplyType 訂單初始型態 ex:EcOrderApply.orderApplyType
	 * @param orderSn 訂單流水號 ex:EcOrderApply.orderSn
	 * @param oldReservedMsisdn 舊門號
	 * @param newReserveMsisdn 新門號
	 * @throws MsisdnReservationException
	 * @throws MsisdnCancelReservationException
	 * @throws InvalidInputException 錯誤輸入(無效參數)
	 * @throws SystemException 系統錯誤
	 * @throws ServiceException 服務異常
	 */
	public void updateReservedMsisdn(String userIdentification,String orderApplyType, String orderSn,String oldReservedMsisdn,String newReserveMsisdn) throws MsisdnReservationException,MsisdnCancelReservationException,InvalidInputException,SystemException,ServiceException;
//	/**
//	 * 更改付款資料為CSW已檢核完成-->status改為授權成功
//	 * @param orderApplyType 訂單初始型態 ex:EcOrderApply.orderApplyType
//	 * @param orderSn 訂單流水號 ex:EcOrderApply.orderSn
//	 * @throws InvalidInputException 錯誤輸入(無效參數)
//	 * @throws SystemException 系統錯誤
//	 * @throws ServiceException 服務異常
//	 */
//	public void markOrderPaymentToChecked(String orderApplyType, String orderSn) throws InvalidInputException,SystemException,ServiceException;
	/**
	 * ECHO
	 * @param input
	 * @return input
	 * @throws SystemException
	 * @throws ServiceException
	 */
	public String echo(String input) throws SystemException,ServiceException;
	/**
	 * 取得門號資訊
	 * @param phoneNbr
	 * @return 門號資訊
	 * @throws InvalidInputException 錯誤輸入(無效參數)
	 * @throws SystemException 系統錯誤
	 * @throws ServiceException 服務異常
	 */
	public IHLRMsisdnServiceBean getMsisdn(String phoneNbr) throws InvalidInputException,SystemException,ServiceException;
	/**
	 * @param userAccount 用戶代碼(戶用門號以取得用戶等級)
	 * @return java.util.ArrayList vasDetails
	 * @throws InvalidInputException 資料輸入錯誤
	 * @throws SystemException 系統錯誤
	 * @throws ServiceException 服務異常
	 * vasDetails (java.util.ArrayList) (vas 詳細資料集合)<br/>
	 * vasDetails:vasDetail[]
	 * vasDetail (java.util.HashMap) (vas 詳細資料)<br/>
	 * vasDetail:vasProjectNbr (java.lang.String) (vas 專案代碼)<br/>
	 * vasDetail:vasDisplayName (java.lang.String) (vas 顯示名稱)<br/>
	 * vasDetail:vasGroupId (java.lang.String) 群組代碼
	 * vasDetail:vasGroupDesc (java.lang.String) 群組說明
	 * vasDetail:isGroupAllowMultiSelect (java.lang.String) Y: 允許重複申辦/ N:不允許重複申辦
	 * @see com.sti.service.IVasService
	 */
	public java.util.ArrayList getAvailableVasList(String userAccount) throws InvalidInputException, SystemException, ServiceException;
}
