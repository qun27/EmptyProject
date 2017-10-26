package painter1024.emptyptoject.lib_android.ui.widget.ex.text_view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * TODO 标题TextView，实现自动从activity中获取标题
 */

public class TitleTextView extends AppCompatTextView {
    public TitleTextView(Context context) {
        super(context);
    }

    public TitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initTitle();
    }

    private void initTitle() {

        Context context = getContext();

        // TODO: 2017/1/11 得到的不是acitivity
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            String str = (String) activity.getTitle();
            setText(str);
        }


    }
}
