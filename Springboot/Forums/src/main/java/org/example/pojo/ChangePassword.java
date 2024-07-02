package org.example.pojo;

import lombok.Data;

@Data
public class ChangePassword {
    private String phone;
    private String code;
    private String newPassword;
}
