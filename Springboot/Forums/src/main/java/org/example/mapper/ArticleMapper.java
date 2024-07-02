package org.example.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Article;
import org.example.pojo.ArticleDTO;
import org.example.pojo.Comments;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert("insert into article(title,content,create_user,create_time,update_time)" +
            "values(#{title},#{content},#{createUser},#{createTime},#{updateTime})")

    void add(Article article);


    @Insert("insert into comments(user_id,content,article_id,create_time) value (#{userId},#{content},#{articleId},#{createTime})")
    void addComments(Comments comments);


    @Select("select art.id  id,art.title title,art.content content,us.nickname nickname FROM user us ,article art WHERE us.id=art.create_user")
    List<ArticleDTO> selectAll();


    @Select("select art.id  id,art.title title,art.content content,us.nickname nickname,art.create_time createtime from user us ,article art WHERE us.id=art.create_user and art.id=#{articleId}")
    ArticleDTO selectOne(Integer articleId);

    @Select("SELECT art.id  id,art.title title,art.content content,us.nickname nickname FROM USER us ,article art " +
            "WHERE us.id=art.create_user AND art.title LIKE CONCAT('%', #{title}, '%')")
    List<ArticleDTO> selectText(String title);

    @Select("select id,title,content,create_time createTime from article where create_user=#{id}")
    List<ArticleDTO> selectByUserId(String id);
    @Delete("delete from article where id=#{id}")
    void delArticleById(String id);
    @Delete("delete from comments where article_id=#{id}")
    void delCommentsById(String id);
}
