package com.thirdshop.service.impl;

import com.thirdshop.base.BaseDao;
import com.thirdshop.base.BaseServiceImpl;
import com.thirdshop.mapper.ManageMapper;
import com.thirdshop.po.Manage;
import com.thirdshop.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl extends BaseServiceImpl<Manage> implements ManageService {
    @Autowired
    private ManageMapper manageMapper;
    @Override
    public BaseDao<Manage> getBaseDao() {
        return manageMapper;
    }
}
