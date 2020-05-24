package com.glacier.sys.controller;

import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.common.core.entity.vo.HttpResult;
import com.glacier.sys.entity.pojo.Log;
import com.glacier.sys.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author glacier
 * @version 1.0
 * @date 2019-12-18 16:12
 */
@Slf4j
@RestController
@RequestMapping("/log")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LogController {

    private final LogService logService;

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/findPage")
    public HttpResult<PageResponse<Log>> findPage(@RequestBody PageRequest<Log> pageRequest) {
        return HttpResult.ok(this.logService.findPage(pageRequest));
    }
}
