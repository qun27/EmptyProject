package painter1024.emptyproject.biz.ui.util;

import painter1024.emptyptoject.lib.util.base.StringUtil;

/**
 * 界面显示相关工具
 */

public class SecretTextUtil {

    /**
     * 省略手机号码中间
     */
    public static String hiddenPartNumber(String number) {
        if (!StringUtil.isEmpty(number) && number.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < number.length(); i++) {
                char c = number.charAt(i);
                if (i >= 3 && i <= 7) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            number = sb.toString();
        }
        return number;
    }
}
