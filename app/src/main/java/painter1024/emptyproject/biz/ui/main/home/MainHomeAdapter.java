package painter1024.emptyproject.biz.ui.main.home;

import android.view.View;
import android.view.ViewGroup;

import painter1024.emptyproject.core.ui.base.widget.recycler.BaseRecyclerAdapter;

public class MainHomeAdapter extends BaseRecyclerAdapter<MainHomeAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends BaseRecyclerAdapter.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
