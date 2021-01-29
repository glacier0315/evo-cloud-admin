package com.glacier.modules.gen.service;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceDto;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceForm;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

/**
 * @author glacier
 * @version 1.0
 * date 2020-09-20 16:24
 */
@SpringBootTest
class GenDatasourceServiceTest {
    private static final Logger log = LoggerFactory.getLogger(GenDatasourceServiceTest.class);
    @Autowired
    private GenDatasourceService genDatasourceService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Rollback()
//    @Test
    void initDatasource() {
        String poolName = "eboot_sys";
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.10.130:3306/eboot_sys?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
        String username = "eboot_sys";
        String password = "eboot_sys";
        String validationQuery = "SELECT 'x'";
        int save = 0;
        GenDatasourceForm datasourceForm = null;

        datasourceForm = new GenDatasourceForm();
        datasourceForm.setDriverClassName(driver);
        datasourceForm.setUrl(url);
        datasourceForm.setUsername(username);
        datasourceForm.setPassword(password);
        datasourceForm.setName(poolName);
        datasourceForm.setValidationQuery(validationQuery);
        save = this.genDatasourceService.save(datasourceForm);
        Assertions.assertEquals(save, 1, "未保存成功！");

        poolName = "gaedu";
        driver = "oracle.jdbc.driver.OracleDriver";
        url = "jdbc:oracle:thin:@192.168.10.128:1521:XE?characterEncoding=utf8&useSSL=false";
        username = "gaedu";
        password = "gaedu";
        validationQuery = "select 1 from dual";
        datasourceForm = new GenDatasourceForm();
        datasourceForm.setDriverClassName(driver);
        datasourceForm.setUrl(url);
        datasourceForm.setUsername(username);
        datasourceForm.setPassword(password);
        datasourceForm.setName(poolName);
        datasourceForm.setValidationQuery(validationQuery);
        save = this.genDatasourceService.save(datasourceForm);
        Assertions.assertEquals(save, 1, "未保存成功！");
    }

    @Test
    void findById() {
        String id = "adddc27076c840e688118369c97d5f5a";
        GenDatasourceDto datasourceDto = this.genDatasourceService.findById(id);
        log.info("查询结果 {}", datasourceDto);
    }

    @Test
    void findPage() {
        PageResponse<GenDatasourceDto> page = this.genDatasourceService.findPage(new PageRequest<>(new GenDatasourceQuery()));
        log.info("查询结果 {}", page);
    }

    @Test
    void save() {
        String poolName = "eboot_sys";
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.10.130:3306/eboot_sys?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
        String username = "eboot_sys";
        String password = "eboot_sys";
        String validationQuery = "SELECT 'x'";
        GenDatasourceForm datasourceForm = new GenDatasourceForm();
        datasourceForm.setDriverClassName(driver);
        datasourceForm.setUrl(url);
        datasourceForm.setUsername(username);
        datasourceForm.setPassword(password);
        datasourceForm.setName(poolName);
        datasourceForm.setValidationQuery(validationQuery);
        int save = this.genDatasourceService.save(datasourceForm);
        Assertions.assertEquals(save, 1, "未保存成功！");
    }

    @Test
    void delete() {
    }
}
