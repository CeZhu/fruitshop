package com.thirdshop.controller;

import com.thirdshop.base.BaseController;
import com.thirdshop.po.Item;
import com.thirdshop.po.Sc;
import com.thirdshop.service.ItemService;
import com.thirdshop.service.ScService;
import com.thirdshop.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/sc")
public class ScController extends BaseController {
    @Autowired
    private ScService scService;
    @Autowired
    private ItemService itemService;
    @RequestMapping("/exAdd")
    public String exAdd(Sc sc, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId==null){
            return "redirect:/login/uLogin";
        }
        sc.setUserId(userId);
        Item item = itemService.load(sc.getItemId());
        scService.insert(sc);
        item.setScNum(item.getScNum()+1);
        itemService.update(item);
        return "redirect:/sc/findBySql";
    }

    @RequestMapping("/findBySql")
    public String findBySql(HttpSession session, Model model) {
        Integer userId=(Integer) session.getAttribute("userId");
        if(userId==null){
            return "redirect:/login/uLogin";
        }
        String sql="select * from sc where user_id="+userId+" order by id desc";
        Pager<Sc> pagers = scService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        return "sc/my";
    }

    @RequestMapping("/delete")
    public String delete(int id) {
        scService.deleteById(id);
        return "redirect:/sc/findBySql";
    }
}
