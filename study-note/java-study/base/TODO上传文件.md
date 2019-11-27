
step:
0. 文件实体类记录文件信息,MD5/SHA1/保存路径/大小/名称等

1. 获取文件信息，生成相应的保存路径, 创建上传文件实体对象

2. 文件格式支持判断

3. 根据SHA1判断文件是否已经存在

4. 保存文件到系统目录

5. 保存文件信息到数据库

```java
package com.sbot.common.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.net.URLEncoder;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/11/26
 */
public class FileUtil {
    public static String templatePath = "C:\\\\template";
    public static String uploadPath = "C:\\\\upload";
    /**
     * 创建一个文件
     *
     * @return
     * @throws IOException
     */
    public static String createFile(String suffix) throws IOException {
        String fileName = ToolUtil.randomID35() + "." + suffix;
        String fileUrl = templatePath + File.separator + fileName;
        File filePath = new File(templatePath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        File file = new File(fileUrl);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file.getAbsolutePath();
    }

    /**
     * 删除一个文件
     *
     * @param absolutePath
     * @return
     */
    public static boolean deleteFile(String absolutePath) {
        File file = new File(absolutePath);
        if (file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    /**
     * 在目录下添加多个文件
     * @param dir
     * @param filenames
     */
    public static void addFile(String dir, String... filenames) throws IOException {
        File dirFile = new File(dir);
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        for (String filename : filenames) {
            File file = new File(filename);
            if(!file.exists()){
                file.createNewFile();
            }
        }
    }

    /**
     * 在目录下追加多级目录，最后再追加一个文件
     * @param dir
     * @param filename
     * @param dirChilds
     */
    public static String appendFile(String dir, String filename, String... dirChilds) throws IOException {
        StringBuffer sf = new StringBuffer();
        sf.append(dir);
        for (String child : dirChilds) {
            sf.append(File.separator);
            sf.append(child);
        }
        File dirFile = new File(sf.toString());
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        sf.append(filename);
        File file = new File(sf.toString());
        if(!file.exists()){
            file.createNewFile();
        }
        return file.getAbsolutePath();
    }

    /**
     * 文件下载（单个）
     */
    public static void downloadFile(String sourceName, String rename) throws IOException {
        File sourceFile = new File(sourceName);
        if (!sourceFile.exists() || !sourceFile.isFile()) {
            return;
        }
        ServletContext servletContext = HttpUtil.getRequest().getServletContext();
        //获取文件的MimeType类型
        String mimeType = servletContext.getMimeType(sourceName);
        //设置文件的输出类型
        HttpUtil.getResponse().setContentType(mimeType);
        // 确定文件是内嵌或弹出下载框
        String filename = URLEncoder.encode(rename + ToolUtil.getFileSuffix(sourceName), HttpUtil.ENCODING_GBK);
        HttpUtil.getResponse().addHeader(HttpUtil.CONTENT_DISCRIPTION, HttpUtil.ATTACHMENT_FILENAME_EQ + filename);
        // 通过Response域，创建Servlet的输出流，输出文件
        byte[] buffer = new byte[1024];
        FileInputStream fis = new FileInputStream(sourceFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        OutputStream os = HttpUtil.getResponse().getOutputStream();
        int size = bis.read(buffer);
        while (size != -1) {
            os.write(buffer, 0, size);
            size = bis.read(buffer);
        }
        //关流，response获得流会自动关闭，因此也可以不用手动关
        os.close();
        bis.close();
        fis.close();
        deleteFile(sourceName);
    }

    /**
     * 文件上传（单个），保存格式：uploadPath + /suffix/yyyy-MM-dd-xxxxxxxxxxxxxx.suffix
     */
    public static String uploadFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty())
            return null;
        String originalFilename = multipartFile.getOriginalFilename();
        String suffix = ToolUtil.getFileSuffix(originalFilename);
        String newFilename = ToolUtil.randomID35() + suffix;
        String fileUrl = appendFile(uploadPath, newFilename, suffix.replace(".", ""));
        File dest = new File(fileUrl);
        if (!dest.exists())
            dest.createNewFile();
        multipartFile.transferTo(dest);
        return dest.getAbsolutePath();
    }


}
```
