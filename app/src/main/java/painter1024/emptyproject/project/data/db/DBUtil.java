package painter1024.emptyproject.project.data.db;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * 数据库工具
 */

public class DBUtil {

    public static <D extends Dao<T, ?>, T> D getDao(Class<T> clazz) throws SQLException {
        return OrmLiteDBClient.getInstance().getDao(clazz);
    }
}
