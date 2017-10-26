package painter1024.emptyproject.project.ui.base.widget.recycler;

import android.view.View;

import painter1024.emptyproject.core.ui.base.widget.recycler.BaseRecyclerAdapter;

/**
 * 所有RecyclerView适配器的基础类，只添加所有适配器中都需要的代码
 */

public abstract class MyRecyclerAdapter<VH extends MyRecyclerAdapter.ViewHolder> extends BaseRecyclerAdapter<VH> {

    //TODO footer 项目样式的页脚等

    public static abstract class ViewHolder extends BaseRecyclerAdapter.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
