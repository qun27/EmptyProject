package painter1024.emptyptoject.lib_android.ui.widget.ex.recycler_view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.ArrayList;

/**
 * 装饰适配器，为适配器增加添加头和尾部的功能
 * @deprecated 自主定义都类型适配器，position存在bug
 */
public class DecorateAdapter extends RecyclerView.Adapter {

    private boolean DEBUG = false;
    private String TAG = DecorateAdapter.class.getSimpleName();

    private final RecyclerView.Adapter mAdapter;
    private final ArrayList<FixedViewInfo> mHeaderViewInfos;
    private final ArrayList<FixedViewInfo> mFooterViewInfos;

    //our header/footer RecyclerView.ViewHolder
    public static class HeaderFooterViewHolder extends RecyclerView.ViewHolder{
        public HeaderFooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class FixedViewInfo {
        /** The view to add to the list */
        public View view;
        /** The data backing the view. This is returned from {@link ListAdapter#getItem(int)}. */
//        public Object data;
        /** <code>true</code> if the fixed view should be selectable in the list */
//        public boolean isSelectable;
        int type;
    }

    /**
     *
     * @param headerViewInfos 正序，列表位置和position正比增长
     * @param footerViewInfos 正序，列表位置和position正比增长
     * @param adapter
     */
    public DecorateAdapter(ArrayList< FixedViewInfo> headerViewInfos,
                           ArrayList<FixedViewInfo> footerViewInfos, RecyclerView.Adapter adapter) {
        mAdapter = adapter;

        if (headerViewInfos == null) {
            mHeaderViewInfos = new ArrayList<FixedViewInfo>();;
        } else {
            mHeaderViewInfos = headerViewInfos;
        }

        if (footerViewInfos == null) {
            mFooterViewInfos = new ArrayList<FixedViewInfo>();;
        } else {
            mFooterViewInfos = footerViewInfos;
        }

        if (adapter!=null){
            //该适配器的事件监听和传递
            adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                public void onChanged() {
                    notifyDataSetChanged();
                }
                public void onItemRangeChanged(int positionStart, int itemCount) {
                    notifyItemRangeChanged(positionStart + getHeadersCount(), itemCount);
                }
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    notifyItemRangeInserted(positionStart + getHeadersCount(), itemCount);
                }
                public void onItemRangeRemoved(int positionStart, int itemCount) {
                    notifyItemRangeRemoved(positionStart + getHeadersCount(), itemCount);
                }
                public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                    notifyItemMoved(fromPosition + getHeadersCount(), toPosition+getHeadersCount());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return getFootersCount() + getHeadersCount() + getAdapterItemCount();
    }

    //adapter
    public int getAdapterItemCount(){
        return mAdapter==null ? 0 : mAdapter.getItemCount();
    }

    //header
    public int getHeadersCount() {
        return mHeaderViewInfos.size();
    }

    public boolean isHeaderPosition(int position){
        return(position >= 0 && position < getHeadersCount());
    }

    private int getHeaderType(int position){
        return Integer.MIN_VALUE + position;
    }

    public boolean isHeaderType(int type){
        return(type>= Integer.MIN_VALUE && type < Integer.MIN_VALUE +getHeadersCount());
    }

    private int getHeaderIndex(int type){
        return type - Integer.MIN_VALUE;
    }

    //footer
    public int getFootersCount() {
        return mFooterViewInfos.size();
    }

    public boolean isFooterPosition(int position){
         return(position >= getHeadersCount() + getAdapterItemCount()  &&  position < getItemCount());
    }

    private int getFooterType(int position){
        return Integer.MAX_VALUE - (position - getHeadersCount() - getAdapterItemCount());
    }

    public boolean isFooterType(int type){
        return(type <= Integer.MAX_VALUE && type > Integer.MAX_VALUE - getFootersCount());
    }

    private int getFooterIndex(int type){
        return Integer.MAX_VALUE - type;
    }

    @Override
    public int getItemViewType(int position) {
        //check what type our position is, based on the assumption that the order is headers > items > footers
        int type = 0;
        if(isHeaderPosition(position)){
            type = getHeaderType(position);
            if(DEBUG) Log.i(TAG, "getItemViewType getHeaderType : " + type);
            return type;
        }

        if(isFooterPosition(position)){
            type = getFooterType(position);
            if(DEBUG) Log.i(TAG, "getItemViewType getFooterType : " + type);
            return type;
        }

        type = mAdapter.getItemViewType(position - getHeadersCount());
        if(isHeaderType(type) || isFooterType(type)){
            throw new IllegalArgumentException("Item type eqale header or footer : " + type);
        }
        return type;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        if(isHeaderType(type)) {
            FixedViewInfo info = mHeaderViewInfos.get(getHeaderIndex(type));
            return new HeaderFooterViewHolder(info.view);
        }else if(isFooterType(type)){
            FixedViewInfo info = mFooterViewInfos.get(getFooterIndex(type));
            return new HeaderFooterViewHolder(info.view);
        }else{
            return mAdapter.onCreateViewHolder(viewGroup, type);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder vh, int position) {
        //如果是头尾的位置，则不需要在进行绑定
        if(isHeaderPosition(position) || isFooterPosition(position)){
            return;
        }else {
            //it's one of our items, display as required
            mAdapter.onBindViewHolder(vh, position - getHeadersCount());
        }
    }

    /**
     * add a header to the adapter if no contains
     */
    public void addHeader(View header){
        if(containsFixedViewInfo(header, mHeaderViewInfos)){
            //如果已经包含该VIEW 直接返回
            return;
        }
        FixedViewInfo info = new FixedViewInfo();
        info.view = header;
        mHeaderViewInfos.add(info);
        //animate
        notifyItemInserted(mHeaderViewInfos.size() - 1);
    }

    /**
     * remove a header from the adapter
     */
    public void removeHeader(View header){
        int index = removeFixedViewInfo(header, mHeaderViewInfos);
        //animate
        notifyItemRemoved(index);
    }

    /**
     *  add a footer to the adapter
     * @param footer
     */
    public void addFooter(View footer){
        if(containsFixedViewInfo(footer, mFooterViewInfos)){
            //如果已经包含该VIEW 直接返回
            return;
        }
        FixedViewInfo info = new FixedViewInfo();
        info.view = footer;
        mFooterViewInfos.add(info);
        //animate
        notifyItemInserted(getItemCount()-1);
    }

    /**
     * remove a footer from the adapter
     */
    public void removeFooter(View footer){
        int index = removeFixedViewInfo(footer, mFooterViewInfos);
        //if del faild , return
        if(index==-1) return;
        //animate
        notifyItemRemoved(getHeadersCount() + getAdapterItemCount() + index);
    }

    /**
     * get content adapter's reality position
     * @param position content adapter's position
     * @return reality position of content adapter
     */
    public int getRealityPosition(int position){
        int ret = getHeadersCount() + position;
        if(ret>getItemCount()) {
            throw new IllegalArgumentException("position " + position + " reality position" + ret
                    + " large then item count " + getItemCount());
        }
        return ret;
    }

    /**
     * check FixedViewInfo list contains index view
     * @param v index view
     * @param where FixedViewInfo list
     * @return if contains return true,otherwise false;
     */
    private boolean containsFixedViewInfo(View v, ArrayList<FixedViewInfo> where) {
        int len = where.size();
        for (int i = 0; i < len; ++i) {
            FixedViewInfo info = where.get(i);
            if (info.view == v) {
                return true;
            }
        }
        return false;
    }

    /**
     * remove view form index FixedViewInfo list, if contains
     * @param v index view
     * @param where FixedViewInfo list
     * @return if success return index,otherwise -1
     */
    private int removeFixedViewInfo(View v, ArrayList<FixedViewInfo> where) {
        int len = where.size();
        for (int i = 0; i < len; ++i) {
            FixedViewInfo info = where.get(i);
            if (info.view == v) {
                where.remove(i);
                return i;
            }
        }
        return -1;
    }

}
