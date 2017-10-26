package painter1024.emptyproject.biz.data.boot.store.model;

import java.io.Serializable;

/**
 * 启动页图片
 */

public class BootImageBean implements Serializable {
    public static final BootImageBean DEFAULT;

    static {
        DEFAULT = new BootImageBean();
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
