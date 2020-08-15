package com.glacier.modules.sys.controller;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.entity.SysLog;
import com.glacier.modules.sys.service.SysLogService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录日志管理
 *
 * @author glacier
 * @version 1.0
 * @date 2019-12-18 16:12
 */
@Api(tags = "登录日志管理")
@Slf4j
@RestController
@RequestMapping("/log")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LogController {

    private final SysLogService sysLogService;

    /**
     * 分页查询
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/findPage")
    public Result<PageResponse<SysLog>> findPage(@RequestBody PageRequest<SysLog> pageRequest) {
        return Result.ok(this.sysLogService.findPage(pageRequest));
    }
}
