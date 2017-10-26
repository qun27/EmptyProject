package painter1024.emptyptoject.lib.util.biz;


/**
 * 分页帮助类, 默认起始页码为1
 * <br/>
 * <br/>
 * 说明：<br/>
 * 当前页面如果有下一页，则 nextPage = page+1;<br/>
 * 如果没有下一个，则 nextPage = page;<br/>
 */
public class PagingHelper {
    public static final int START_PAGE_DEF = 1; //默认起始页码
    private int startPage = START_PAGE_DEF;
    private int page = START_PAGE_DEF;
    private int nextPage = page + 1;

    public PagingHelper() {
    }

    public PagingHelper(int startPage) {
        this.startPage = startPage;
        setPage(startPage, true);
    }

    /**
     * 获得下一页页码
     */
    public int getNextPage() {
        return nextPage;
    }

    /**
     * 获得起始页码
     * @return 获得起始页码
     */
    public int getStartPage() {
        return startPage;
    }

    /**
     * 翻页
     * @param loadingPage 正在加载的页面
     * @param hasNextPage 是否有更多数据
     */
    public void flip(int loadingPage, boolean hasNextPage){
        if(isStartPage(loadingPage)){
            resetSuccess(hasNextPage);
        }else{
            flipSuccess(hasNextPage);
        }
    }

    /**
     * 提供的页面是否是起始页
     * @return 如果处于起始页返回true
     */
    public boolean isStartPage(int page){
        return startPage == page;
    }

    /**
     * 重置成功时调用
     * @param hasNextPage 是否还有下一页
     */
    private void resetSuccess(boolean hasNextPage){
        setPage(startPage, hasNextPage);
    }

    /**
     * 翻页成功时调用
     * @param hasNextPage 是否还有下一页
     */
    private void flipSuccess(boolean hasNextPage){
        setPage(page+1, hasNextPage);
    }

    /**
     * 设置页面
     * @param page 页面
     */
    private void setPage(int page, boolean hasNextPage) {
        this.page = page;
        this.nextPage = this.page;
        if(hasNextPage) this.nextPage++;
    }
}
