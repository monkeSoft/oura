package com.monkesoft.oura.mybatis.mapper;

import com.monkesoft.oura.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    UserInfo getUserById(String userId);

    List<UserInfo> getUsers();

    void insertUser(UserInfo user);

    void updateUser(UserInfo user);

    void deleteUser(String userId);
}
