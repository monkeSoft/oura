package com.monkesoft.oura.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.monkesoft.oura.entity.ExtInfo;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
@CacheConfig(cacheNames = "oura")
public class UserService implements IUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserMapper userDao;

    @Autowired
    ExtService extService;

    @Override
    @CacheEvict()
    @Transactional
    public void insertUser(UserInfo userInfo) {
        Assert.notNull(userInfo,"用户对象不能为空");
        userDao.insertUser(userInfo);
        extService.insertExt(UserInfo.EXT_GROUP,userInfo.getId(),userInfo.getExt());
    }

    @CacheEvict()
    @Override
    @Transactional
    public void updateUser(UserInfo userInfo) {
        Assert.notNull(userInfo,"用户对象不能为空");
        userDao.updateUser(userInfo);
        extService.updateExt(UserInfo.EXT_GROUP,userInfo.getId(),userInfo.getExt());
    }

    @Override
    @CacheEvict()
    public void updateUserStatus(String userId) {
        Assert.notNull(userId,"用户ID不能为空");
        userDao.updateUserStatus(userId);
    }

    @Override
    @CacheEvict()
    @Transactional
    public void deleteUser(String userId) {
        Assert.hasText(userId,"用户ID不能为空");
        userDao.deleteUser(userId);
        extService.deleteExt(UserInfo.EXT_GROUP,userId);
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
        UserInfo userInfo = userDao.getUserById(userId);
        if (userInfo != null) {
            List<ExtInfo> extList = extService.getExtOfObj(UserInfo.EXT_GROUP,userId);
            for(ExtInfo ext : extList)
                userInfo.addExt(ext.getExtFieldId(),ext.getValue());
        }
        return userInfo;
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
