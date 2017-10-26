package painter1024.emptyptoject.lib_android.ui.widget.ex.recycler_view;


import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by linxiaowu on 2017/3/2.
 * RecyclerView分割线
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {


    private Drawable drawable;
    private VisibilityProvider visibilityProvider;
    private int leftMargin;


    public DividerItemDecoration(Drawable drawable) {
        this.drawable = drawable;
    }

    private DividerItemDecoration(Builder builder) {
        this.drawable = builder.drawable;
        this.visibilityProvider = builder.visibilityProvider;
        this.leftMargin = builder.leftMargin;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();


        int childCount = parent.getChildCount();

        //1，第一条线不画，
        //2，顶部画线。
        for (int i = 1; i < childCount; i++) {

            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int position = params.getViewLayoutPosition();

            if (visibilityProvider != null && visibilityProvider.shouldHideDivider(position, parent))
                continue;

            int top = child.getTop() + params.topMargin;
            int bottom = top + drawable.getIntrinsicHeight();

            drawable.setBounds(left + leftMargin, top, right, bottom);
            drawable.draw(c);
        }

    }


    /**
     * 隐藏item分割线提供者
     */
    public interface VisibilityProvider {

        /**
         * @param position item位置
         * @param parent   RecyclerView
         * @return true 如果该间距需要被隐藏
         */
        boolean shouldHideDivider(int position, RecyclerView parent);
    }


    public static class Builder {
        private Drawable drawable;
        private int leftMargin;
        private VisibilityProvider visibilityProvider;


        public Builder(Drawable drawable) {
            this.drawable = drawable;
        }

        public Builder setLeftMargin(int leftMargin) {
            this.leftMargin = leftMargin;
            return this;
        }

        public Builder setVisibilityProvider(VisibilityProvider visibilityProvider) {
            this.visibilityProvider = visibilityProvider;
            return this;
        }

        public DividerItemDecoration build() {
            return new DividerItemDecoration(this);
        }

    }


}
