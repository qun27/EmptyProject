package painter1024.emptyptoject.lib_android.util.device.info;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.util.UUID;

import painter1024.emptyptoject.lib_android.util.SDKVersionUtil;


/**
 * UDID工具
 */
public class UDIDUtil {

    private static final String PREFS_FILE = "device_id.xml";
    private static final String PREFS_DEVICE_ID = "device_id";
    private static UUID uuid;

    /**
     * 获取设备ID
     * @return 设备唯一标识
     */
    public static String getUDID(Context context){
        return uuid(context);
    }

    private static String uuid(Context cxt) {
        if (uuid == null) {
            final SharedPreferences prefs = cxt.getSharedPreferences(PREFS_FILE, 0);
            final String id = prefs.getString(PREFS_DEVICE_ID, null);

            if (id != null) {
                uuid = UUID.fromString(id);
            } else {
                final String androidID = Settings.Secure.getString(cxt.getContentResolver(), Settings.Secure.ANDROID_ID);

                try {
                    //部分厂商定制出现相同的 ANDROID_ID：9774d56d682e549c
                    if (!"9774d56d682e549c".equals(androidID)) {
                        uuid = UUID.nameUUIDFromBytes(androidID.getBytes("utf8"));
                    } else {
                        if (SDKVersionUtil.hasM()) {
                            uuid = UUID.randomUUID();
                        } else {
                            //6.0以上系统需要动态权限，用该方式会崩溃
                            final String deviceID = ((TelephonyManager) cxt.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                            uuid = deviceID != null ? UUID.nameUUIDFromBytes(deviceID.getBytes("utf8")) : UUID.randomUUID();
                        }
                    }
                } catch (Exception e) {
                    uuid = UUID.randomUUID();
//                    throw new RuntimeException(e);
                }

                prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString()).commit();
            }
        }

        return uuid.toString().replace("-", "");
    }

//    /**
//     * 获取设备ID
//     * @return
//     */
//    public static String getUDID(){
//        final TelephonyManager tm = (TelephonyManager) BaseApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
//
//        final String tmDevice, tmSerial, androidId;
//        tmDevice = "" + tm.getDeviceId();
//        tmSerial = "" + tm.getSimSerialNumber();
//        androidId = "" + android.provider.Settings.Secure.getString(BaseApplication.getInstance().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
//        LogUtil.d("tmDevice =" + tm.getDeviceId()+ " tmSerial =" + tm.getSimSerialNumber());
//        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
//
//        String uniqueId = deviceUuid.toString();
//
//
//        return uniqueId;
//    }
}
