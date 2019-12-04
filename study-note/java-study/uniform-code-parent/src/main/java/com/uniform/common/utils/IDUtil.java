package com.uniform.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/4
 */
public class IDUtil {

    public static String randomID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String randomID35() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String id = UUID.randomUUID().toString();
        String date = sdf.format(new Date());
        return date + id.substring(9, 13) + id.substring(14, 18)
                + id.substring(19, 23) + id.substring(24);
    }

}
