package com.monkesoft.oura.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.monkesoft.oura.entity.UserOrgVO;
import com.monkesoft.oura.entity.UserRoleVO;
import com.monkesoft.oura.inter.IUserService;
import com.monkesoft.oura.mybatis.mapper.UserMapper;
import com.monkesoft.oura.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;


@Service
@CacheConfig(cacheNames = "oura")
public class UserService implements IUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserMapper userDao;

    @CacheEvict()
    public void insertUser(UserInfo userInfo) {
        Assert.notNull(userInfo,"用户对象不能为空");
        userDao.insertUser(userInfo);
    }

    @CacheEvict()
    @Override
    public void updateUser(UserInfo userInfo) {
        Assert.notNull(userInfo,"用户对象不能为空");
        userDao.updateUser(userInfo);
    }

    @Override
    @CacheEvict()
    public void updateUserStatus(String userId) {
        Assert.notNull(userId,"用户ID不能为空");
        userDao.updateUserStatus(userId);
    }

    @Override
    @CacheEvict()
    public void deleteUser(String userId) {
        Assert.hasText(userId,"用户ID不能为空");
        userDao.deleteUser(userId);
    }

    /**
     * 获取单个人员信息
     *
     * @param userId
     * @return
     */
    @Cacheable(key = "'user_'+#userId")
    public UserInfo getUserById(String userId) {
        Assert.hasText(userId,"用户ID不能为空");
        return userDao.getUserById(userId);
    }

    /**
     * 获取人员列表
     *
     * @return
     */
    @Cacheable(key = "'user_all_'+#pageNum+'_'+#pageSize", sync = true)
    public Page<UserInfo> getUsers(int pageNum, int pageSize) {
        pageNum = pageNum < 0 ? 0 : pageNum;
        pageSize = pageSize <= 0 ? 10 : pageSize;
        Page<UserInfo> page = PageHelper.startPage(pageNum, pageSize);
        userDao.getUsers();
        return page;
    }

    @Cacheable(key = "'user_org_'+#orgId+'_'+#pageNum+'_'+#pageSize", sync = true)
    public Page<UserOrgVO> getUsersOfOrg(String orgId, int pageNum, int pageSize) {
        Page<UserOrgVO> page = PageHelper.startPage(pageNum, pageSize);
        userDao.getUsersOfOrg(orgId);
        return page;
    }

    @Cacheable(key = "'user_role_'+#roleId+'_'+#pageNum+'_'+#pageSize", sync = true)
    public Page<UserRoleVO> getUsersOfRole(String roleId, int pageNum, int pageSize) {
        Page<UserRoleVO> page = PageHelper.startPage(pageNum, pageSize);
        userDao.getUsersOfRole(roleId);
        return page;
    }
}
