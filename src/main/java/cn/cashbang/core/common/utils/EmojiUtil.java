package cn.cashbang.core.common.utils;

public class EmojiUtil {

    /**
     * 将emoji表情替换成空串
     *
     * @param source
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source) {
        if (source != null && source.length() > 0) {
            return source.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");
        } else {
            return source;
        }
    }
}
