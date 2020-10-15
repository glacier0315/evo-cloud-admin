package com.glacier.modules.fdfs.util;

import com.glacier.common.core.entity.Result;
import com.glacier.modules.fdfs.FdfsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author glacier
 * @version 1.0
 * date 2020-07-22 15:18
 */
@SpringBootTest(classes = {FdfsApplication.class})
public class FileDfsUtilTest {

    @Resource
    private FileDfsUtil fileDfsUtil;

    @Test
    public void uploadFile() {
        File file = new File("D:\\12.jpg");
        Result<String> result = this.fileDfsUtil.uploadFile(file);
        System.out.println(result);
    }

    @Test
    public void deleteFile() {
        String fileUrl = "group1/M00/00/00/wKgKhF8x9q2AXbSkAABNkeog2NU666.jpg";
        Result<String> result = this.fileDfsUtil.deleteFile(fileUrl);
        System.out.println(result);
    }
}
