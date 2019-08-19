package com.monkesoft.oura.inter;

import com.github.pagehelper.IPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.monkesoft.oura.entity.UserInfo;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.StringUtils;

import java.util.List;

public interface IUserService {

    public void insertUser(UserInfo userInfo);

    public void updateUser(UserInfo userInfo);

    public void deleteUser(String userId);

    public UserInfo getUserById(String userId);

    public Page getUsers(int pageNum, int pageSize);

    public Page getUsersOfOrg(String orgId,int pageNum,int pageSize);

    public Page getUsersOfRole(String roleId,int pageNum,int pageSize);
}
