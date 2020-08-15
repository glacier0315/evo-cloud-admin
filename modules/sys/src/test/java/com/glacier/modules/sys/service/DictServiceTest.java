package com.glacier.modules.sys.service;

import com.glacier.common.core.entity.dto.IdDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-15 12:03
 */
@SpringBootTest
class DictServiceTest {

    @Resource
    private DictService dictService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
    }

    @Test
    void batchDelete() {
        List<IdDto> idDtos = new ArrayList<>(1);
        for (int i = 0; i < 5; i++) {
            IdDto idDto = new IdDto();
            idDto.setId("1234" + i);
            idDtos.add(idDto);
        }
        int delete = this.dictService.batchDelete(idDtos);
        System.out.println(delete);
    }

    @Test
    void findDictTree() {
    }
}
