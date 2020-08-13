package com.glacier.modules.sys.service.impl;

import com.glacier.common.core.entity.form.IdForm;
import com.glacier.common.core.utils.TreeBuildFactory;
import com.glacier.modules.sys.common.Constant;
import com.glacier.modules.sys.entity.form.dept.DeptForm;
import com.glacier.modules.sys.entity.pojo.Dept;
import com.glacier.modules.sys.entity.pojo.User;
import com.glacier.modules.sys.entity.vo.DeptVo;
import com.glacier.modules.sys.mapper.DeptMapper;
import com.glacier.modules.sys.mapper.RoleDeptMapper;
import com.glacier.modules.sys.mapper.UserMapper;
import com.glacier.modules.sys.service.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
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
    private final ModelMapper modelMapper;
    private final DeptMapper deptMapper;
    private final UserMapper userMapper;
    private final RoleDeptMapper roleDeptMapper;

    /**
     * 查找所有 组织机构
     *
     * @return
     */
    @Override
    public List<DeptVo> findAllList() {
        return this.modelMapper.map(
                this.deptMapper.selectAll(),
                new TypeToken<List<DeptVo>>() {
                }.getType());
    }

    /**
     * 根据用户ID查找所有 组织机构
     *
     * @param userId
     * @return
     */
    @Override
    public List<DeptVo> findListByUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            return new ArrayList<>(1);
        }
        if (Constant.ADMIN_ID.equals(userId)) {
            return this.findAllList();
        }
        return this.modelMapper.map(
                this.deptMapper.findByUserId(userId),
                new TypeToken<List<DeptVo>>() {
                }.getType());
    }

    /**
     * 保存
     *
     * @param deptForm
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int save(DeptForm deptForm) {
        AtomicInteger update = new AtomicInteger(0);
        Optional.ofNullable(deptForm).ifPresent(form -> {
            Dept dept = this.modelMapper.map(form, Dept.class);
            if (!dept.isNewRecord()) {
                dept.preUpdate();
                update.set(this.deptMapper.updateByPrimaryKey(dept));
                // 更新用户表 组织机构名称
                User user = new User();
                user.setDeptId(dept.getId());
                user.setDeptName(dept.getName());
                this.userMapper.updateDeptByDeptId(user);
            } else {
                dept.preInsert();
                update.set(this.deptMapper.insert(dept));
            }
        });
        return update.get();
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
        if (idForms != null
                && !idForms.isEmpty()) {
            List<String> list = idForms.stream()
                    .map(IdForm::getId)
                    .collect(Collectors.toList());
            int deleteBatchIds = this.deptMapper.deleteBatchIds(list);
            this.roleDeptMapper.deleteByDeptIds(list);
            return deleteBatchIds;
        }
        return 0;
    }

    @Override
    public List<String> findByRole(String roleId) {
        if (StringUtils.isNotEmpty(roleId)) {
            // TODO: 2020/8/12 根据角色id 查询角色所具有的单位
        }
        return new ArrayList<String>(1);
    }


    /**
     * 根据用户ID 查找组织机构树
     *
     * @param userId
     * @return
     */
    @Override
    public List<DeptVo> findTree(String userId) {
        return TreeBuildFactory.buildMenuTree(
                this.findListByUserId(userId));
    }
}
