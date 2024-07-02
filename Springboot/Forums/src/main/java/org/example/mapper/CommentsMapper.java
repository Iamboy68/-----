package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.CommentsDTO;

import java.util.List;

@Mapper
public interface CommentsMapper {

    @Select("select c.id as id,u.nickname,c.create_time as createTime ,c.content from comments c,user u " +
            "where c.article_id=#{articleId} and c.user_id=u.id")
    List<CommentsDTO> selectByIdComments(String articleId);
}
