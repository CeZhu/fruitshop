package com.thirdshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.thirdshop.base.BaseController;
import com.thirdshop.po.Cart;
import com.thirdshop.po.Item;
import com.thirdshop.service.CartService;
import com.thirdshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ItemService itemService;

    @RequestMapping("/exAdd")
    @ResponseBody
    public String exAdd(Cart cart, HttpSession session) {
        Integer userId = (Integer)session.getAttribute("userId");
        JSONObject json=new JSONObject();
        if(userId==null){
            json.put("res",0);
            return json.toJSONString();
        }
        cart.setUserId(userId);
        Integer itemId = cart.getItemId();
        Item item = itemService.load(itemId);
        Double price=Double.parseDouble(item.getPrice());
        Integer discount=item.getZk();
        if(discount!=null){
            price=price*discount/10;
        }
        BigDecimal real_price=new BigDecimal(price).setScale(2, RoundingMode.UP);
        cart.setPrice(real_price.doubleValue());

        Integer num=cart.getNum();
        BigDecimal total=new BigDecimal(num*real_price.doubleValue()).setScale(2,RoundingMode.UP);
        cart.setTotal(total.doubleValue()+"");

        cartService.insert(cart);
        json.put("res",1);
        return json.toJSONString();
    }

    @RequestMapping("/findBySql")
    public String findBySql(HttpSession session, Model model) {
        Integer userId=(Integer)session.getAttribute("userId");
        String sql="select * from car where user_id="+userId+" order by id desc";
        List<Cart> list = cartService.listBySqlReturnEntity(sql);
        model.addAttribute("list",list);
        return "cart/cart";
    }
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        cartService.deleteById(id);
        return "success";
    }
}
