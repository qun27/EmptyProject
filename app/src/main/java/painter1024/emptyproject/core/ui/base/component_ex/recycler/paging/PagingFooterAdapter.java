package painter1024.emptyproject.core.ui.base.component_ex.recycler.paging;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import painter1024.emptyptoject.lib_android.ui.widget.ex.recycler_view.FooterAdapter;
import painter1024.emptyproject.R;
import painter1024.emptyproject.app.util.ResUtil;

/**
 * 分页页脚适配器
 */

public class PagingFooterAdapter extends FooterAdapter {

    private String text;

    public PagingFooterAdapter(@NonNull RecyclerView.Adapter adapter) {
        super(adapter);
        enable(true);
        setFooterCallback(new FooterCallback() {
            @Override
            public void onFooterCreate(View footer) {
                setFooterText(footer);
            }

            @Override
            public void onFooterBind(View footer) {
                setFooterText(footer);
            }
        });
    }

    /**
     * 设置当前是否是最后一页
     */
    public void setLastPage(boolean lastPage){
        if (lastPage) setText(R.string.last_page_footer);
        else setText(R.string.no_last_page_footer);
    }

    public void setText(int textId){
        setText(ResUtil.getString(textId));
    }

    public void setText(String text){
        this.text = text;
        notifyDataSetChanged();
    }

    public void enable(boolean enable){
        if (enable) {
            setFooterView(R.layout.core_item_paging_last_page_footer);
        } else {
            setFooterView(0);
        }
    }

    private void setFooterText(View footer) {
        if (footer instanceof TextView) {
            TextView textView = (TextView) footer;
            textView.setText(text);
        }
    }

}
