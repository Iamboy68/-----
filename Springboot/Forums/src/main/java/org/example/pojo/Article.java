package org.example.pojo;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id;
    @NotEmpty
    @Pattern(regexp = "^\\S{1,20}$")
    private String title;
    @NotEmpty
    private String content;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
