package Http;

import java.security.MessageDigest;

import sun.misc.BASE64Decoder;

//MD5&BASE64
public class MD5 {
	public static final String enCode(String s) {
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char[] str = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
				str[(k++)] = hexDigits[(byte0 & 0xF)];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ”√BASE64º”√‹
	 * 
	 * @param str
	 * @return
	 */
	public static String getBASE64(String str) {
		byte[] b = str.getBytes();
		String s = null;
		if (b != null) {
			s = new sun.misc.BASE64Encoder().encode(b);
		}
		return s;
	}

	/**
	 * Ω‚√‹BASE64◊÷¥‹
	 * 
	 * @param s
	 * @return
	 */
	public static String getFromBASE64(String s) {
		byte[] b = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				return new String(b);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new String(b);
	}
}
