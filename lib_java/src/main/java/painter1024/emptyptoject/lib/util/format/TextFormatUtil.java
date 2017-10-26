package painter1024.emptyptoject.lib.util.format;

import painter1024.emptyptoject.lib.util.base.StringUtil;

/**
 * 文本格式工具
 */

public class TextFormatUtil {


    private static final String REGEX_MAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private static final String REGEX_NUMBER = "^[0-9]*$";
    //private static final String REGEX_PHONE_NUMBER = "^((13[0-9])|(15[^4,\\D])|(17[0,6,7,8])|(18[0-9])|(14[5,7]))\\d{8}$";
    private static final String REGEX_PHONE_NUMBER = "^\\d{11}$";//手机号码只能输入11位的数字
    //private static final String REGEX_PASSWORD_ = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    private static final String PASSWORD_REGEX = "^[0-9A-Za-z]+$";
    private static final String ABC_REGEX = "^[A-Za-z]+$";
    private static final String CHINESE_REGEX = "^[\u4E00-\u9FA5]+$";

    /**
     * 匹配正则
     */
    public static boolean matcherRegex(String text, String regex) {
        if( StringUtil.isEmpty(text) || StringUtil.isEmpty(regex) ) return false;
        return text.matches(regex);
    }

    /**
     * 是否是纯数字
     **/
    public static boolean isNumber(String text) {
        return matcherRegex(text, REGEX_NUMBER);
    }

    /**
     * 是否是Email
     */
    public static boolean isEmail(String text) {
        return matcherRegex(text, REGEX_MAIL);
    }

    /**
     * 是否是手机号码
     */
    public static boolean isPhoneNumber(String text) {
        return matcherRegex(text, REGEX_PHONE_NUMBER);
    }

    /**
     * 是否纯字母
     */
    public static boolean isAbc(String text){
        return matcherRegex(text, ABC_REGEX);
    }

    /**
     * 是否是纯中文
     */
    public static boolean isChinese(String text) {
        return matcherRegex(text, CHINESE_REGEX);
    }

    /**
     * 是否包含中文
     */
    public static boolean hasChinese(String text){
        boolean isContains = false;
        for (int i = 0; i < text.length(); i++) {
            isContains = isChinese( String.valueOf(text.charAt(i)) );
            if(isContains)break;
        }
        return isContains;
    }

    /**
     * 密码字符判断
     */
    public static boolean isPassword(String text) {
        return matcherRegex(text, PASSWORD_REGEX);
    }

    /**
     * 添加缩进
     */
    public static String addIndent(String text) {
        if (text == null || text.isEmpty()) return "";
        return "\u3000\u3000" + text;
    }
}

