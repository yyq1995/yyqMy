package cn.ssmc.util;

import java.security.MessageDigest;

public class MD5Password {
	public static String md5(String pwd, String salt) {
		String pp = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bs = md.digest(pwd.getBytes());// 16
			for (byte b : bs) {
				String s = Integer.toHexString((b - salt.hashCode()) & 0xff);
				if (s.length() < 2) {
					s += "0";
				}
				pp += s;
			}
		} catch (Exception e) {

		}
		System.out.println("pp:"+pp);
		return pp;
	}
}
