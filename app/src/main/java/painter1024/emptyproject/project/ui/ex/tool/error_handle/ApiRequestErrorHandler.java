package painter1024.emptyproject.project.ui.ex.tool.error_handle;

import painter1024.emptyproject.R;
import painter1024.emptyproject.app.util.ResUtil;
import painter1024.emptyproject.project.data.net.error.ErrorCode;
import painter1024.emptyproject.project.data.net.error.ErrorCodeException;
import painter1024.emptyproject.project.data.net.error.ErrorUtil;
import painter1024.emptyproject.core.ui.ex.util.MsgUtil;
import painter1024.emptyproject.biz.ui.user.util.LogoutUtil;

/**
 * 异常处理
 */

public class ApiRequestErrorHandler {

    public static void handleGlobalError(Throwable throwable){
        Throwable globalError = ErrorUtil.filterGlobalError(throwable);

        if(globalError instanceof ErrorCodeException){
            //处理Api反馈的异常，token无效、登录超时
            ErrorCodeException e = (ErrorCodeException) globalError;
            int errorCode = e.errorCode();
            switch (errorCode) {
                case ErrorCode.TOKEN_INVALID:
                    LogoutUtil.logoutByTokenInvalid();
                    break;
                case ErrorCode.ACCOUNT_DISABLED:
                    LogoutUtil.logoutByAccountDisabled();
                    break;
            }
        } else {
            //处理其他非Api反馈的请求异常
            MsgUtil.showMsg(ResUtil.getString(R.string.net_error));
        }
    }

    public static void handleNotGlobalError(Throwable throwable) {
        handleNotGlobalError(throwable, null);
    }

    public static void handleNotGlobalError(Throwable throwable, NotGlobalErrorHandler callback) {
        ErrorCodeException notGlobalError = ErrorUtil.filterNotGlobalError(throwable);
        if (notGlobalError==null) return;
        if (callback!=null) {
            callback.handle(notGlobalError);
        } else {
            MsgUtil.showMsg(notGlobalError.getMessage());
        }
    }

    public interface NotGlobalErrorHandler {
        void handle(ErrorCodeException notGlobalError);
    }
}
