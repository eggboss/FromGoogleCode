package com.esoon.resource;

import java.util.Locale;
import java.util.ResourceBundle;
/**
 * <p>取得系統設定之resource</p>
 * <p>對應之properties 為resource.properties</p>
 *
 */
public class ResourceBundleAdapter {
	/**
	 * 透過Locale取得不同的ResourceBundle
	 * @param locale
	 * @return
	 */
	public static ResourceBundle getResourceBundle(Locale locale){
		return ResourceBundle.getBundle("resource",locale);
	}
	/**
	 * <p>透過Locale取得現在系統的mode</p>
	 * <p>開發環境mode=local</p>
	 * <p>測試環境mode=staging</p>
	 * <p>Production環境mode=production</p>
	 * @param locale 區域
	 * @return
	 */
	public static String getMode(Locale locale){
		String mode = getResourceBundle(locale).getString("mode"); 
		return mode; 
	}
	/**
	 * <p>取得現在系統的mode</p>
	 * <p>開發環境mode=local</p>
	 * <p>測試環境mode=staging</p>
	 * <p>Production環境mode=production</p>
	 * @return
	 */
	public static String getMode(){
		String mode = getResourceBundle(Locale.ENGLISH).getString("mode"); 
		return mode; 
	}
	/**
	 * <p>透過resource name及Locale取得對應之Resource</p>
	 * <p>取得mode,取得此mode下所對應之resource name之resource</p>
	 * @param resource 
	 * @param locale
	 * @return
	 */
	public static String getResource(String resource,Locale locale){
		String mode = getResourceBundle(locale).getString("mode");
		String output = getResourceBundle(locale).getString(mode+"."+resource);
		return output;
	}
	/**
	 * <p>透過resource name及Locale取得對應之Resource</p>
	 * <p>取得mode,取得此mode下所對應之resource name之resource</p>
	 * @param resource 
	 * @return
	 */
	public static String getResource(String resource)
	{
		String mode = getMode();
		String output = getResourceBundle(Locale.ENGLISH).getString(mode+"."+resource);
		return output;
	}
}
