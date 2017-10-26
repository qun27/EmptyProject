package painter1024.emptyptoject.lib.util.base;

/**
 * String工具类
 */

public class StringUtil {

    private static final int MAX_COMMENT_NUMBER = 99;

    /**
     * 判断是否为null 或 空字符串
     * @param str 被判断的文本
     * @return 如果为null 或 空字符串，返回true
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 点赞、评论数量的返回
     */
    public static String upperLimitFormat(int number) {
        return upperLimitFormat(number, MAX_COMMENT_NUMBER);
    }

    public static String upperLimitFormat(int number, int upperLimit) {
        if (number < 0) return "";
        else if (number > upperLimit) return upperLimit + "+";
        else return String.valueOf(number);
    }
}
