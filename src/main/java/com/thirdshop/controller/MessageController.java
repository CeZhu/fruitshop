package com.thirdshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.thirdshop.base.BaseController;
import com.thirdshop.po.Message;
import com.thirdshop.service.MessageService;
import com.thirdshop.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {
    @Autowired
    private MessageService messageService;

    @RequestMapping("/findBySql")
    public String findBySql(Message message, Model model) {
        String sql="select * from message where 1=1";
        if (!isEmpty(message.getName())) {
            sql+=" and name like '%"+message.getName()+"%'";
        }
        sql+=" order by id desc";
        Pager<Message> pagers = messageService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",message);
        return "message/message";
    }

    @RequestMapping("/delete")
    public String delete(int id) {
        messageService.deleteById(id);
        return "redirect:/message/findBySql";
    }

    @RequestMapping("/add")
    public String add() {
        return "message/add";
    }

    @RequestMapping("/exAdd")
    @ResponseBody
    public String exAdd(Message message) {
        messageService.insert(message);
        JSONObject json=new JSONObject();
        return json.toJSONString();
    }
}
