package kirk.mediaconvert.util;

import java.util.Locale;
import java.util.ResourceBundle;
/**
 * <p>���o�t�γ]�w��resource</p>
 * <p>������properties ��resource.properties</p>
 *
 */
public class ResourceBundleAdapter {
	/**
	 * �z�LLocale���o���P��ResourceBundle
	 * @param locale
	 * @return
	 */
	public static ResourceBundle getResourceBundle(Locale locale){
		return ResourceBundle.getBundle("resource",locale);
	}
	/**
	 * <p>�z�LLocale���o�{�b�t�Ϊ�mode</p>
	 * <p>�}�o����mode=local</p>
	 * <p>��������mode=staging</p>
	 * <p>Production����mode=production</p>
	 * @param locale �ϰ�
	 * @return
	 */
	public static String getMode(Locale locale){
		String mode = getResourceBundle(locale).getString("mode"); 
		return mode; 
	}
	/**
	 * <p>���o�{�b�t�Ϊ�mode</p>
	 * <p>�}�o����mode=local</p>
	 * <p>��������mode=staging</p>
	 * <p>Production����mode=production</p>
	 * @return
	 */
	public static String getMode(){
		String mode = getResourceBundle(Locale.ENGLISH).getString("mode"); 
		return mode; 
	}
	/**
	 * <p>�z�Lresource name��Locale���o������Resource</p>
	 * <p>���omode,���o��mode�U�ҹ�����resource name��resource</p>
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
	 * <p>�z�Lresource name��Locale���o������Resource</p>
	 * <p>���omode,���o��mode�U�ҹ�����resource name��resource</p>
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
