package com.monkesoft.oura.mybatis.mapper;

import com.github.pagehelper.Page;
import com.monkesoft.oura.entity.OrganizationInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationMapper {
    void insertOrg(OrganizationInfo org);

    void updateOrg(OrganizationInfo org);

    void deleteOrg(String orgId);

    OrganizationInfo getOrgById(String orgId);

    Page<OrganizationInfo> getOrgs();
    Page<OrganizationInfo> getSubOrgs(String parentId) ;

    List<OrganizationInfo> getOrgsOfUser(String userId);


}
