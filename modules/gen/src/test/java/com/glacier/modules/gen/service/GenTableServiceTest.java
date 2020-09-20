package com.glacier.modules.gen.service;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.gen.entity.dto.table.GenTableDto;
import com.glacier.modules.gen.entity.dto.table.GenTableQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-09-20 15:01
 */
@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class GenTableServiceTest {
    private final GenTableService GenTableService;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById() {
        GenTableDto genTableDto = this.GenTableService.findById("");
        log.info("查询结果 {}", genTableDto);
    }

    @Test
    void findPage() {
        PageResponse<GenTableDto> page = this.GenTableService.findPage(new PageRequest<>(new GenTableQuery()));
        log.info("查询结果 {}", page);
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }
}
