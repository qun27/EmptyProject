package painter1024.emptyproject.core.ui.ex.controller.state;

import android.view.View;

import painter1024.emptyproject.R;

/**
 * 状态view控制器
 */

public class StateViewController {

    public enum State{
        NORMAL, ERROR, EMPTY
    }

    private View errorView;
    private View emptyView;

    State state = State.NORMAL;

    public StateViewController(View rootView) {
        errorView = rootView.findViewById(R.id.errorView);
        emptyView = rootView.findViewById(R.id.emptyView);
        if(errorView==null || emptyView==null)
            throw new IllegalStateException("需要有两个id, R.id.emptyView 和 R.id.errorView");
        setState(State.NORMAL);
    }

    public void setState(State state) {
        switch (state) {
            case ERROR:
                if (errorView!=null) errorView.setVisibility(View.VISIBLE);
                if (emptyView!=null) emptyView.setVisibility(View.GONE);
                break;
            case EMPTY:
                if (errorView!=null) errorView.setVisibility(View.GONE);
                if (emptyView!=null) emptyView.setVisibility(View.VISIBLE);
                break;
            case NORMAL:
            default:
                if (errorView!=null) errorView.setVisibility(View.GONE);
                if (emptyView!=null) emptyView.setVisibility(View.GONE);
                break;
        }
    }

    public void setOnErrorViewClickListener(View.OnClickListener onClickListener){
        if (errorView!=null) errorView.setOnClickListener(onClickListener);
    }

    public void setOnEmptyViewClickListener(View.OnClickListener onClickListener){
        if (emptyView!=null) emptyView.setOnClickListener(onClickListener);
    }
}
