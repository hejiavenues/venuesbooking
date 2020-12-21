package cn.cashbang.core.common.utils;

/**
 * @Description: 数据脱敏
 * @see:
 * @auther:hanlq
 * @version:1.0.0
 * @date:2019年09月26 16:48
 */
public class DesensitizationTool {
    public static String  desMobile(String mobile ) {
        if(mobile==null) {
            return "";
        }
        if(mobile.length()<8) {
            return mobile;
        }
        return mobile.replace(mobile.substring(3, 7), "****") ;
    }
}
