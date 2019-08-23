package com.monkesoft.oura.mybatis.mapper;

import com.monkesoft.oura.entity.RoleInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    void insertRole(RoleInfo role);

    void updateRole(RoleInfo role);

    void deleteRole(String roleId);

    RoleInfo getRoleById(String roleId);

    List<RoleInfo> getRoles();
}
