package org.example.service;

import org.example.pojo.Article;
import org.example.pojo.ArticleDTO;
import org.example.pojo.Comments;

import java.util.List;

public interface ArticleService {
    void add(Article article);


    List<ArticleDTO> selectAll();

    ArticleDTO selectOne(Integer articleId);

    List<ArticleDTO> selectText(String title);

    List<ArticleDTO> selectByUserId(String id);

    void addComments(Comments comments);

    void delById(String id);
}
