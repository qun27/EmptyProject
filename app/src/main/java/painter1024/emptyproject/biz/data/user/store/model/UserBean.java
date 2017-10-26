package painter1024.emptyproject.biz.data.user.store.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 用户数据模型
 */

public class UserBean implements Serializable{

    /**
     * "uid": "10",
     * "cellphone": "13242056839",
     * "username": "小哦6839",
     * "sex": "0",
     * "header": "./static/uploads/images/header/default.png",
     */
    @SerializedName("uid")
    private int uid;//用户id
    @SerializedName("token")
    private String token;//用户唯一token.
    @SerializedName("cellphone")
    private String cellphone;//用户手机号码
    @SerializedName("username")
    private String username;//用户昵称
    @SerializedName("sex")
    private int sex;//用户性别（0：未选择，1：男，2:女）
    @SerializedName("header")
    private String header;//用户头像

    @Override
    public String toString() {
        return "UserBean{" +
                "uid=" + uid +
                ", cellphone='" + cellphone + '\'' +
                ", username='" + username + '\'' +
                ", sex=" + sex +
                ", header='" + header + '\'' +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
