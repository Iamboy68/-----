package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Comments {
    @NotNull
    private Integer id;
    @NotEmpty
    private Integer userId;
    @NotEmpty
    private String content;
    @NotEmpty
    private Integer articleId;
    private LocalDateTime createTime;


}
