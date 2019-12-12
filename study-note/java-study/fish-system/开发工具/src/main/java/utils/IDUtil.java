package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * ID工具类
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/11
 */
public class IDUtil {

    /**
     * 格式：UUID去掉‘-’
     *
     * @return
     */
    public static String baseUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 格式：idyyyyMMddhhmmss{x}n
     *
     * @return
     */
    public static String randomID(int n) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = sdf.format(new Date());
        return "id" + date + StringUtil.randomString(n);
    }
}
