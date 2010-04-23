package com.emyfone.service;
/**
 * 贈品 Service Bean
 * @author james
 *
 */
public interface IGiftServiceBean  extends java.io.Serializable{
	/**
	 * 取得贈品代碼
	 * @return 贈品代碼
	 */
	public int getGiftId();
	/**
	 * 取得贈品名稱
	 * @return 贈品名稱
	 */
	public String getGiftName();
	/**
	 * 取得贈品規則類別
	 * @return 贈品規則類別
	 */
	public String getGiftRuleType();
}
