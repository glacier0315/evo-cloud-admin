package com.glacier.modules.sys.convert;

import com.glacier.modules.sys.entity.Dept;
import com.glacier.modules.sys.entity.dto.dept.DeptForm;
import com.glacier.modules.sys.entity.dto.dept.DeptVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * date 2021-05-06 16:43
 * 单位类型转换
 *
 * @author glacier
 * @version 1.0
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeptConvert {
    /**
     * 转换
     *
     * @param dept
     * @return
     */
    DeptVo toDeptVo(Dept dept);
    
    /**
     * 转换
     *
     * @param deptList
     * @return
     */
    List<DeptVo> toDeptVo(List<Dept> deptList);
    
    /**
     * 转换
     *
     * @param form
     * @return
     */
    Dept map(DeptForm form);
}
