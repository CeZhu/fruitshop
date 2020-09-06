package com.thirdshop.service.impl;

import com.thirdshop.base.BaseDao;
import com.thirdshop.base.BaseServiceImpl;
import com.thirdshop.mapper.CartMapper;
import com.thirdshop.po.Cart;
import com.thirdshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends BaseServiceImpl<Cart> implements CartService {
    @Autowired
    private CartMapper carMapper;
    @Override
    public BaseDao<Cart> getBaseDao() {
        return carMapper;
    }
}
