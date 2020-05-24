package com.glacier.sys.service.impl;

import com.glacier.common.core.entity.form.IdDto;
import com.glacier.sys.common.Constant;
import com.glacier.sys.entity.pojo.Dept;
import com.glacier.sys.mapper.DeptMapper;
import com.glacier.sys.service.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hebin
 * @version 1.0
 * @date 2019-10-24 17:12
 */
@Slf4j
@Transactional(readOnly = true)
@Service("DeptService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;

    /**
     * 保存
     *
     * @param record
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(Dept record) {
        int update = 0;
        if (record.getId() != null && !record.getId().isEmpty()) {
            update = this.deptMapper.updateById(record);
        } else {
            update = this.deptMapper.insert(record);
        }
        return update;
    }

    /**
     * 根据id批量删除
     *
     * @param idDtos
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int batchDelete(List<IdDto> idDtos) {
        if (idDtos != null && !idDtos.isEmpty()) {
            List<String> list = idDtos.stream()
                    .map(IdDto::getId)
                    .collect(Collectors.toList());
            return this.deptMapper.deleteBatchIds(list);
        }
        return 0;
    }


    /**
     * 根据用户ID 查找组织机构树
     *
     * @param userId
     * @return
     */
    @Override
    public List<Dept> findTree(String userId) {
        List<Dept> depts = this.findDeptsByUsername(userId);
        List<Dept> deptList = new ArrayList<>(10);
        //
        if (depts != null && !depts.isEmpty()) {
            Iterator<Dept> iterator = depts.iterator();
            while (iterator.hasNext()) {
                Dept dept = iterator.next();
                if (dept.getParentId() == null || "".equals(dept.getParentId().trim())
                        || "0".equals(dept.getParentId())) {
                    deptList.add(dept);
                    // 删除
                    iterator.remove();
                }
            }
        }
        // 排序
        deptList.sort(Comparator.comparingInt(Dept::getOrderNum));
        // 组装子类菜单
        this.findChildren(deptList, depts);
        return deptList;
    }

    /**
     * 递归组装菜单
     *
     * @param deptList 当前父级菜单
     * @param depts    待查询菜单
     */
    private void findChildren(List<Dept> deptList, List<Dept> depts) {
        // 为空则返回
        if (deptList == null || deptList.isEmpty() || depts == null || depts.isEmpty()) {
            return;
        }
        for (Dept parent : deptList) {
            List<Dept> children = new ArrayList<>(10);
            Iterator<Dept> iterator = depts.iterator();
            while (iterator.hasNext()) {
                Dept dept = iterator.next();
                if (parent.getId() != null && parent.getId().equals(dept.getParentId())) {
                    // 处理层级
                    if (parent.getLevel() == null) {
                        parent.setLevel(0);
                    }
                    dept.setParentName(parent.getName());
                    dept.setLevel(parent.getLevel() + 1);
                    children.add(dept);
                    iterator.remove();
                }
            }
            parent.setChildren(children);
            children.sort(Comparator.comparingInt(Dept::getOrderNum));
            this.findChildren(children, depts);
        }
    }

    /**
     * 根据用户ID查找所有 组织机构
     *
     * @param userId
     * @return
     */
    private List<Dept> findDeptsByUsername(String userId) {
        List<Dept> deptList = new ArrayList<>(10);
        if (userId == null) {
            return deptList;
        }
        if (Constant.ADMIN_ID.equals(userId)) {
            deptList = this.deptMapper.selectList(null);
        } else {
            deptList = this.deptMapper.findDeptsByUserId(userId);
        }
        return deptList;
    }
}
