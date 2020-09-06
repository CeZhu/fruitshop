package com.thirdshop.controller;

import com.thirdshop.base.BaseController;
import com.thirdshop.po.Item;
import com.thirdshop.po.ItemCategory;
import com.thirdshop.service.ItemCategoryService;
import com.thirdshop.service.ItemService;
import com.thirdshop.utils.Pager;
import com.thirdshop.utils.SystemContext;
import com.thirdshop.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemCategoryService itemCategoryService;

    @RequestMapping("/findBySql")
    public String findBySql(Model model, String name) {
        String sql = "select * from item where isDelete=0";
        if (!isEmpty(name)) {
            sql += " and name like '%" + name + "%'";
        }
        sql += " order by id desc";
        Pager<Item> pagers = itemService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers", pagers);
        return "item/item";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        String sql = "select * from item_category where isDelete=0 and pid is not null";
        List<ItemCategory> itemCategories = itemCategoryService.listBySqlReturnEntity(sql);
        model.addAttribute("types", itemCategories);
        return "item/add";
    }

    @RequestMapping("/exAdd")
    public String exAdd(Item item, @RequestParam(value = "file", required = false) MultipartFile[] files,
                        HttpServletRequest request) throws IOException {
        itemCommon(item, files, request);
        item.setGmNum(0);
        item.setIsDelete(0);
        item.setScNum(0);
        itemService.insert(item);

        return "redirect:/item/findBySql";
    }

    private void itemCommon(Item item, @RequestParam(value = "file", required = false) MultipartFile[] files, HttpServletRequest request) throws IOException {
        for (int i = 0; i < files.length; i++) {
            if (files[i].getSize() == 0) break;
            String path = SystemContext.getRealPath() + "resources\\ueditor\\upload\\";
            String uuid = UUIDUtils.create();
            String originalFilename = files[i].getOriginalFilename();
            String filename = path + uuid + originalFilename;
            File newFile = new File(filename);
            files[i].transferTo(newFile);
            String webPath = request.getContextPath() + "\\resources\\ueditor\\upload\\" + uuid + originalFilename;
            switch (i) {
                case 0:
                    item.setUrl1(webPath);
                    break;
                case 1:
                    item.setUrl2(webPath);
                    break;
                case 2:
                    item.setUrl3(webPath);
                    break;
                case 3:
                    item.setUrl4(webPath);
                    break;
                case 4:
                    item.setUrl5(webPath);
                    break;
            }
        }
        ItemCategory itemCategory = itemCategoryService.load(item.getCategoryIdTwo());
        item.setCategoryIdOne(itemCategory.getPid());
    }

    @RequestMapping("/update")
    public String update(int id, Model model) {
        String sql = "select * from item_category where isDelete=0 and pid is not null";
        List<ItemCategory> itemCategories = itemCategoryService.listBySqlReturnEntity(sql);
        Item item = itemService.load(id);
        model.addAttribute("types", itemCategories);
        model.addAttribute("obj", item);
        return "item/update";
    }

    @RequestMapping("/exUpdate")
    public String exUpdate(Item item, @RequestParam("file") MultipartFile[] files,
                           HttpServletRequest request) throws IOException{
        itemCommon(item, files, request);
        itemService.updateById(item);
        return "redirect:/item/findBySql";
    }

    @RequestMapping("/delete")
    public String delete(int id) {
        Item item = itemService.load(id);
        item.setIsDelete(1);
        itemService.updateById(item);
        return "redirect:/item/findBySql";
    }

    @RequestMapping("/shoplist")
    public String shoplist(Item item,Model model, String condition) {
        String sql="select * from item where 1=1";
        if (!isEmpty(item.getCategoryIdTwo())) {
            sql+=" and category_id_two="+item.getCategoryIdTwo();
        }
        if (!isEmpty(condition)) {
            sql+=" and name like '%"+condition+"%'";
            model.addAttribute("condition",condition);
        }
        if (!isEmpty(item.getGmNum())) {
            sql+=" order by gmNum desc";
        }
        if (!isEmpty(item.getPrice())) {
            sql+=" order by price+0 desc";
        }
        if (isEmpty(item.getGmNum()) && isEmpty(item.getPrice())) {
            sql+=" order by id desc";
        }
        Pager<Item> pagers = itemService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",item);
        return "item/shoplist";
    }

    @RequestMapping("/view")
    public String view(int id, Model model) {
        Item item = itemService.load(id);
        model.addAttribute("obj",item);
        return "item/view";
    }

}
