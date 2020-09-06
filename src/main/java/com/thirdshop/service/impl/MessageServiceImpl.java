package com.thirdshop.service.impl;

import com.thirdshop.base.BaseDao;
import com.thirdshop.base.BaseServiceImpl;
import com.thirdshop.mapper.MessageMapper;
import com.thirdshop.po.Message;
import com.thirdshop.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends BaseServiceImpl<Message> implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public BaseDao<Message> getBaseDao() {
        return messageMapper;
    }
}
