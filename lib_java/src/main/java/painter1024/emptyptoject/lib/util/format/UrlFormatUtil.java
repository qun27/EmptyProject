package painter1024.emptyptoject.lib.util.format;

/**
 * URL 格式工具
 */

public class UrlFormatUtil {

    /**
     * 保证http格式
     */
    public static String ensureHttp(String url) {
        if (isHttp(url))
            return url;
        else
            return "http://" + url;
    }

    /**
     * 判断是否属实http格式
     */
    public static boolean isHttp(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }
}
