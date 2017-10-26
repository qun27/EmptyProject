package painter1024.emptyproject.core.ui.ex.controller.edit;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import painter1024.emptyproject.R;

/**
 * 编辑标题栏控制器
 */

public class EditController {

    private ToggleButton editToggleButton;
    private Button checkAllButton;

    private OnStateChangeListener onStateChangeListener;

    private boolean empty;

    public EditController(View rootView) {
        editToggleButton = (ToggleButton) rootView.findViewById(R.id.editToggleButton);
        checkAllButton = (Button) rootView.findViewById(R.id.checkAllButton);
        if(editToggleButton==null || checkAllButton==null)
            throw new IllegalStateException("需要有两个id, R.id.editToggleButton 和 R.id.cancelButton");

        setState(false);
        editToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setState(isChecked);
                if(onStateChangeListener!=null) onStateChangeListener.onStateChange(isChecked);
            }
        });
    }

    public void setState(boolean chooseState) {
        checkAllButton.setVisibility(chooseState ? View.VISIBLE : View.GONE);
        editToggleButton.setVisibility(View.VISIBLE);
        editToggleButton.setChecked(chooseState);
    }

    /**
     * 设置是否为空，
     * 如果空状态发生变化，则先切换为非选择状态，然后隐藏相关按钮。
     * @param empty 是否为空
     */
    public void setEmpty(boolean empty) {
        //只在变化时处理，无变化直接返回
        if (this.empty == empty) return;
        this.empty = empty;
        setState(false);
        if (empty) {
            checkAllButton.setVisibility(View.GONE);
            editToggleButton.setVisibility(View.GONE);
        }
    }

    public void setOnStateChangeListener(OnStateChangeListener listener){
        onStateChangeListener = listener;
    }

    public void setOnCheckAllClickListener(View.OnClickListener listener){
        checkAllButton.setOnClickListener(listener);
    }

    public interface OnStateChangeListener {
        void onStateChange(boolean chooseState);
    }
}
