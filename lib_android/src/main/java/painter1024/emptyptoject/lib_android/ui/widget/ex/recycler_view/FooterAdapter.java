package painter1024.emptyptoject.lib_android.ui.widget.ex.recycler_view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.SoftReference;

/**
 * 带有底部的适配器
 */

public class FooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int FOOTER_VIEW_TYPE = 10086;

    private int footerViewId;
    private final RecyclerView.Adapter adapter;

    private MyAdapterDataObserver myAdapterDataObserver;

    private FooterCallback footerCallback;

    public FooterAdapter(@NonNull RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        myAdapterDataObserver = new MyAdapterDataObserver(new SoftReference<RecyclerView.Adapter>(this));
        this.adapter.registerAdapterDataObserver(myAdapterDataObserver);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOTER_VIEW_TYPE) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(footerViewId, parent, false);
            onFooterCreate(view);
            return new FooterVH(view);
        } else {
            return adapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterVH) {
            FooterVH footerVH = (FooterVH) holder;
            onFooterBind(footerVH.itemView);
        } else {
            adapter.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position < getItemCountOfAdapter())
            return adapter.getItemViewType(position);
        return FOOTER_VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        return getItemCountOfAdapter() + getItemCountOfFooter();
    }

    public int getItemCountOfAdapter(){
        return adapter == null ? 0 : adapter.getItemCount();
    }

    public int getItemCountOfFooter(){
        return footerViewId == 0 ? 0 : 1;
    }

    //========相关设置方法==========

    /**
     * 设置页脚
     * @param footerViewId 页脚id ， 0为无
     */
    public void setFooterView(int footerViewId) {
        if ( this.footerViewId == footerViewId) return;
        this.footerViewId = footerViewId;
        notifyDataSetChanged();
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public FooterCallback getFooterCallback() {
        return footerCallback;
    }

    public void setFooterCallback(FooterCallback footerCallback) {
        this.footerCallback = footerCallback;
    }


    //========相关设置方法 end ==========

    //========相关生命传递 ==========

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (adapter!=null)adapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (adapter!=null) adapter.onDetachedFromRecyclerView(recyclerView);
    }

    //========相关生命传递 end ==========

    private void onFooterCreate(View footer) {
        if (footerCallback != null) footerCallback.onFooterCreate(footer);
    }

    private void onFooterBind(View footer) {
        if (footerCallback != null) footerCallback.onFooterBind(footer);
    }


    private static class MyAdapterDataObserver extends RecyclerView.AdapterDataObserver {

        private final SoftReference<RecyclerView.Adapter> adapterSoftReference;

        MyAdapterDataObserver(SoftReference<RecyclerView.Adapter> adapterSoftReference) {
            this.adapterSoftReference = adapterSoftReference;
        }

        public void onChanged() {
            if (adapterSoftReference!=null && adapterSoftReference.get()!=null)
                adapterSoftReference.get().notifyDataSetChanged();
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
            if (adapterSoftReference!=null && adapterSoftReference.get()!=null)
                adapterSoftReference.get().notifyItemRangeChanged(positionStart, itemCount);
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            if (adapterSoftReference!=null && adapterSoftReference.get()!=null)
                adapterSoftReference.get().notifyItemRangeInserted(positionStart, itemCount);
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            if (adapterSoftReference!=null && adapterSoftReference.get()!=null)
                adapterSoftReference.get().notifyItemRangeRemoved(positionStart, itemCount);
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            if (adapterSoftReference!=null && adapterSoftReference.get()!=null)
                adapterSoftReference.get().notifyItemMoved(fromPosition, toPosition);
        }
    }

    private static class FooterVH extends RecyclerView.ViewHolder {

        FooterVH(View itemView) {
            super(itemView);
        }
    }

    public interface FooterCallback {
        void onFooterCreate(View footer);
        void onFooterBind(View footer);
    }
}
