package org.example.pojo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentsDTO {
    private Integer id;
    private String nickname;
    private String createTime;
    private String content;

}
