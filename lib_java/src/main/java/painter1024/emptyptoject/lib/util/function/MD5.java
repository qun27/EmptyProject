package painter1024.emptyptoject.lib.util.function;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import painter1024.emptyptoject.lib.util.calculate.HexUtil;


/**
 * MD5工具类
 */
public class MD5 {

	/**
	 * 是否为小写
	 */
	private static boolean TO_LOWER_CASE_DEF = true;

	private MD5() {}


	/**
	 * 获取信息摘要，调用{@link #getMessageDigest(String, String)} , utf-8字符集
	 * @param str 文本信息
	 * @return 信息摘要
	 */
	public static String getMessageDigest(String str) {
		return getMessageDigest(str, TO_LOWER_CASE_DEF);
	}

	/**
	 * 获取信息摘要, utf-8字符集，默认小写
	 *
	 * @param str 文本信息
	 * @param toLowCase 是否小写
	 * @return 信息摘要
	 */
	public static String getMessageDigest(String str, boolean toLowCase) {

		try {
			return getMessageDigest(str.getBytes("utf-8"), toLowCase);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取信息摘要，默认小写
	 *
	 * @param str 文本信息
	 * @param charSet 字符集
	 * @return 信息摘要
	 */
	public static String getMessageDigest(String str, String charSet) throws UnsupportedEncodingException {
		return getMessageDigest(str.getBytes(charSet), TO_LOWER_CASE_DEF);
	}
	/**
	 * 获取信息摘要
	 *
	 * @param str 文本信息
	 * @param charSet 字符集
	 * @return 信息摘要
	 */
	public static String getMessageDigest(String str, String charSet, boolean toLowCase) throws UnsupportedEncodingException {
		return getMessageDigest(str.getBytes(charSet), toLowCase);
	}

	/**
	 * 获取信息摘要，调用{@link #getMessageDigest(byte[], boolean)} ，
	 * toLowerCase参数为false
	 *
	 * @param buffer 信息数据集
	 * @return 信息摘要
	 */
	public static String getMessageDigest(byte[] buffer) {
		return getMessageDigest(buffer, TO_LOWER_CASE_DEF);
	}

	/**
	 * 获取信息摘要
	 *
	 * @param buffer 信息数据集
	 * @param toLowerCase 是否为小写
     * @return 信息摘要
     */
	public static String getMessageDigest(byte[] buffer, boolean toLowerCase) {

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(buffer);
			byte[] md = mdTemp.digest();

			return HexUtil.encodeHexStr(md, toLowerCase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
