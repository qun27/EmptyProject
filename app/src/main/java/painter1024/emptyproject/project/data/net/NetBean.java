package painter1024.emptyproject.project.data.net;

import java.io.Serializable;

/**
 * 网络数据模型类
 */

public class NetBean<T> implements Serializable{
    private int status;
    public String info;
    public T data;

    /**
     * 状态是否OK
     * @return 如果OK返回true
     */
    public boolean isOk(){
        return status == 0;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NetBean{" +
                "data=" + data +
                ", status=" + status +
                ", info='" + info + '\'' +
                '}';
    }
}
