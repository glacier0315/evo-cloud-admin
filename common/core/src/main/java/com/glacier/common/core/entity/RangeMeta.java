package com.glacier.common.core.entity;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author glacier
 * @version 1.0
 * date 2020-11-08 20:00
 */
public class RangeMeta implements Serializable {
    private static final Pattern PATTERN = Pattern.compile("^bytes=([0-9]+)-([0-9]+)?$");
    private static final long serialVersionUID = -157220972759137761L;
    /**
     * 记录下载类型  1 断点续传 2 普通下载
     */
    private final int rangeType;
    /**
     * 记录文件大小
     */
    private final long fileLength;
    /**
     * 记录已下载文件大小
     */
    private final long pastLength;
    /**
     * 记录客户端需要下载的字节段的最后一个字节偏移量（比如bytes=27000-39000，则这个值是为39000）
     */
    private final long toLength;
    /**
     * 客户端请求的字节总量
     */
    private final long contentLength;

    public RangeMeta(int rangeType, long fileLength, long pastLength, long toLength, long contentLength) {
        this.rangeType = rangeType;
        this.fileLength = fileLength;
        this.pastLength = pastLength;
        this.toLength = toLength;
        this.contentLength = contentLength;
    }

    public int getRangeType() {
        return this.rangeType;
    }

    public long getFileLength() {
        return this.fileLength;
    }

    public long getPastLength() {
        return this.pastLength;
    }

    public long getToLength() {
        return this.toLength;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 解析range
     * @param range 范围
     * @param fileLength 文件长度
     * @return 范围元数据
     */
    public static RangeMeta parse(final String range, final long fileLength) {
        // 记录下载类型  1 断点续传 2 普通下载 默认
        int rangeType = 2;
        // 记录已下载文件大小
        long pastLength = 0L;
        // 记录客户端需要下载的字节段的最后一个字节偏移量（比如bytes=27000-39000，则这个值是为39000）
        long toLength = fileLength - 1;
        // 客户端请求的字节总量
        long contentLength = fileLength;

        // 客户端请求的下载的文件块的开始字节
        if (range != null
                && !range.trim().isEmpty()) {
            Matcher matcher = PATTERN.matcher(range);
            if (matcher.find()) {
                pastLength = Math.max(Long.parseLong(matcher.group(1)), 0);
                String endStr = matcher.group(2);
                if (StringUtils.isNotBlank(endStr)) {
                    toLength = Math.min(Long.parseLong(endStr), fileLength - 1);
                }
            }
            // 客户端请求的是 1275856879-1275877358 之间的字节
            contentLength = toLength - pastLength + 1;
            // 断点续传
            rangeType = 1;
        }
        return new RangeMeta(rangeType, fileLength, pastLength, toLength, contentLength);
    }

    @Override
    public String toString() {
        return "RangeMeta{" +
                "rangeType=" + this.rangeType +
                ", fileLength=" + this.fileLength +
                ", pastLength=" + this.pastLength +
                ", toLength=" + this.toLength +
                ", contentLength=" + this.contentLength +
                '}';
    }
}
