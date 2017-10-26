package painter1024.emptyproject.core.ui.base.widget.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 所有RecyclerView适配器的基础类，只添加所有适配器中都需要的代码
 */

public abstract class BaseRecyclerAdapter<VH extends BaseRecyclerAdapter.ViewHolder> extends RecyclerView.Adapter<VH>{

    //TODO footer

    public static abstract class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
