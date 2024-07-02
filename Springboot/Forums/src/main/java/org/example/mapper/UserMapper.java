package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.User;

@Mapper
public interface UserMapper {
    @Select("select * from user where phone=#{phone}")
    User findByPhone(String phone);


    @Insert("insert into user(phone, nickname, create_time, update_time) values(#{phone}, #{nickname}, now(), now())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    @Select("select * from user where id = #{id}")
    User getUserById(int id);

    default User createUser(String phone, String nickname) {
        User user = new User();
        user.setPhone(phone);
        user.setNickname(nickname);
        insertUser(user);
        return getUserById(user.getId());
    }

    @Select("select password from user where phone=#{phone}")
    String findByPassword(String phone);

    @Select("select count(*) from article art,user us where art.create_user=us.id and us.id=#{userId}")
    Integer selectAllNumber(Integer userId);

    @Select("select password from user where phone=#{phone}")
    String selectPassword(String phone);

    @Update("update user set password=#{password} where phone=#{phone}")
    void changePassword(String password, String phone);

    @Update("update user set nickname=#{nickname} where id=#{id}")
    void updateNickename(String nickname, Integer id);

    @Select("select nickname from user where id=#{id}")
    String selectNickname(Integer id);
}
