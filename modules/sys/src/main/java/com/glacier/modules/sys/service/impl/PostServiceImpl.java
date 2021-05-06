package com.glacier.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glacier.common.core.entity.page.PageRequest;
import com.glacier.common.core.entity.page.PageResponse;
import com.glacier.modules.sys.convert.PostConvert;
import com.glacier.modules.sys.entity.Post;
import com.glacier.modules.sys.entity.dto.post.PostDto;
import com.glacier.modules.sys.entity.dto.post.PostQuery;
import com.glacier.modules.sys.mapper.PostMapper;
import com.glacier.modules.sys.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 岗位业务层
 *
 * @author glacier
 * @version 1.0
 * date 2020-09-05 21:53
 */
@Transactional(readOnly = true)
@Service("postService")
public class PostServiceImpl implements PostService {
    private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);
    @Autowired
    private PostConvert postConvert;
    @Autowired
    private PostMapper postMapper;
    
    @Override
    public PageResponse<PostDto> findPage(PageRequest<PostQuery> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        PageInfo<Post> pageInfo = PageInfo.of(this.postMapper.selectList(pageRequest.getParams()));
        return new PageResponse<>(
                pageInfo.getPageNum(),
                pageInfo.getPages(),
                pageInfo.getTotal(),
                this.postConvert.toPostDto(pageInfo.getList()));
    }
    
    @Override
    public List<PostDto> findAllList() {
        return this.postConvert.toPostDto(
                this.postMapper.selectAll());
    }
    
    @Override
    public boolean checkCode(PostDto postDto) {
        if (postDto != null
                && StringUtils.isNotEmpty(postDto.getCode())) {
            return this.postMapper.checkCodeExist(postDto) > 0;
        }
        return false;
    }
    
    @Transactional(rollbackFor = {})
    @Override
    public int save(PostDto postDto) {
        Post post = this.postConvert.map(postDto);
        if (!post.isNewRecord()) {
            post.preUpdate();
            return this.postMapper.updateByPrimaryKey(post);
        }
        post.preInsert();
        return this.postMapper.insert(post);
    }
    
    @Transactional(rollbackFor = {})
    @Override
    public int delete(String id) {
        if (StringUtils.isBlank(id)) {
            return 0;
        }
        return this.postMapper.deleteByPrimaryKey(id);
    }
}
