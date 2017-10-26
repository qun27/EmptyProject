package painter1024.emptyproject.project.ui.ex.tool.error_handle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import painter1024.emptyproject.project.data.net.util.ApiUtil;
import painter1024.emptyproject.project.ui.ex.tool.error_handle.ApiRequestErrorHandler;

/**
 * Api请求错误接收器
 */

public class ApiRequestErrorReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!ApiUtil.ACTION_API_REQUEST_ERROR.equals(action)) return;
        Throwable throwable = (Throwable) intent.getSerializableExtra(ApiUtil.KEY_API_REQUEST_ERROR);
        ApiRequestErrorHandler.handleGlobalError(throwable);
    }
}
