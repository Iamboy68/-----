package org.example.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class ArticleDTO {
    private Integer id;
    private String title;
    private String content;
    private String nickname;
    private String createTime;

}
