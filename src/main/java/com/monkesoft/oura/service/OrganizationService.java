package com.monkesoft.oura.service;

import com.github.pagehelper.Page;
import com.monkesoft.oura.entity.OrganizationInfo;
import com.monkesoft.oura.inter.IOrganizationService;
import com.monkesoft.oura.mybatis.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService implements IOrganizationService {

    @Autowired
    OrganizationMapper orgMapper;

    @Override
    public void insertUser(OrganizationInfo orgInfo) {

    }

    @Override
    public void updateUser(OrganizationInfo orgInfo) {

    }

    @Override
    public void deleteUser(String orgId) {

    }

    @Override
    public OrganizationInfo getOrgById(String id) {
        return orgMapper.getOrgById(id);
    }

    @Override
    public List<OrganizationInfo> getSubOrgs(String parentId) {
        return null;
    }

    @Override
    public Page<OrganizationInfo> getSubOrgsByPage(String parentId, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<OrganizationInfo> getOrgsOfUser(String userId) {
        return null;
    }

    @Override
    public Page<OrganizationInfo> getOrgs(int pageNum, int pageSize) {
        return null;
    }
}
