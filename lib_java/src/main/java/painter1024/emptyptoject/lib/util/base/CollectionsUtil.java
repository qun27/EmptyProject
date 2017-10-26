package painter1024.emptyptoject.lib.util.base;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 集合相关工具
 */
public class CollectionsUtil {

    /**
     * 是否null或元素为空
     */
    public static <K,V>boolean isEmpty(Map<K, V> map){
        if(map==null || map.isEmpty()) return true;
        return false;
    }

    /**
     * 是否null或元素为空
     */
    public static <E>boolean isEmpty(Collection<E> collection){
        if(collection==null || collection.isEmpty()) return true;
        return false;
    }

    /**
     * 是否null或元素为空
     */
    public static <E>boolean isEmpty(E... collection){
        if(collection==null || collection.length==0) return true;
        return false;
    }

    /**
     * Map根据Key排序
     * @return 排序好的list
     */
    public static List<Map.Entry<String, String>> shortMapByKey(Map<String, String> args) {
        List<Map.Entry<String, String>> list = new ArrayList<>(args.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> entry1, Map.Entry<String, String> entry2) {
                return entry1.getKey().compareTo(entry2.getKey());
            }
        });
        return list;
    }


    /**
     * 深度复制方法
     * */
    public static <T> List<T> deepCopyList(List<T> src) throws ClassNotFoundException, IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

    /**
     * Returns a copy of the given list that is safe to iterate over and perform actions that may
     * modify the original list.
     *
     * <p> See #303 and #375. </p>
     */
    public static <T> List<T> getSnapshot(Collection<T> other) {
        if (isEmpty(other)) return new ArrayList<>();
        // toArray creates a new ArrayList internally and this way we can guarantee entries will not
        // be null. See #322.
        List<T> result = new ArrayList<T>(other.size());
        for (T item : other) {
            result.add(item);
        }
        return result;
    }

    /**
     * Returns a {@link java.util.Queue} of the given size using Glide's preferred implementation.
     */
    public static <T> Queue<T> createQueue(int size) {
        return new ArrayDeque<T>(size);
    }

    /**
     * array 转换为 list
     */
    public static <T>List<T> arrayToList(T... array) {
        return Arrays.asList(array);
    }
}
