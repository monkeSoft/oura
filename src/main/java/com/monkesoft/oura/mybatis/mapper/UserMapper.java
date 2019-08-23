package com.monkesoft.oura.mybatis.mapper;

import com.monkesoft.oura.entity.UserInfo;
import com.monkesoft.oura.entity.UserOrgVO;
import com.monkesoft.oura.entity.UserRoleVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    void insertUser(UserInfo user);

    void updateUser(UserInfo user);

    void deleteUser(String userId);

    UserInfo getUserById(String userId);

    List<UserInfo> getUsers();

    List<UserOrgVO> getUsersOfOrg(String orgId);
    List<UserRoleVO> getUsersOfRole(String roleId);
}
