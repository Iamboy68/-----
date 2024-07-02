package org.example.controller;

import org.example.pojo.CommentsDTO;
import org.example.pojo.Result;
import org.example.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;


    @GetMapping("/selectByIdComments")
    public Result<List<CommentsDTO>> selectByIdComments(@RequestParam("article_id") String article_id){
      List<CommentsDTO> commentsDTOS= commentsService.selectByIdComments(article_id);
        return Result.success(commentsDTOS);
    }

}
