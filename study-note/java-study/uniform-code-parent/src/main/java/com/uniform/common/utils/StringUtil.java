package com.uniform.common.utils;


import java.io.*;
import java.nio.charset.Charset;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/4
 */
public class StringUtil {

    public static boolean isBlank(String string) {
        return null == string || "".equals(string);
    }

    public static boolean isNotBlank(String string) {
        return !isBlank(string);
    }

    /**
     * 获取文件后缀名  eg: '.txt'
     */
    public static String getFileSuffix(String fileName) {
        if (!fileName.isEmpty()) {
            int lastIndexOf = fileName.lastIndexOf(".");
            return fileName.substring(lastIndexOf);
        }
        return "";
    }

    /**
     * 首字母转小写
     */
    public static String lowerFirst(String word) {
        if (Character.isLowerCase(word.charAt(0))) {
            return word;
        } else {
            return String.valueOf(Character.toLowerCase(word.charAt(0))) + word.substring(1);
        }
    }

    /**
     * 首字母转大写
     */
    public static String upperFirst(String word) {
        if (Character.isUpperCase(word.charAt(0))) {
            return word;
        } else {
            return String.valueOf(Character.toUpperCase(word.charAt(0))) + word.substring(1);
        }
    }

    public static String getExceptionInfo(Throwable throwable) throws IOException {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String exceptionErr = throwable.getCause().toString();
        try {
            throwable.printStackTrace(pw);
        } finally {
            pw.close();
        }
        String exceptionNAme = exceptionErr.substring(exceptionErr.lastIndexOf(".") + 1, exceptionErr.lastIndexOf(":"));
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(sw.toString().getBytes(Charset.forName("utf8"))), Charset.forName("utf8")));
        String line;
        StringBuffer strbuf = new StringBuffer();
        while ((line = br.readLine()) != null) {
            if (!line.trim().equals("")) {
               if(line.contains("Caused by:"))
                strbuf.append(line.substring(line.lastIndexOf(":")));
            }
        }
        System.out.println(strbuf.toString());
        return exceptionNAme + "{" +strbuf.toString() +"}";
    }
}
