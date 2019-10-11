package com.monkesoft.oura.service;

import com.github.pagehelper.Page;
import com.monkesoft.oura.entity.OrgUserVO;
import com.monkesoft.oura.entity.OrganizationInfo;

import java.util.List;

/**
 * 组织服务接口
 */
public interface IOrganizationService {

    public void insertOrg(OrganizationInfo orgInfo);

    public void updateOrg(OrganizationInfo orgInfo);

    public void updateOrgStatus(int status, String orgId);

    public void deleteOrg(String orgId);

    public void addUsersToOrg(String orgId,String... userIds);

    public OrganizationInfo getOrgById(String id);

    public Page<OrganizationInfo> getSubOrgs(String parentId,int pageNum,int pageSize);

    public List<OrgUserVO> getOrgsOfUser(String userId);

    public Page<OrganizationInfo> getOrgs(int pageNum,int pageSize);

}
