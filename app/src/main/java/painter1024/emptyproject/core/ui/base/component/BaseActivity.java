package painter1024.emptyproject.core.ui.base.component;

import android.widget.TextView;

import painter1024.emptyproject.R;
import painter1024.emptyproject.core.ui.base.component.lifecycle.LifecycleActivity;

/**
 * 基础activity,所有activity的基础类，只用于添加所有页面都需要的代码
 */

public class BaseActivity extends LifecycleActivity {

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        if (titleTextView != null) {
            titleTextView.setText(title);
            if (color != 0) {
                titleTextView.setTextColor(color);
            }
        }
    }
}
