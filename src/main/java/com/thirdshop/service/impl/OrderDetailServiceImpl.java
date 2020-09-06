package com.thirdshop.service.impl;

import com.thirdshop.base.BaseDao;
import com.thirdshop.base.BaseServiceImpl;
import com.thirdshop.mapper.OrderDetailMapper;
import com.thirdshop.po.OrderDetail;
import com.thirdshop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements OrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Override
    public BaseDao<OrderDetail> getBaseDao() {
        return orderDetailMapper;
    }
}
