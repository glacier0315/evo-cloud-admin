package com.glacier.modules.gen.service;

import com.glacier.modules.gen.entity.dto.GenTableColumnDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-09-20 15:01
 */
@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenTableColumnServiceTest {

    private final GenTableColumnService genTableColumnService;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void selectListByTableId() {
        List<GenTableColumnDto> genTableColumnDtos = this.genTableColumnService.selectListByTableId("");
        log.info("查询结果 {}", genTableColumnDtos);
    }
}
