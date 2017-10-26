package painter1024.emptyproject.project.ui.ex.tool.error_handle;

/**
 * 异常处理
 */

public class ErrorHandler {
    public static void handle(Throwable throwable) {
        ApiRequestErrorHandler.handleNotGlobalError(throwable);
        //... 其他异常处理
    }
}
