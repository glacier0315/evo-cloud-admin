package com.glacier.modules.gen.utils;

import com.glacier.modules.gen.entity.dto.column.GenTableColumnDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author glacier
 * @version 1.0
 * @date 2021-01-28 17:29
 */
@SpringBootTest
class GenUtilsTest {
    @Autowired
    private GenUtils genUtils;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void queryTables() {
        String dataSourceId = "adddc27076c840e688118369c97d5f5a";
        List<String> tables = genUtils.queryTables(dataSourceId);
        tables.forEach(System.out::println);
    }

    @Test
    void queryTableColumns() {
        String dataSourceId = "adddc27076c840e688118369c97d5f5a";
        String tableName = "sys_user";
        List<GenTableColumnDto> tableColumns = genUtils.queryTableColumns(dataSourceId, tableName);
        tableColumns.forEach(System.out::println);
    }
}