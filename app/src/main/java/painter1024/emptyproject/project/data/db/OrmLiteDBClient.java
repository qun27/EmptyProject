package painter1024.emptyproject.project.data.db;

import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

import painter1024.emptyproject.app.MyApplication;
import painter1024.emptyproject.app.util.LogUtil;


/**
 * OrmLiteDBClient
 */

public class OrmLiteDBClient extends OrmLiteSqliteOpenHelper {

    static class Handle{
         static OrmLiteDBClient INSTANCE = new OrmLiteDBClient();
    }

    private OrmLiteDBClient() {
        super(MyApplication.getInstance(), DBConstant.DB_NAME, null, DBConstant.DB_VERSION);
    }

    public static OrmLiteDBClient getInstance(){
        return Handle.INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        LogUtil.d("创建数据库");
//        try {
//            // TODO 创建数据表
//            TableUtils.createTable(connectionSource, CourseDetailBean.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        LogUtil.d("升级数据库");
//        try {
//            // TODO 升级数据表
//            TableUtils.dropTable(connectionSource, CourseDetailBean.class, true);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
