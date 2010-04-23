package kk.leech.util;

public class CommonUtil {

	public static void main(String[] args) {

		System.out.println(Math.pow(3, 3));
	}

	public static String forgePureNumber(String src) {

		if (src == null)
			return "";

		StringBuffer forged = new StringBuffer();
		char[] cs = src.toCharArray();
		for (int i = 0; i < cs.length; i++) {
			try {
				forged.append(Integer.parseInt(String.valueOf(cs[i])));
			} catch (NumberFormatException e) {
				continue;
			}
		}
		return forged.toString();
	}

	public static String makeUpZero(String number, int length) {

		StringBuffer s = new StringBuffer(number);
		int srcLength = number.length();
		for (int i = 0; i < (length - srcLength); i++) {
			s.insert(0, "0");
		}
		return s.toString();
	}
}
