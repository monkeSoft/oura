package com.monkesoft.oura.service;

import com.github.pagehelper.Page;
import com.monkesoft.oura.entity.RoleInfo;
import com.monkesoft.oura.inter.IRoleService;

import java.util.List;

public class RoleService implements IRoleService {
    @Override
    public void insertRole(RoleInfo roleInfo) {

    }

    @Override
    public void updateRole(RoleInfo roleInfo) {

    }

    @Override
    public void deleteRole(String roleId) {

    }

    @Override
    public RoleInfo getRoleById(String id) {
        return null;
    }

    @Override
    public List<RoleInfo> getSubRoles(String parentId) {
        return null;
    }

    @Override
    public Page<RoleInfo> getSubRolesByPage(String parentId, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public List<RoleInfo> getRolesOfUser(String userId) {
        return null;
    }

    @Override
    public Page<RoleInfo> getRoles(int pageNum, int pageSize) {
        return null;
    }
}
