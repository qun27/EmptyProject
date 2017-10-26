package painter1024.emptyproject.biz.data.user.store.sp;

import android.content.Intent;

import painter1024.emptyproject.app.MyApplication;
import painter1024.emptyproject.project.data.sp.SPClient;
import painter1024.emptyproject.biz.data.user.store.model.UserBean;


/**
 * 用户轻量存储
 */

public class UserSP {

    /**
     * 常量命名方式： 类型（KEY） + 描述
     * 存放方式，按照属性区分
     */
    // 用户id
    private static String KEY_USER_ID = "KEY_USER_ID";
    public static int getUserId() {
        return SPClient.sp().getInt(KEY_USER_ID, -1);
    }
    public static void putUserId(int userId) {
        SPClient.putInt(KEY_USER_ID, userId);
    }

    // token
    private static String KEY_TOKEN = "KEY_TOKEN";
    public static String getToken() {
        return SPClient.sp().getString(KEY_TOKEN, "");
    }
    public static void putToken(String token) {
        SPClient.putString(KEY_TOKEN, token);
    }

    // 手机号
    public static String KEY_CELLPHONE = "KEY_CELLPHONE";
    public static String getCellphone() {
        return SPClient.sp().getString(KEY_CELLPHONE, "");
    }
    public static void putCellphone(String cellphone) {
        SPClient.putString(KEY_CELLPHONE, cellphone);
        Intent intent = new Intent(KEY_CELLPHONE);
        MyApplication.getInstance().sendBroadcast(intent);
    }

    // 用户名
    public static String KEY_USER_NAME = "KEY_USER_NAME";
    public static String getUserName() {
        return SPClient.sp().getString(KEY_USER_NAME, "");
    }
    public static void putUserName(String name) {
        SPClient.putString(KEY_USER_NAME, name);
        Intent intent = new Intent(KEY_USER_NAME);
        MyApplication.getInstance().sendBroadcast(intent);
    }

    // 性别
    public static String KEY_SEX = "KEY_SEX";
    public static int getSex() {
        return SPClient.sp().getInt(KEY_SEX, 0);
    }
    public static void putSex(int sex) {
        SPClient.putInt(KEY_SEX, sex);
        Intent intent = new Intent(KEY_SEX);
        MyApplication.getInstance().sendBroadcast(intent);
    }

    // 头像
    public static String KEY_HEADER = "KEY_HEADER";
    public static String getHeader() {
        return SPClient.sp().getString(KEY_HEADER, "");
    }
    public static void putHeader(String header) {
        SPClient.putString(KEY_HEADER, header);
        Intent intent = new Intent(KEY_HEADER);
        MyApplication.getInstance().sendBroadcast(intent);
    }

    // 密码
    private static String KEY_PWD = "KEY_PWD";
    public static String getPwd() {
        return SPClient.sp().getString(KEY_PWD, "");
    }
    public static void putPwd(String pwd) {
        SPClient.putString(KEY_PWD, pwd);
    }

    // 用户Bean
    public static void putUserBean(UserBean bean){
        putUserId(bean.getUid());
        putToken(bean.getToken());
        putCellphone(bean.getCellphone());
        putUserName(bean.getUsername());
        putSex(bean.getSex());
        putHeader(bean.getHeader());
    }
    public static UserBean getUserBean(){
        UserBean bean = new UserBean();
        bean.setUid(getUserId());
        bean.setToken(getToken());
        bean.setCellphone(getCellphone());
        bean.setUsername(getUserName());
        bean.setSex(getSex());
        bean.setHeader(getHeader());
        return bean;
    }

}
