package painter1024.emptyproject.core.ui.base.component_ex.recycler.paging;

/**
 * 分页相关工具
 */

public class PagingUtil {
    public static boolean isLastPage(boolean hasNextPage, int itemCount) {
        return !hasNextPage || itemCount < 15;
    }
}
