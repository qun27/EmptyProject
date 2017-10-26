package painter1024.emptyptoject.lib_android.util.storage.file;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 文件读取工具
 */

public class FileReadUtil {

    /**
     * 读取文件文本
     */
    public static String readText(String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path), 8192);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append("\n").append(line);
            }
            bufferedReader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从assets目录中读取文本文件的内容
     * @param context 上下文
     * @param name 文件名  好比如reader.txt
     * @return 文件的内容文本
     */
    public static String readTextFromAssets(Context context, String name) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = context.getAssets().open(name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null){
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
