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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
@Cacheable(value = "users")
public class UserService implements IUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserMapper userDao;

    @CachePut(value = "users", key = "#userInfo.id")
    @CacheEvict(value = "users")
    public void insertUser(UserInfo userInfo) {
        if (userInfo == null)
            return;
        userDao.insertUser(userInfo);
    }

    @CachePut(value = "users", key = "#userInfo.id")
    @CacheEvict(value = "users")
    public void updateUser(UserInfo userInfo) {
        if (userInfo == null)
            return;
        userDao.updateUser(userInfo);
    }

    @CachePut(value = "users", key = "#userId")
    @CacheEvict(value = "users")
    public void deleteUser(String userId) {
        if (!StringUtils.hasText(userId)) {
            return;
        }
        userDao.deleteUser(userId);
    }

    /**
     * 获取单个人员信息
     *
     * @param userId
     * @return
     */
    @Cacheable(value = "users", key = "#userId")
    public UserInfo getUserById(String userId) {
        if (!StringUtils.hasText(userId)) {
            return null;
        }
        UserInfo u = userDao.getUserById(userId);
        return u;
    }

    /**
     * 获取人员列表
     *
     * @return
     */
    @Cacheable(value = "users", key = "'user_all_'+#pageNum+'_'+#pageSize", sync = true)
    public Page<UserInfo> getUsers(int pageNum, int pageSize) {
        pageNum = pageNum < 0 ? 0 : pageNum;
        pageSize = pageSize <= 0 ? 10 : pageSize;
        Page<UserInfo> page = PageHelper.startPage(pageNum, pageSize);
        userDao.getUsers();
        return page;
    }

    @Cacheable(value = "users", key = "'user_org_'+#orgId+'_'+#pageNum+'_'+#pageSize", sync = true)
    public Page<UserOrgVO> getUsersOfOrg(String orgId, int pageNum, int pageSize) {
        Page<UserOrgVO> page = PageHelper.startPage(pageNum, pageSize);
        userDao.getUsersOfOrg(orgId);
        return page;
    }

    @Cacheable(value = "users", key = "'user_role_'+#roleId+'_'+#pageNum+'_'+#pageSize", sync = true)
    public Page<UserRoleVO> getUsersOfRole(String roleId, int pageNum, int pageSize) {
        Page<UserRoleVO> page = PageHelper.startPage(pageNum, pageSize);
        userDao.getUsersOfRole(roleId);
        return page;
    }
}
