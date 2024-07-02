package org.example.pojo;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


import java.sql.Date;

@Data
public class UserDTO {

    private Long id;
    private String phone;
    private String nickname;
    private String userPic;

}
