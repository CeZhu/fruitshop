package com.thirdshop.controller;

import com.thirdshop.po.Comment;
import com.thirdshop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/exAdd")
    public String exAdd(Comment comment, HttpSession session) {
        Integer userId=(Integer)session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login/uLogin";
        }
        comment.setAddTime(new Date());
        comment.setUserId(userId);
        commentService.insert(comment);
        return "redirect:/itemOrder/my";
    }
}
