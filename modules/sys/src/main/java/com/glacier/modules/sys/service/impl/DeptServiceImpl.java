package com.glacier.modules.sys.service.impl;

import com.glacier.common.core.entity.form.IdForm;
import com.glacier.common.core.utils.TreeBuildFactory;
import com.glacier.modules.sys.common.Constant;
import com.glacier.modules.sys.entity.pojo.Dept;
import com.glacier.modules.sys.entity.pojo.User;
import com.glacier.modules.sys.mapper.DeptMapper;
import com.glacier.modules.sys.mapper.UserMapper;
import com.glacier.modules.sys.service.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hebin
 * @version 1.0
 * @date 2019-10-24 17:12
 */
@Slf4j
@Transactional(readOnly = true)
@Service("deptService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;
    private final UserMapper userMapper;

    /**
     * 查找所有 组织机构
     *
     * @return
     */
    @Override
    public List<Dept> findAllList() {
        return this.deptMapper.selectAll();
    }

    /**
     * 根据用户ID查找所有 组织机构
     *
     * @param userId
     * @return
     */
    @Override
    public List<Dept> findListByUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            return new ArrayList<>(1);
        }
        if (Constant.ADMIN_ID.equals(userId)) {
            return this.deptMapper.selectAll();
        }
        return this.deptMapper.findByUserId(userId);
    }

    /**
     * 保存
     *
     * @param record
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(Dept record) {
        if (record.isNewRecord()) {
            record.preInsert();
            return this.deptMapper.insert(record);
        }
        record.preUpdate();
        int update = this.deptMapper.updateByPrimaryKey(record);
        // 更新用户表 组织机构名称
        this.userMapper.updateDeptByDeptId(
                User.builder()
                        .deptId(record.getId())
                        .deptName(record.getName())
                        .build());
        return update;
    }

    /**
     * 根据id批量删除
     *
     * @param idForms
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int batchDelete(List<IdForm> idForms) {
        if (idForms != null && !idForms.isEmpty()) {
            List<String> list = idForms.stream()
                    .map(IdForm::getId)
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
        return TreeBuildFactory.buildMenuTree(
                this.findListByUserId(userId));
    }
}
