package com.thirdshop.service.impl;

import com.thirdshop.base.BaseDao;
import com.thirdshop.base.BaseServiceImpl;
import com.thirdshop.mapper.CommentMapper;
import com.thirdshop.po.Comment;
import com.thirdshop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public BaseDao<Comment> getBaseDao() {
        return commentMapper;
    }
}
