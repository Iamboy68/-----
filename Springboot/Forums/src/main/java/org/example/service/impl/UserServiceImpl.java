package org.example.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Select;
import org.example.mapper.UserMapper;
import org.example.pojo.*;
import org.example.service.UserService;
import org.example.utils.PasswordUtil;
import org.example.utils.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.example.utils.RedisConstants.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result sendCode(String phone, HttpSession session) {
        if (RegexUtils.isPhoneInvalid(phone)) {
            return Result.error("手机号错误");
        }

        String code = RandomUtil.randomNumbers(6);

        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY+phone,code,LOGIN_CODE_TTL, TimeUnit.MINUTES);


        return Result.success(code);
    }

    @Override
    public Result login(Login login, HttpSession session) {
        String phone = login.getPhone();

        if (RegexUtils.isPhoneInvalid(phone)) {
            return Result.error("手机号错误");
        }


        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);


        String code = login.getCode();
        if (cacheCode == null || !cacheCode.equals(code)) {
            return Result.error("验证码错误");
        }

        User user = userMapper.findByPhone(phone);
        if (user == null) {
            user = createUserWithPhone(phone);
        }

        return getResult(session, user);
    }


    @Override
    public Result changePassword(ChangePassword changepassword, HttpSession session) {
        String phone = changepassword.getPhone();
        if (RegexUtils.isPhoneInvalid(phone)) {
            return Result.error("手机号错误");
        }
        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);
        String code = changepassword.getCode();
        if (cacheCode == null || !cacheCode.equals(code)) {
            return Result.error("验证码错误");
        }
       String selectPassword=userMapper.selectPassword(phone);
        if (selectPassword!=null){
            if(PasswordUtil.checkPassword(changepassword.getNewPassword(),selectPassword)){
                return Result.error("新密码与旧密码一致");
            }
        }


        String password = PasswordUtil.hashPassword(changepassword.getNewPassword());

        userMapper.changePassword(password,phone);


        return Result.success();
    }

    @Override
    public String updateNickname(String nickname, Integer id) {
        userMapper.updateNickename(nickname,id);
       String setNickname= userMapper.selectNickname(id);
        return setNickname;
    }

    @Override
    public Result passwordlogin(Login login, HttpSession session) {
        String phone = login.getPhone();
        String password=login.getPassword();
        if (RegexUtils.isPhoneInvalid(phone)) {
            return Result.error("账号错误");
        }
        User user = userMapper.findByPhone(phone);
        if(user==null){
            return Result.error("账号错误");
        }
//        String hashPassword = PasswordUtil.hashPassword();
        String byPassword= userMapper.findByPassword(phone);
        if(!PasswordUtil.checkPassword(password,byPassword)){
            return Result.error("密码错误");
        }
        return getResult(session, user);
    }

    @Override
    public Integer selectAllNumber(Integer userId) {
      Integer cnt=  userMapper.selectAllNumber(userId);
        return cnt;
    }





    private Result getResult(HttpSession session, User user) {
        String token = UUID.randomUUID().toString(true);

        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);

        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO,new HashMap<>()
                , CopyOptions.create().setIgnoreNullValue(true)
                        .setFieldValueEditor((name,value)->value.toString()));

        String tokenKey = LOGIN_USER_KEY + token;
        stringRedisTemplate.opsForHash().putAll(tokenKey,userMap);

        stringRedisTemplate.expire(tokenKey,LOGIN_USER_TTL, TimeUnit.MINUTES);

        session.setAttribute("user", userDTO);

        return Result.success(token);
    }

    private User createUserWithPhone(String phone) {
        String nickname = RandomUtil.randomString(10);
        return userMapper.createUser(phone, nickname);

    }
}
