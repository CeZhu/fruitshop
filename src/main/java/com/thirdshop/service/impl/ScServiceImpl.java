package com.thirdshop.service.impl;

import com.thirdshop.base.BaseDao;
import com.thirdshop.base.BaseServiceImpl;
import com.thirdshop.mapper.ScMapper;
import com.thirdshop.po.Sc;
import com.thirdshop.service.ScService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScServiceImpl extends BaseServiceImpl<Sc> implements ScService {
    @Autowired
    private ScMapper scMapper;
    @Override
    public BaseDao<Sc> getBaseDao() {
        return scMapper;
    }
}
