package com.glacier.modules.sys.utils;

import com.glacier.common.core.constant.CommonConstant;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件工具类
 *
 * @author glacier
 * @version 1.0
 * @date 2020-11-01 22:41
 */
public class FileUtils extends org.apache.commons.io.FileUtils {
    public static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    public static final String DOT = ".";
    private static final Pattern PATTERN = Pattern.compile("^bytes=([0-9]+)-([0-9]+)?$");
    
    private FileUtils() {

    }

    /**
     * 向浏览器发送文件下载，支持断点续传
     *
     * @param file     要下载的文件
     * @param request  请求对象
     * @param response 响应对象
     * @return 返回错误信息，无错误信息返回null
     */
    public static String downFile(File file, HttpServletRequest request, HttpServletResponse response) {
        return downFile(file, request, response, null);
    }

    /**
     * 向浏览器发送文件下载，支持断点续传
     *
     * @param file     要下载的文件
     * @param request  请求对象
     * @param response 响应对象
     * @param fileName 指定下载的文件名
     * @return 返回错误信息，无错误信息返回null
     */
    public static String downFile(File file, HttpServletRequest request, HttpServletResponse response, String fileName) {
        String error = null;
        if (file == null || !file.exists()) {
            error = "文件已丢失或不存在！";
            logger.debug("---------------" + file + " " + error);
            return error;
        }
        if (!file.isFile()) {
            error = "该文件是一个文件夹！";
            logger.debug("---------------" + file + " " + error);
            return error;
        }
        if (file.length() <= 0) {
            error = "该文件是一个空文件。";
            logger.debug("---------------" + file + " " + error);
            return error;
        }
        if (!file.canRead()) {
            error = "该文件没有读取权限。";
            logger.debug("---------------" + file + " " + error);
            return error;
        }

        // 记录文件大小
        long fileLength = file.length();
        // 记录已下载文件大小
        long pastLength = 0L;
        // 记录客户端需要下载的字节段的最后一个字节偏移量（比如bytes=27000-39000，则这个值是为39000）
        long toLength = 0L;
        // 客户端请求的字节总量
        long contentLength = 0L;
        // 负责读取数据
        RandomAccessFile raf = null;
        // 缓冲
        OutputStream out = null;
        WritableByteChannel outChannel = null;
        FileChannel inChannel = null;

        // 通知客户端允许断点续传，响应格式为：Accept-Ranges: bytes
        response.setHeader("Accept-Ranges", "bytes");
        String range = request.getHeader("Range");
        // 客户端请求的下载的文件块的开始字节
        if (range != null && !range.isEmpty()) {
            logger.debug("request.getHeader(\"Range\") = " + range);
            Matcher matcher = PATTERN.matcher(range);
            if (matcher.find()) {
                pastLength = Integer.parseInt(matcher.group(1));
                String endStr = matcher.group(2);
                if (StringUtils.isNotBlank(endStr)) {
                    toLength = Integer.parseInt(endStr);
                    // 修正数据
                    if (toLength >= fileLength) {
                        toLength = fileLength - 1;
                    }
                } else {
                    toLength = fileLength - 1;
                }
            }
            // 客户端请求的是 1275856879-1275877358 之间的字节
            contentLength = toLength - pastLength + 1;
            if (pastLength == 0L) {
                // 是从开始下载
                logger.debug("---------------是从开始进行下载！");
            }
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            // 针对 bytes=27000- 的请求
            response.setHeader("Content-Range",
                    "bytes " + pastLength + "-" + toLength + "/" + fileLength);
        } else {
            // 客户端要求全文下载
            contentLength = fileLength;
            // 是普通下载
            logger.debug("---------------是普通下载！");
        }
        long startTime = System.currentTimeMillis();
        try {
            response.addHeader("Content-Disposition",
                    "attachment; filename="
                            + URLEncoder.encode(StringUtils.isBlank(fileName) ? file.getName() : fileName, CommonConstant.CHARSET_UTF_8));
            // set the MIME type.
            response.setContentType(getContentType(file.getName()));
            response.addHeader("Content-Length", String.valueOf(contentLength));
            out = response.getOutputStream();
            raf = new RandomAccessFile(file, "r");
            outChannel = Channels.newChannel(out);
            inChannel = raf.getChannel();
            inChannel.transferTo(pastLength, contentLength, outChannel);
            out.flush();
            logger.debug("---------------下载完成！");
        } catch (IOException e) {
            /**
             * 在写数据的时候， 对于 ClientAbortException 之类的异常，
             * 是因为客户端取消了下载，而服务器端继续向浏览器写入数据时， 抛出这个异常，这个是正常的。
             * 尤其是对于迅雷这种吸血的客户端软件， 明明已经有一个线程在读取 bytes=1275856879-1275877358，
             * 如果短时间内没有读取完毕，迅雷会再启第二个、第三个。。。线程来读取相同的字节段， 直到有一个线程读取完毕，迅雷会 KILL
             * 掉其他正在下载同一字节段的线程， 强行中止字节读出，造成服务器抛 ClientAbortException。
             * 所以，我们忽略这种异常
             */
            logger.warn("提醒：向客户端传输时出现IO异常，但此异常是允许的，有可能客户端取消了下载，导致此异常，不用关心!", e);
        } finally {
            IOUtils.closeQuietly(outChannel);
            IOUtils.closeQuietly(inChannel);
            IOUtils.closeQuietly(raf);
        }
        logger.info("---------------下载耗时： {} ms, 响应状态: {}",
                (System.currentTimeMillis() - startTime), response.getStatus());
        return null;
    }

    /**
     * 根据“文件名的后缀”获取文件内容类型（而非根据File.getContentType()读取的文件类型）
     *
     * @param returnFileName 带验证的文件名
     * @return 返回文件类型
     */
    public static String getContentType(String returnFileName) {
        String contentType = "application/octet-stream";
        if (returnFileName.lastIndexOf(DOT) < 0) {
            return contentType;
        }
        returnFileName = returnFileName.toLowerCase();
        returnFileName = returnFileName.substring(returnFileName.lastIndexOf(DOT) + 1);
        switch (returnFileName) {
            case "html":
            case "htm":
            case "shtml":
                contentType = "text/html";
                break;
            case "apk":
                contentType = "application/vnd.android.package-archive";
                break;
            case "sis":
            case "sisx":
                contentType = "application/vnd.symbian.install";
                break;
            case "exe":
            case "msi":
                contentType = "application/x-msdownload";
                break;
            case "css":
                contentType = "text/css";
                break;
            case "xml":
                contentType = "text/xml";
                break;
            case "gif":
                contentType = "image/gif";
                break;
            case "jpeg":
            case "jpg":
                contentType = "image/jpeg";
                break;
            case "js":
                contentType = "application/x-javascript";
                break;
            case "atom":
                contentType = "application/atom+xml";
                break;
            case "rss":
                contentType = "application/rss+xml";
                break;
            case "mml":
                contentType = "text/mathml";
                break;
            case "txt":
                contentType = "text/plain";
                break;
            case "jad":
                contentType = "text/vnd.sun.j2me.app-descriptor";
                break;
            case "wml":
                contentType = "text/vnd.wap.wml";
                break;
            case "htc":
                contentType = "text/x-component";
                break;
            case "png":
                contentType = "image/png";
                break;
            case "tif":
            case "tiff":
                contentType = "image/tiff";
                break;
            case "wbmp":
                contentType = "image/vnd.wap.wbmp";
                break;
            case "ico":
                contentType = "image/x-icon";
                break;
            case "jng":
                contentType = "image/x-jng";
                break;
            case "bmp":
                contentType = "image/x-ms-bmp";
                break;
            case "svg":
                contentType = "image/svg+xml";
                break;
            case "jar":
            case "var":
            case "ear":
                contentType = "application/java-archive";
                break;
            case "doc":
                contentType = "application/msword";
                break;
            case "pdf":
                contentType = "application/pdf";
                break;
            case "rtf":
                contentType = "application/rtf";
                break;
            case "xls":
                contentType = "application/vnd.ms-excel";
                break;
            case "ppt":
                contentType = "application/vnd.ms-powerpoint";
                break;
            case "7z":
                contentType = "application/x-7z-compressed";
                break;
            case "rar":
                contentType = "application/x-rar-compressed";
                break;
            case "swf":
                contentType = "application/x-shockwave-flash";
                break;
            case "rpm":
                contentType = "application/x-redhat-package-manager";
                break;
            case "der":
            case "pem":
            case "crt":
                contentType = "application/x-x509-ca-cert";
                break;
            case "xhtml":
                contentType = "application/xhtml+xml";
                break;
            case "zip":
                contentType = "application/zip";
                break;
            case "mid":
            case "midi":
            case "kar":
                contentType = "audio/midi";
                break;
            case "mp3":
                contentType = "audio/mpeg";
                break;
            case "ogg":
                contentType = "audio/ogg";
                break;
            case "m4a":
                contentType = "audio/x-m4a";
                break;
            case "ra":
                contentType = "audio/x-realaudio";
                break;
            case "3gpp":
            case "3gp":
                contentType = "video/3gpp";
                break;
            case "mp4":
                contentType = "video/mp4";
                break;
            case "mpeg":
            case "mpg":
                contentType = "video/mpeg";
                break;
            case "mov":
                contentType = "video/quicktime";
                break;
            case "flv":
                contentType = "video/x-flv";
                break;
            case "m4v":
                contentType = "video/x-m4v";
                break;
            case "mng":
                contentType = "video/x-mng";
                break;
            case "asx":
            case "asf":
                contentType = "video/x-ms-asf";
                break;
            case "wmv":
                contentType = "video/x-ms-wmv";
                break;
            case "avi":
                contentType = "video/x-msvideo";
                break;
            default:
                contentType = "application/octet-stream";
                break;
        }
        return contentType;
    }
}
