package com.thirdshop.service.impl;

import com.thirdshop.base.BaseDao;
import com.thirdshop.base.BaseServiceImpl;
import com.thirdshop.mapper.ItemMapper;
import com.thirdshop.po.Item;
import com.thirdshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Override
    public BaseDao<Item> getBaseDao() {
        return itemMapper;
    }
}
