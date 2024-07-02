package org.example.service.impl;

import org.example.mapper.ArticleMapper;
import org.example.pojo.Article;
import org.example.pojo.ArticleDTO;
import org.example.pojo.Comments;
import org.example.pojo.UserDTO;
import org.example.service.ArticleService;
import org.example.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        UserDTO user = UserHolder.getUser();
        article.setCreateUser(Math.toIntExact(user.getId()));
        articleMapper.add(article);
    }

    @Override
    public List<ArticleDTO> selectAll() {
        List<ArticleDTO> list= articleMapper.selectAll();
        return list;
    }
    @Override
    public List<ArticleDTO> selectByUserId(String id) {
        List<ArticleDTO> list=  articleMapper.selectByUserId(id);

        return list;
    }

    @Override
    public void addComments(Comments comments) {
        comments.setCreateTime(LocalDateTime.now());
        articleMapper.addComments(comments);
    }

    @Override
    public void delById(String id) {
        articleMapper.delCommentsById(id);
        articleMapper.delArticleById(id);

    }


    @Override
    public ArticleDTO selectOne(Integer articleId) {
        ArticleDTO articleDTO=articleMapper.selectOne(articleId);
        return articleDTO;
    }

    @Override
    public List<ArticleDTO> selectText(String title) {
        List<ArticleDTO> list= articleMapper.selectText(title);
        return list;
    }


}
