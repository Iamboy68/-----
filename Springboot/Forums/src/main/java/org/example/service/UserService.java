package org.example.service;

import jakarta.servlet.http.HttpSession;
import org.example.pojo.ChangePassword;
import org.example.pojo.Login;
import org.example.pojo.Result;

public interface UserService {
    Result sendCode(String phone, HttpSession session);

    Result login(Login login, HttpSession session);

    Result passwordlogin(Login login, HttpSession session);

    Integer selectAllNumber(Integer userId);


    Result changePassword(ChangePassword changepassword, HttpSession session);

    String updateNickname(String nickname, Integer id);
}
