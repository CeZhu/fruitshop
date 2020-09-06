package com.thirdshop.controller;

import com.thirdshop.po.ItemCategory;
import com.thirdshop.service.ItemCategoryService;
import com.thirdshop.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/itemCategory")
public class ItemCategoryController {
    @Autowired
    private ItemCategoryService itemCategoryService;

    @RequestMapping("/findBySql")
    public String findBySql(Model model, ItemCategory itemCategory) {
        String sql="select * from item_category where isDelete=0 and pid is null";
        Pager<ItemCategory> pagers = itemCategoryService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",itemCategory);
        return "itemCategory/itemCategory";
    }

    @RequestMapping("/add")
    public String add() {
        return "itemCategory/add";
    }

    @RequestMapping("/exAdd")
    public String exAdd(ItemCategory itemCategory) {
        itemCategory.setIsDelete(0);
        itemCategoryService.insert(itemCategory);
        return "redirect:/itemCategory/findBySql";
    }

    @RequestMapping("/update")
    public String update(int id, Model model) {
        ItemCategory obj = itemCategoryService.load(id);
        model.addAttribute("obj",obj);
        return "itemCategory/update";
    }

    @RequestMapping("/exUpdate")
    public String exUpdate(int id, String name) {
        ItemCategory itemCategory = itemCategoryService.load(id);
        itemCategory.setName(name);
        itemCategoryService.update(itemCategory);
        return "redirect:/itemCategory/findBySql";
    }

    @RequestMapping("/delete")
    public String delete(int id){
        ItemCategory itemCategory = itemCategoryService.load(id);
        itemCategory.setIsDelete(1);
        itemCategoryService.update(itemCategory);
        String sql="update item_category set isDelete=1 where pid="+itemCategory.getPid();
        itemCategoryService.updateBysql(sql);
        return"redirect:/itemCategory/findBySql";
    }

    @RequestMapping("/findBySql2")
    public String findBySql2(ItemCategory itemCategory, Model model) {
        String sql="select * from item_category where isDelete=0 and pid="+itemCategory.getPid();
        Pager<ItemCategory> pagers = itemCategoryService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",itemCategory);
        return "itemCategory/itemCategory2";
    }

    @RequestMapping("/add2")
    public String add2(int pid, Model model) {
        model.addAttribute("pid",pid);
        return "itemCategory/add2";
    }

    @RequestMapping("/exAdd2")
    public String exAdd2(ItemCategory itemCategory, int pid) {
        itemCategory.setIsDelete(0);
        itemCategory.setPid(pid);
        itemCategoryService.insert(itemCategory);
        return "redirect:/itemCategory/findBySql2?pid="+itemCategory.getPid();
    }

    @RequestMapping("/update2")
    public String update2(int id,Model model) {
        ItemCategory itemCategory = itemCategoryService.load(id);
        model.addAttribute("obj",itemCategory);
        return "itemCategory/update2";
    }

    @RequestMapping("/exUpdate2")
    public String exUpdate2(int id, String name) {
        ItemCategory itemCategory = itemCategoryService.load(id);
        itemCategory.setName(name);
        itemCategoryService.update(itemCategory);
        return "redirect:/itemCategory/findBySql2?pid="+itemCategory.getPid();
    }

    @RequestMapping("/delete2")
    public String delete2(int id, int pid){
        String sql="update item_category set isDelete=1 where id="+id+" and pid="+pid;
        itemCategoryService.updateBysql(sql);
        return "redirect:/itemCategory/findBySql2?pid="+pid;
    }

}
