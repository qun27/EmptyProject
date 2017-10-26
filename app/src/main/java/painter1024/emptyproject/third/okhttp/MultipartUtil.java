package painter1024.emptyproject.third.okhttp;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Multipart工具
 */

public class MultipartUtil {

    public static RequestBody convertToBody(File file){
        return RequestBody.create(MediaType.parse("multipart/form-data"), file);
    }

    public static MultipartBody.Part convertToPart(String key, File file){
        RequestBody imageBody = convertToBody(file);
        return MultipartBody.Part.createFormData(key, file.getName(), imageBody);
    }
}
