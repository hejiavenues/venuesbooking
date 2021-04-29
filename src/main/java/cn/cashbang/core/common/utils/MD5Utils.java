package cn.cashbang.core.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * MD5加密工具
 *
 * @author Tiny
 * @email xxx@cashbang.cn
 * @url www.cashbang.cn
 * @date 2017年8月8日 下午5:17:34
 */
public class MD5Utils {

	private static final String SALT = "1qazxsw2";
	
	private static final String ALGORITH_NAME = "md5";
	
	private static final int HASH_ITERATIONS = 2;

	/**
	 * 使用md5生成加密后的密码
	 * @param pswd
	 * @return
	 */
	public static String encrypt(String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}
	
	/**
	 * 使用md5生成加密后的密码
	 * @param username
	 * @param pswd
	 * @return
	 */
	public static String encrypt(String username, String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}

    public static void main(String[] args) {
       //System.out.println(MD5Utils.encrypt("jdj123456"));

//        SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        String format = date.format(new Date(System.currentTimeMillis()));
//        System.out.println("当前时间："+format);

        // 获得日历对象
        Calendar c = Calendar. getInstance ();
// 获得当前时间的毫秒值
        long todayTime = c.getTimeInMillis();

        System.out.println("当前时间："+todayTime);
    }
}
