package com.thirdshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.thirdshop.base.BaseController;
import com.thirdshop.po.*;
import com.thirdshop.service.ItemCategoryService;
import com.thirdshop.service.ItemService;
import com.thirdshop.service.ManageService;
import com.thirdshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录相关控制器
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
    @Autowired
    private ManageService manageService;
    @Autowired
    private ItemCategoryService itemCategoryService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public String login() {
        return "login/mLogin";
    }

    @RequestMapping("/toLogin")
    public String toLogin(Manage manage, HttpServletRequest request) {
        Manage byEntity = manageService.getByEntity(manage);
        if(byEntity==null){
            return "redirect:login/mLogout";
        }
        request.getSession().setAttribute("manage",byEntity);
        return "login/mIndex";
    }

    @RequestMapping("/mtuichu")
    public String mtuichu(HttpServletRequest request) {
        request.getSession().setAttribute("manage",null);
        return "login/mLogin";
    }

    @RequestMapping("/uIndex")
    public String uIndex(Model model) {
        String sql1="select * from item_category where isDelete=0 and pid is null order by name";
        List<ItemCategory> itemCategories = itemCategoryService.listBySqlReturnEntity(sql1);
        List<CategoryDto> list=new ArrayList<>();
        for (ItemCategory ic : itemCategories) {
            CategoryDto cd=new CategoryDto();
            cd.setFather(ic);
            String sql2="select * from item_category where isDelete=0 and pid="+ic.getId();
            List<ItemCategory> children = itemCategoryService.listBySqlReturnEntity(sql2);
            cd.setChildren(children);
            list.add(cd);
        }
        model.addAttribute("lbs",list);
        //折扣商品
        List<Item> zks = itemService.listBySqlReturnEntity("select * from item where zk is not null order by zk desc limit 0,10");
        model.addAttribute("zks",zks);
        //热销商品
        List<Item> rxs=itemService.listBySqlReturnEntity("select * from item order by gmNum desc limit 0,10");
        model.addAttribute("rxs",rxs);

        return "login/uIndex";
    }

    @RequestMapping("/res")
    public String res() {
        return "login/res";
    }

    @RequestMapping("/toRes")
    public String toRes(User user) {
        userService.insert(user);
        return "redirect:/login/uLogin";
    }

    @RequestMapping("/uLogin")
    public String uLogin() {
        return "login/uLogin";
    }

    @RequestMapping("/utoLogin")
    public String utoLogin(User u, HttpServletRequest request, HttpSession session) {
        User user = userService.getByEntity(u);
        if (user == null) {
            request.setAttribute("message","用户名或者密码错误");
            return "login/uLogin";
        }else{
            request.getSession().setAttribute("role",2);
            request.getSession().setAttribute("username",user.getUserName());
            request.getSession().setAttribute("userId",user.getId());
            return "redirect:/login/uIndex";
        }
    }

    @RequestMapping("/uTui")
    public String uTui(HttpSession session) {
        session.invalidate();
        return "redirect:/login/uIndex";
    }

    @RequestMapping("/pass")
    public String pass(HttpSession session, Model model) {
        Integer userId=(Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login/uLogin";
        }
        User user = userService.load(userId);
        model.addAttribute("obj",user);
        return "login/pass";
    }

    @RequestMapping("/upass")
    @ResponseBody
    public String upass(String password, HttpSession session) {
        Integer userId=(Integer) session.getAttribute("userId");
        JSONObject json=new JSONObject();
        if (userId == null) {
            json.put("res",0);
            return json.toJSONString();
        }
        User user = userService.load(userId);
        user.setPassWord(password);
        userService.update(user);
        json.put("res",1);
        return json.toJSONString();
    }
}
