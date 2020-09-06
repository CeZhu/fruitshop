package com.thirdshop.service.impl;

import com.thirdshop.base.BaseDao;
import com.thirdshop.base.BaseServiceImpl;
import com.thirdshop.mapper.NewsMapper;
import com.thirdshop.po.News;
import com.thirdshop.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Override
    public BaseDao<News> getBaseDao() {
        return newsMapper;
    }
}
