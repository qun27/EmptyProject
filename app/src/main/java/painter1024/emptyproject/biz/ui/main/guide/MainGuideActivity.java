package painter1024.emptyproject.biz.ui.main.guide;

import android.content.Context;
import android.content.Intent;

import painter1024.emptyproject.core.ui.base.component.BaseActivity;

/**
 * 新版本引导页
 */

public class MainGuideActivity extends BaseActivity{

    public static Intent prepareIntent(Context context) {
        return new Intent(context, MainGuideActivity.class);
    }
}
