package painter1024.emptyproject.biz.ui.user.util;

import painter1024.emptyproject.R;
import painter1024.emptyproject.app.MyApplication;
import painter1024.emptyproject.app.util.ResUtil;
import painter1024.emptyproject.biz.data.user.store.sp.UserSP;
import painter1024.emptyproject.biz.ui.user.UserInteract;

/**
 * 退出登录工具
 */

public class LogoutUtil {

    public static void logoutSuccess() {
        //TODO 退出登录成功的数据处理
        UserSP.putToken("");
    }

    public static void logoutByTokenInvalid() {
        //TODO token无效，退出登录
        logoutSuccess();
        UserInteract.jumpToLoginWithAlert(MyApplication.getInstance(),
                ResUtil.getString(R.string.token_invalid));
    }
    public static void logoutByAccountDisabled() {
        //TODO 登录超时，退出登录
        logoutSuccess();
        UserInteract.jumpToLoginWithAlert(MyApplication.getInstance(),
                ResUtil.getString(R.string.account_disabled));
    }
}
