package com.thirdshop.controller;

import com.thirdshop.base.BaseController;
import com.thirdshop.po.News;
import com.thirdshop.service.NewsService;
import com.thirdshop.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {
    @Autowired
    private NewsService newsService;

    @RequestMapping("/findBySql")
    public String findBySql(News news, Model model) {
        String sql="select * from news where 1=1";
        if (!isEmpty(news.getName())) {
            sql+=" and name like '%"+news.getName()+"%'";
        }
        sql+=" order by id desc";
        Pager<News> pagers = newsService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",news);
        return "news/news";
    }

    @RequestMapping("/add")
    public String add() {
        return "news/add";
    }

    @RequestMapping("/exAdd")
    public String exAdd(News news) {
        news.setAddTime(new Date());
        newsService.insert(news);
        return "redirect:/news/findBySql";
    }

    @RequestMapping("/update")
    public String update(int id, Model model) {
        News news = newsService.load(id);
        model.addAttribute("obj",news);
        return "news/update";
    }

    @RequestMapping("/exUpdate")
    public String exUpdate(News news) {
        news.setAddTime(new Date());
        newsService.update(news);
        return "redirect:/news/findBySql";
    }

    @RequestMapping("/delete")
    public String delete(int id) {
        newsService.deleteById(id);
        return "redirect:/news/findBySql";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        String sql="select * from news order by id desc";
        Pager<News> pagers = newsService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        return "news/list";
    }

    @RequestMapping("/view")
    public String view(int id, Model model) {
        News news = newsService.load(id);
        model.addAttribute("obj",news);
        return "news/view";
    }
}
