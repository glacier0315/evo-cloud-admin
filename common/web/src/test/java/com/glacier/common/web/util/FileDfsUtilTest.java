package com.glacier.common.web.util;

import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.common.web.CommonWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-22 15:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CommonWebApplication.class})
public class FileDfsUtilTest {

    @Resource
    private FileDfsUtil fileDfsUtil;

    @Test
    public void uploadFile() {
        File file = new File("D:\\12.jpg");
        HttpResult<String> result = this.fileDfsUtil.uploadFile(file);
        System.out.println(result);
    }

    @Test
    public void deleteFile() {
        String fileUrl = "group1/M00/00/00/wKgKhF8YPHOAbXxoAABNkeog2NU914.jpg";
        HttpResult<String> result = this.fileDfsUtil.deleteFile(fileUrl);
        System.out.println(result);
    }
}
