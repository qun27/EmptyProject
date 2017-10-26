package painter1024.emptyptoject.lib_android.ui.uitl;

import android.widget.TextView;

/**
 * 倒计时工具,给按钮添加倒计时功能
 */
public class CountDownTimerEx extends android.os.CountDownTimer {

    private TextView btn;

    private String onTickText = "S";
    private String onFinishText = "获取验证码";
    private OnFinishListener listener;


    public CountDownTimerEx(TextView btn, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        this.btn = btn;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        //计时过程显示
        btn.setEnabled(false);
        btn.setText(millisUntilFinished / 1000 + onTickText);
    }

    @Override
    public void onFinish() {
        //计时完毕时触发
        btn.setEnabled(true);
        btn.setText(onFinishText);
        if (listener != null)
            listener.onFinish();
    }


    /**
     * 获取倒计时结束后显示的文本
     * @return 倒计时结束后显示的文本
     */
    public String getOnFinishText() {
        return onFinishText;
    }

    /**
     * 设置倒计时结束后显示的文本
     */
    public void setOnFinishText(String onFinishText) {
        this.onFinishText = onFinishText;
    }

    /**
     * 获取倒计时显示的后缀文本
     * @return 倒计时显示的后缀文本
     */
    public String getOnTickText() {
        return onTickText;
    }

    /**
     * 设置倒计时显示的后缀文本
     */
    public void setOnTickText(String onTickText) {
        this.onTickText = onTickText;
    }

    /**
     * 计时完成监听器
     */
    public interface OnFinishListener {
        void onFinish();
    }

    public void setOnFinishListener(OnFinishListener listener) {
        this.listener = listener;
    }

}
