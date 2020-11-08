package com.glacier.modules.dfs.utils;

import com.glacier.common.core.constant.CommonConstant;
import com.glacier.common.core.constant.MediaConstants;
import com.glacier.common.core.entity.RangeMeta;
import com.glacier.common.core.utils.ContentTypeUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

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

    private FileUtils() {

    }

    /**
     * 向浏览器发送文件下载，支持断点续传
     *
     * @param file     要下载的文件
     * @param request  请求对象
     * @param response 响应对象
     */
    public static void downFile(File file, HttpServletRequest request, HttpServletResponse response) {
        downFile(file, request, response, null);
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
    public static void downFile(File file, HttpServletRequest request, HttpServletResponse response, String fileName) {
        if (!checkFile(file, response)) {
            return;
        }
        // 负责读取数据
        RandomAccessFile raf = null;
        // 缓冲
        OutputStream out = null;
        WritableByteChannel outChannel = null;
        FileChannel inChannel = null;

        // 通知客户端允许断点续传，响应格式为：Accept-Ranges: bytes
        response.setHeader("Accept-Ranges", "bytes");
        RangeMeta rangeMeta = RangeMeta.parse(request.getHeader("Range"), file.length());
        long startTime = System.currentTimeMillis();
        try {
            response.addHeader("Content-Disposition",
                    "attachment; filename="
                            + URLEncoder.encode(
                            StringUtils.isBlank(fileName) ?
                                    file.getName() : fileName,
                            CommonConstant.CHARSET_UTF_8));
            // set the MIME type.
            response.setContentType(ContentTypeUtils.getContentType(file.getName()));
            response.addHeader("Content-Length", String.valueOf(rangeMeta.getContentLength()));
            out = response.getOutputStream();
            raf = new RandomAccessFile(file, "r");
            outChannel = Channels.newChannel(out);
            inChannel = raf.getChannel();
            inChannel.transferTo(rangeMeta.getPastLength(), rangeMeta.getContentLength(), outChannel);
            out.flush();
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
            IOUtils.closeQuietly(outChannel, e -> {
                logger.error("关闭流异常 ", e);
            });
            IOUtils.closeQuietly(inChannel, e -> {
                logger.error("关闭流异常 ", e);
            });
            IOUtils.closeQuietly(raf, e -> {
                logger.error("关闭流异常 ", e);
            });
        }
        logger.info("下载耗时： {} ms, 响应状态: {}",
                (System.currentTimeMillis() - startTime), response.getStatus());
    }

    /**
     * @param file
     * @param response
     * @return
     */
    private static boolean checkFile(File file, HttpServletResponse response) {
        if (file == null || !file.exists()) {
            printError(response, "文件已丢失或不存在！");
            return false;
        }
        if (!file.isFile()) {
            printError(response, "该文件是一个文件夹！");
            return false;
        }
        if (file.length() <= 0) {
            printError(response, "该文件是一个空文件。");
            return false;
        }
        if (!file.canRead()) {
            printError(response, "该文件没有读取权限。");
            return false;
        }
        return true;
    }

    /**
     * 将错误写入响应
     *
     * @param response
     * @param error
     */
    private static void printError(HttpServletResponse response, String error) {
        response.setContentType(MediaConstants.APPLICATION_JSON_CHARSET_UTF_8);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write("{error: " + error + "}");
        } catch (IOException e) {
            logger.error("写入响应失败！", e);
        } finally {
            IOUtils.closeQuietly(writer, e -> {
                logger.error("关闭流异常 ", e);
            });
        }
    }

}
