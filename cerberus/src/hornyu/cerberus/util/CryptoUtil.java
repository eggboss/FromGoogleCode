package hornyu.cerberus.util;

import hornyu.util.PropUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;

public class CryptoUtil {
	static private Logger log = Logger.getLogger("CryptoUtil.java");
	static public final String CRIPTO_MD5 = "MD5";
	static public final String CRIPTO_SHA_1 = "SHA-1";
	static public final String CRIPTO_BASE64 = "BASE64";
	static public final String CRIPTO_NONE = "NONE";
	/**
	 * 取得編碼後的字串
	 * @param inString
	 * @return
	 */
	static public String getCryptoString(String inString){
		String cryptoType = getCryptType();
		log.debug("Encode By " + cryptoType);
		return getCryptoString(inString, cryptoType);
	}
	
	/**
	 * 取得編碼後的字串
	 * @param inString
	 * @param cryptoType
	 * @return
	 */
	static public String getCryptoString(String inString,String cryptoType){
		if(cryptoType.equalsIgnoreCase(CRIPTO_MD5)){
//			byte[] digest = getCryptoByteArray(inString, cryptoType);
//			return new String(toHex(digest));
			return DigestUtils.md5Hex(inString);
		}
		else if(cryptoType.equalsIgnoreCase(CRIPTO_SHA_1)){
			return DigestUtils.shaHex(inString);
		}
		else if(cryptoType.equalsIgnoreCase(CRIPTO_BASE64)){
			return getEncodeBASE64(inString);
		}
		else if(cryptoType.equalsIgnoreCase(CRIPTO_NONE)){
			return inString;
		}
		else{
			return inString;
		}
	}
	
	public static String toHex(byte[] digest) {
	    StringBuffer buffer = new StringBuffer();
	    for (int i = 0; i < digest.length; ++i) {
	      byte b = digest[i];
	      int value = (b & 0x7F) + (b < 0 ? 128 : 0);
	      buffer.append(value < 16 ? "0" : "");
	      buffer.append(Integer.toHexString(value));
	    }
	    return buffer.toString();
	  }
	
	static public byte[] getCryptoByteArray(String inString,String cryptoType){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(cryptoType);
			md.update(inString.getBytes()); 
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		byte[] digest = md.digest(); 
		return digest;
	}
	
	//	 將 s 進行 BASE64 編碼 
	public static String getBASE64(String s) { 
		if (s == null) return null; 
		return (new sun.misc.BASE64Encoder()).encode( s.getBytes() ); 
	} 
	
	public static String getEncodeBASE64(String s) { 
		if (s == null) return null; 
		return new String(Base64.encodeBase64(s.getBytes()));
	} 

	//	 將 BASE64 編碼的字符串 s 進行解碼 
	public static String getFromBASE64(String s) { 
		if (s == null) return null; 
		BASE64Decoder decoder = new BASE64Decoder(); 
		try { 
			byte[] b = decoder.decodeBuffer(s); 
			return new String(b); 
		} catch (Exception e) { 
			return null; 
		} 
	}

	public static String getCryptType(){
		String passwdEncryptType = null;
		try {
			Properties prop = PropUtil.getProperties("cerberus.properties");
			passwdEncryptType = prop.getProperty("passwdEncryptType");
		} catch (IOException e) {
			passwdEncryptType = "NONE"; // 沒加密
		}
		return passwdEncryptType;
	}
	
	/**
	 * @param args
	 * @throws Exception
	 */
	static public void main(String[] args) throws Exception{
//		System.out.println(CryptoUtil.getCryptoString("sssss", CryptoUtil.CRIPTO_SHA_1));
//		System.out.println(DigestUtils.shaHex("sssss"));
//		System.out.println(CryptoUtil.getCryptoString("sssss", CryptoUtil.CRIPTO_MD5));
//		System.out.println(DigestUtils.md5Hex("sssss"));
//		System.out.println(CryptoUtil.getBASE64("1234"));
		
//		String ss = CryptoUtil.getEncodeBASE64("1234");
//		System.out.println(ss);
//		System.out.println(new String(Base64.decodeBase64(ss.getBytes("UTF-8")), "BIG5"));
		
//		System.out.println(new String(Base64.decodeBase64("1tC7qsjLw/G5srrNufo=".getBytes("gb2312")), "gb2312"));
		
//		String cont = "R0lGODlhGAANAPU/AMYDEIsNEr8FEc4EELMIE+wAEPUAELMGEXUOFJ0JE+IAEO8AEPIAEN4AEOYA";
//					"R0lGODlhKAAZAMQaANvb2/39/fz8/Pv7+/r6+vn5+fj4+Pf392JiYvb29v////X19fT09PPz8/Ly"
//					+"8vHx8fDw8O/v7+7u7u3t7ezs7Ovq6urp6eno6Ojn5+fm5v///wAAAAAAAAAAAAAAAAAAACH5BAHo"
//					+"AxoALAAAAAAoABkAAAX/oCZqQGmeaKqeYwsEcCzPdC0DLSnsfO//wB9OBBgYj8ikcskcAgjQqHRK"
//					+"rVpxgIJ2y+16v2BtyUAum8/otJpcOrjfb4T8IJ/D4/f8oZTo+/0ICoGBggp/gIYJCId+JQuPkJCD"
//					+"hQuBj3WYmJGQJQyen591hQyBnwqnhainoJ4lDa+wsIMIDQoNda+ZmLGxJQ6/wMCTmacOhKmFwcAl"
//					+"D83OzqLHgc3TD9XVzw8lENzd3ZMQCOEQCtyB5uXh6d4QJRHv8PCT7wgRCvL3EYH0+fERJRICCgyI"
//					+"QEIhCQULCgxEUJAcBQMFlphAsSLFWXImKJiQkeJGjhtRWaxYgoLJkycVSVBQlVIlypUvY5aoQLOm"
//					"zZs4c+qkWcKCz59AgwodStRniQtIkypdyrSpU6QlMEidSrWq1atYpZbIwLWr169gw4rluqKs2bMm";
//					"QgAAOw==";
//		System.out.println(new String(Base64.decodeBase64("QgAAOw==".getBytes("UTF-8")), "BIG5"));
//		String out = getFromBASE64(cont);
//		System.out.println(out);
//		System.out.println(new String(out.getBytes("ISO-8859-1"), "UTF-8"));
		
		Character cc = new Character('1');
		Character cc2 = cc;
		
		if(cc.equals(new Character('1'))){
			System.out.println("1");
		}else if(cc.equals(new Character('1'))){
			System.out.println("2");
		}else if(cc.equals(cc2)){
			System.out.println("3");
		}else if(cc.equals(new Character('1'))){
			System.out.println("4");
		}else if(cc.equals(new Character('1'))){
			System.out.println("5");
		}else{
			System.out.println("else");
		}
	}
}
