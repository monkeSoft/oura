package com.monkesoft.oura.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.monkesoft.oura.entity.OrganizationInfo;
import com.monkesoft.oura.inter.IOrganizationService;
import com.monkesoft.oura.mybatis.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@CacheConfig(cacheNames = "oura")
public class OrganizationService implements IOrganizationService {

    @Autowired
    OrganizationMapper orgMapper;

    @Override
    @CacheEvict()
    public void insertOrg(OrganizationInfo orgInfo) {
        Assert.notNull(orgInfo,"组织对象不能为空");
        orgMapper.insertOrg(orgInfo);
    }

    @Override
    @CacheEvict()
    public void updateOrg(OrganizationInfo orgInfo) {
        Assert.notNull(orgInfo,"组织对象不能为空");
        orgMapper.updateOrg(orgInfo);
    }

    @Override
    @CacheEvict()
    public void deleteOrg(String orgId) {
        Assert.hasText(orgId,"组织ID不能为空");
        orgMapper.deleteOrg(orgId);
    }

    @Override
    @Cacheable(key = "'org_'+#id")
    public OrganizationInfo getOrgById(String id) {
        Assert.hasText(id,"组织ID不能为空");
        return orgMapper.getOrgById(id);
    }

    @Override
    @Cacheable(key = "'org_subs_'+#parentId")
    public List<OrganizationInfo> getSubOrgs(String parentId) {
        if (!StringUtils.hasText(parentId))
            parentId = "-1";//根节点默认为-1

        return orgMapper.getSubOrgs(parentId);
    }

    @Override
    @Cacheable(key = "'org_subs_'+#parentId+'_'+#pageNum+'_'+#pageSize")
    public Page<OrganizationInfo> getSubOrgsByPage(String parentId, int pageNum, int pageSize) {
        if (!StringUtils.hasText(parentId))
            parentId = "-1";//根节点默认为-1

        Page<OrganizationInfo> page = PageHelper.startPage(pageNum, pageSize);
        orgMapper.getSubOrgs(parentId);
        return page;
    }

    @Override
    @Cacheable(key = "'org_user_'+#userId")
    public List<OrganizationInfo> getOrgsOfUser(String userId) {
        return orgMapper.getOrgsOfUser(userId);
    }

    @Override
    @Cacheable(key = "'org_all_'+#pageNum+'_'+#pageSize", sync = true)
    public Page<OrganizationInfo> getOrgs(int pageNum, int pageSize) {
        Page<OrganizationInfo> page = PageHelper.startPage(pageNum, pageSize);
        orgMapper.getOrgs();
        return page;
    }
}
