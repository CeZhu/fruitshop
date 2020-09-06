package com.thirdshop.controller;

import com.thirdshop.base.BaseController;
import com.thirdshop.po.User;
import com.thirdshop.service.UserService;
import com.thirdshop.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findBySql")
    public String findBySql(Model model,String userName) {
        String sql="select * from user";
        if(!isEmpty(userName)){
            sql+=" where userName like '%"+userName+"%'";
        }
        Pager<User> pagers = userService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        return "user/user";
    }

    @RequestMapping("/view")
    public String view(HttpSession session,Model model) {
        Integer userId = (Integer)session.getAttribute("userId");
        if(userId==null){
            return "redirect:/login/uLogin";
        }
        User user = userService.load(userId);
        model.addAttribute("obj",user);
        return "user/view";
    }

    @RequestMapping("/exUpdate")
    public String exUpdate(User user, HttpSession session) {
        Integer userId=(Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login/uLogin";
        }
        user.setId(userId);
        userService.update(user);
        return "redirect:/user/view";
    }
}
