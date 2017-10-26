package painter1024.emptyproject.project.ui.ex.tool.loading;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import painter1024.emptyproject.R;
import painter1024.emptyproject.app.util.ResUtil;


/**
 * 加载弹框工具
 */

public class LoadingUiTool {

    private View loadingView;
    private TextView textView;

    public LoadingUiTool(Activity activity) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        loadingView = inflater.inflate(R.layout.core_widget_tool_loading,
                (ViewGroup) activity.findViewById(android.R.id.content), false);
        textView = (TextView) loadingView.findViewById(R.id.textView);
        activity.addContentView(loadingView, loadingView.getLayoutParams());
        loadingView.setVisibility(View.GONE);
    }

    public void show(CharSequence message){
        if(message==null) message = ResUtil.getString(R.string.loading);
        textView.setText(message);
        loadingView.setVisibility(View.VISIBLE);
    }

    public void hide(){
        loadingView.setVisibility(View.GONE);
    }
}
