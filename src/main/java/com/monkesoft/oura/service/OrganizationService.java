package com.monkesoft.oura.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.monkesoft.oura.entity.ExtInfo;
import com.monkesoft.oura.entity.OrgUserVO;
import com.monkesoft.oura.entity.OrganizationInfo;
import com.monkesoft.oura.inter.IOrganizationService;
import com.monkesoft.oura.mybatis.mapper.ExtMapper;
import com.monkesoft.oura.mybatis.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "oura")
public class OrganizationService implements IOrganizationService {

    @Autowired
    OrganizationMapper orgMapper;

    @Autowired
    ExtService extService;

    @Override
    @CacheEvict()
    @Transactional
    public void insertOrg(OrganizationInfo orgInfo) {
        Assert.notNull(orgInfo,"组织对象不能为空");
        orgMapper.insertOrg(orgInfo);
        extService.insertExt(OrganizationInfo.EXT_GROUP,orgInfo.getId(),orgInfo.getExt());
    }

    @Override
    @CacheEvict()
    @Transactional
    public void updateOrg(OrganizationInfo orgInfo) {
        Assert.notNull(orgInfo,"组织对象不能为空");
        orgMapper.updateOrg(orgInfo);
        extService.updateExt(OrganizationInfo.EXT_GROUP,orgInfo.getId(),orgInfo.getExt());
    }

    @Override
    @CacheEvict()
    public void updateOrgStatus(int status, String orgId) {
        Assert.hasText(orgId,"组织ID不能为空");
        orgMapper.updateOrgStatus(status,orgId);
    }

    @Override
    @CacheEvict()
    @Transactional
    public void deleteOrg(String orgId) {
        Assert.hasText(orgId,"组织ID不能为空");
        orgMapper.deleteOrg(orgId);
        extService.deleteExt(OrganizationInfo.EXT_GROUP,orgId);
    }

    @Override
    @Cacheable(key = "'org_'+#id")
    public OrganizationInfo getOrgById(String id) {
        Assert.hasText(id,"组织ID不能为空");
        OrganizationInfo orgInfo = orgMapper.getOrgById(id);
        if (orgInfo != null) {
            List<ExtInfo> extList = extService.getExtOfObj(OrganizationInfo.EXT_GROUP,id);
            for(ExtInfo ext : extList)
                orgInfo.addExt(ext.getExtFieldId(),ext.getValue());
        }
        return orgInfo;
    }

    @Override
    @Cacheable(key = "'org_subs_'+#parentId+'_'+#pageNum+'_'+#pageSize")
    public Page<OrganizationInfo> getSubOrgs(String parentId, int pageNum, int pageSize) {
        if (!StringUtils.hasText(parentId))
            parentId = "-1";//根节点默认为-1

        Page<OrganizationInfo> page = PageHelper.startPage(pageNum, pageSize);
        orgMapper.getSubOrgs(parentId);
        return page;
    }

    @Override
    @Cacheable(key = "'org_user_'+#userId")
    public List<OrgUserVO> getOrgsOfUser(String userId) {
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
