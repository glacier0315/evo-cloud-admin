package com.glacier.sys.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.glacier.common.core.entity.pojo.BasePojo;
import com.glacier.sys.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.reflection.MetaObject;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-24 19:43
 */
@Slf4j
public class PoMetaObjectHandler implements MetaObjectHandler {
    /**
     * 获取当前交易的用户，为空返回默认system
     *
     * @return
     */
    private String getCurrentUsername() {
        return StringUtils.defaultIfBlank(SecurityUtils.getUsername(), BasePojo.DEFAULT_USERNAME);
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createdBy", this.getCurrentUsername(), metaObject);
        this.setFieldValByName("createdTime", Date.from(ZonedDateTime.now().toInstant()), metaObject);
        this.updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updatedBy", this.getCurrentUsername(), metaObject);
        this.setFieldValByName("updatedTime", Date.from(ZonedDateTime.now().toInstant()), metaObject);
    }
}
