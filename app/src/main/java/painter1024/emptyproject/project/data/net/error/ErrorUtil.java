package painter1024.emptyproject.project.data.net.error;

import android.support.annotation.Nullable;

import java.util.Arrays;

import painter1024.emptyproject.project.data.net.error.ErrorCode;
import painter1024.emptyproject.project.data.net.error.ErrorCodeException;
import painter1024.emptyproject.project.data.net.util.ApiUtil;

/**
 * 异常工具
 */

public class ErrorUtil {

    public static final int[] GLOBAL_ERROR = {
            ErrorCode.TOKEN_INVALID,
            ErrorCode.ACCOUNT_DISABLED
    };

    /**
     * 过滤得到非全局异常
     */
    @Nullable
    public static ErrorCodeException filterNotGlobalError(Throwable throwable) {
        if (! (throwable instanceof ApiUtil.ApiRequestException) ) {
            return null;
        }
        Throwable cause = throwable.getCause();
        if (cause instanceof ErrorCodeException) {
            return isGlobalError((ErrorCodeException) cause) ? null : (ErrorCodeException) cause;
        } else {
            return null;
        }
    }

    /**
     * 过滤得到全局异常，
     * 分两种：api反馈的全局性异常 和 非api反馈的网络请求异常
     */
    @Nullable
    public static Throwable filterGlobalError(Throwable throwable) {
        if (! (throwable instanceof ApiUtil.ApiRequestException) ) {
            return null;
        }
        Throwable cause = throwable.getCause();
        if (cause instanceof ErrorCodeException) {
            return isGlobalError((ErrorCodeException) cause) ? cause : null;
        } else {
            return cause;
        }
    }

    public static boolean isGlobalError(ErrorCodeException e) {
        return isGlobalError(e.errorCode());
    }

    public static boolean isGlobalError(int errorCode) {
        return Arrays.asList(GLOBAL_ERROR).contains(errorCode);
    }
}
