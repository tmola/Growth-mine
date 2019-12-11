package fish.util;

import java.math.BigDecimal;
import java.util.Random;

/**
 * String工具类
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/11
 */
public class StringUtil {

    /**
     * 字符串文本是否为空(NULL或者''或者只包含space)
     */
    public static Boolean isBlank(String text) {
        if (null == text || text.trim().isEmpty()) return true;
        return false;
    }

    /**
     * 字符串文本是否非空
     */
    public static Boolean isNotBlank(String text) {
        return !isBlank(text);
    }

    /**
     * 首字母转小写
     */
    public static String lowerFirst(String text) {
        if (Character.isLowerCase(text.charAt(0))) return text;
        return String.valueOf(Character.toLowerCase(text.charAt(0))) + text.substring(1);
    }

    /**
     * 首字母转小写,其余转大写
     */
    public static String lowerFirstUpperOther(String text) {
        return String.valueOf(Character.toLowerCase(text.charAt(0))) + text.substring(1).toUpperCase();
    }

    /**
     * 首字母转大写
     */
    public static String upperFirst(String text) {
        if (Character.isUpperCase(text.charAt(0))) return text;
        return String.valueOf(Character.toUpperCase(text.charAt(0))) + text.substring(1);
    }

    /**
     * 首字母转大写,其余转小写
     */
    public static String upperFirstLowerOther(String text) {
        return String.valueOf(Character.toUpperCase(text.charAt(0))) + text.substring(1).toLowerCase();
    }

    /**
     * String类型转化为指定小数位的double类型
     * 注：使用BigDecimal.ROUND_HALF_UP四舍五入
     */
    public static double toDouble(String num, int n) {
        Double number = Double.parseDouble(num);
        BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.setScale(n, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * String类型转化为int类型
     */
    public static int toInt(String num) {
        return Integer.parseInt(num);
    }

    /**
     * n位随机字符串
     */
    public static String randomString(int n) {
        if (n <= 0) return "";
        int i = 0;
        StringBuffer rStr = new StringBuffer();
        Random random = new Random();
        while (i < n) {
            int number = random.nextInt('z' + 1);
            if ((number >= '0' && number <= '9') || (number >= 'A' && number <= 'Z')
                    || (number >= 'a' && number <= 'z')) {
                rStr.append((char) number);
                i++;
            }
        }
        return rStr.toString();
    }
}
