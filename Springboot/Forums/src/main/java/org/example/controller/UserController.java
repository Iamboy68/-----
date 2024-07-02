package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.pojo.ChangePassword;
import org.example.pojo.Login;
import org.example.pojo.Result;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.example.utils.RedisConstants.LOGIN_USER_KEY;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/register")
    public Result register(@RequestParam("phone") String phone, HttpSession session) {
        return userService.sendCode(phone, session);
    }

    @PostMapping("/login")
    public Result login(@RequestBody Login login, HttpSession session) {
        return userService.login(login, session);
    }

    @PostMapping("/password")
    public Result password(@RequestBody Login login, HttpSession session) {
        return userService.passwordlogin(login, session);
    }

    @PostMapping("/changePassword")
    public Result changePassword(@RequestBody ChangePassword changepassword, HttpSession session) {
        userService.changePassword(changepassword,session);
        return Result.success() ;
    }

    @GetMapping("/selectAllNumber")
    public Result<Integer> selectAllNumber(@RequestParam("userId") Integer userId) {
      Integer cnt= userService.selectAllNumber(userId);
        return Result.success(cnt);

    }

    @GetMapping("/changeInfo")
    public Result<String> changeInfo(@RequestParam("nickname") String nickname,@RequestParam("id") Integer id) {
        String setNickname= userService.updateNickname(nickname,id);
        return Result.success(setNickname);

    }

    @GetMapping("/me")
    public Result<Map<Object, Object>> getMeData(@RequestHeader(name = "Authorization") String token) {
        String redisToken = LOGIN_USER_KEY + token;
        Map<Object, Object> map = redisTemplate.opsForHash().entries(redisToken);
        return Result.success(map);
    }


}
