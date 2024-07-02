package org.example.controller;

import org.example.pojo.Article;
import org.example.pojo.ArticleDTO;
import org.example.pojo.Comments;
import org.example.pojo.Result;
import org.example.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();

    }




    @GetMapping("/list")
    public Result<List<ArticleDTO>> list(){
        List<ArticleDTO>  list = articleService.selectAll();
      return Result.success(list);
    }
    @GetMapping("/delById")
    public Result articleDelById(@RequestParam("id") String id){
         articleService.delById(id);
      return Result.success();
    }
    @GetMapping("/listByUserId")
    public Result<List<ArticleDTO>> listByUserId(@RequestParam("id") String id){
        List<ArticleDTO>  list = articleService.selectByUserId(id);
        return Result.success(list);
    }

    @PostMapping ("/insertComments")
    public Result insertComments(@RequestBody Comments comments){
        articleService.addComments(comments);
        return Result.success();
    }


    @GetMapping("/listTitle")
    public Result<List<ArticleDTO>> listTitle(@RequestParam("title") String title){
        List<ArticleDTO>  list = articleService.selectText(title);
      return Result.success(list);
    }

    @GetMapping ("/ListOne")
    public Result<ArticleDTO> ListOne(@RequestParam("Id") Integer Id){
        ArticleDTO articleDTO=articleService.selectOne(Id);
        return Result.success(articleDTO);

    }


}
