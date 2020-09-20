package com.glacier.modules.gen.service;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceDto;
import com.glacier.modules.gen.entity.dto.datasource.GenDatasourceQuery;
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
 * @date 2020-09-20 16:24
 */
@Slf4j
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class GenDatasourceServiceTest {

    private final GenDatasourceService genDatasourceService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById() {
        GenDatasourceDto datasourceDto = this.genDatasourceService.findById("");
        log.info("查询结果 {}", datasourceDto);
    }

    @Test
    void findPage() {
        PageResponse<GenDatasourceDto> page = this.genDatasourceService.findPage(new PageRequest<>(new GenDatasourceQuery()));
        log.info("查询结果 {}", page);
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }
}
