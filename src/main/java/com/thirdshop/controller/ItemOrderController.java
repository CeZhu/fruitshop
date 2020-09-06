package com.thirdshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.thirdshop.base.BaseController;
import com.thirdshop.po.*;
import com.thirdshop.service.CartService;
import com.thirdshop.service.ItemOrderService;
import com.thirdshop.service.OrderDetailService;
import com.thirdshop.service.UserService;
import com.thirdshop.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/itemOrder")
public class ItemOrderController extends BaseController {

    @Autowired
    private ItemOrderService itemOrderService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("findBySql")
    public String findBySql(Model model, ItemOrder itemOrder) {
        String sql = "select * from item_order where 1=1 ";
        if (!isEmpty(itemOrder.getCode())) {
            sql += " and code like '%" + itemOrder.getCode() + "%'";
        }
        sql += " order by id desc";
        Pager<ItemOrder> pagers = itemOrderService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers", pagers);
        model.addAttribute("obj", itemOrder);
        return "itemOrder/itemOrder";
    }

    @RequestMapping("/my")
    public String my(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login/uLogin";
        }
        //全部订单
        String sql = "select * from item_order where user_id=" + userId;
        List<ItemOrder> all = itemOrderService.listBySqlReturnEntity(sql);
        //待发货
        String sql2 = sql + " and status=0";
        List<ItemOrder> dfh = itemOrderService.listBySqlReturnEntity(sql2);
        //已取消
        String sql3 = sql + " and status=1";
        List<ItemOrder> yqx = itemOrderService.listBySqlReturnEntity(sql3);
        //待收货
        String sql4 = sql + " and status=2";
        List<ItemOrder> dsh = itemOrderService.listBySqlReturnEntity(sql4);
        //已收货
        String sql5 = sql + " and status=3";
        List<ItemOrder> ysh = itemOrderService.listBySqlReturnEntity(sql5);

        model.addAttribute("all", all);
        model.addAttribute("dfh", dfh);
        model.addAttribute("yqx", yqx);
        model.addAttribute("dsh", dsh);
        model.addAttribute("ysh", ysh);
        return "itemOrder/my";
    }

    @RequestMapping("/exAdd")
    @ResponseBody
    public String exAdd(@RequestBody List<CartDto> list, HttpSession session) {
        JSONObject json = new JSONObject();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            json.put("res", 0);
            return json.toJSONString();
        }
        User user = userService.load(userId);
        if (user.getAddress() == null) {
            json.put("res", 2);
            return json.toJSONString();
        }
        ItemOrder itemOrder = new ItemOrder();
        itemOrder.setAddTime(new Date());
        itemOrder.setCode(getOrderNo());
        itemOrder.setIsDelete(0);
        itemOrder.setStatus(0);
        itemOrder.setUserId(userId);
        BigDecimal total = new BigDecimal(0);
        for (CartDto cartDto : list) {
            Integer id = cartDto.getId();
            Cart cart = cartService.load(id);
            total = total.add(new BigDecimal(cart.getPrice()).multiply(new BigDecimal(cartDto.getNum())));
        }
        itemOrder.setTotal(total.setScale(2, RoundingMode.HALF_UP).toString());
        itemOrderService.insert(itemOrder);

        for (CartDto cartDto : list) {
            Integer id = cartDto.getId();
            Cart cart = cartService.load(id);
            OrderDetail od = new OrderDetail();
            od.setItemId(cart.getItemId());
            od.setNum(cartDto.getNum());
            od.setOrderId(itemOrder.getId());
            od.setStatus(0);
            od.setTotal(new BigDecimal(cart.getPrice()).multiply(new BigDecimal(cartDto.getNum()))
                    .setScale(2, RoundingMode.HALF_UP).toString());
            orderDetailService.insert(od);
            cartService.deleteById(id);
        }
        json.put("res", 1);
        return json.toJSONString();
    }


    private static String date;
    private static long orderNum = 0L;

    public static synchronized String getOrderNo() {
        String str = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        if (date == null || !date.equals(str)) {
            date = str;
            orderNum = 0L;
        }
        orderNum++;
        long orderNO = Long.parseLong(date) * 10000;
        orderNO += orderNum;
        return orderNO + "";
    }

    @RequestMapping("/qx")
    public String qx(int id, Model model) {
        ItemOrder itemOrder = itemOrderService.load(id);
        itemOrder.setStatus(1);
        itemOrderService.update(itemOrder);
        model.addAttribute("obj", itemOrder);
        return "redirect:/itemOrder/my";
    }

    @RequestMapping("/fh")
    public String fh(Integer id, Model model) {
        ItemOrder obj = itemOrderService.load(id);
        obj.setStatus(2);
        itemOrderService.update(obj);
        model.addAttribute("obj", obj);
        return "redirect:/itemOrder/findBySql";
    }

    @RequestMapping("/sh")
    public String sh(Integer id, Model model) {
        ItemOrder obj = itemOrderService.load(id);
        obj.setStatus(3);
        itemOrderService.update(obj);
        model.addAttribute("obj", obj);
        return "redirect:/itemOrder/my";
    }

    @RequestMapping("/pj")
    public String pj(Integer id, Model model) {
        model.addAttribute("id", id);
        return "itemOrder/pj";
    }

}
