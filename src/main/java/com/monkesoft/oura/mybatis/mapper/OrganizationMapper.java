package com.monkesoft.oura.mybatis.mapper;

import com.github.pagehelper.Page;
import com.monkesoft.oura.entity.OrgUserVO;
import com.monkesoft.oura.entity.OrganizationInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationMapper {
    void insertOrg(OrganizationInfo org);

    void updateOrg(OrganizationInfo org);

    void updateOrgStatus(int status,String orgId);

    void deleteOrg(String orgId);

    void addUsersToOrg(@Param("orgUserVOList") List<OrgUserVO> orgUserVOList);

    void removeUserFromOrg(String orgId,String userId);

    OrganizationInfo getOrgById(String orgId);

    Page<OrganizationInfo> getOrgs();

    Page<OrganizationInfo> getSubOrgs(String parentId) ;

    List<OrgUserVO> getOrgsOfUser(String userId);


}
