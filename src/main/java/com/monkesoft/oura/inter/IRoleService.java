package com.monkesoft.oura.inter;

import com.github.pagehelper.Page;
import com.monkesoft.oura.entity.RoleInfo;
import com.monkesoft.oura.entity.RoleInfo;

import java.util.List;

/**
 * 角色服务接口
 */
public interface IRoleService {

    public void insertRole(RoleInfo roleInfo);

    public void updateRole(RoleInfo roleInfo);

    public void deleteRole(String roleId);

    public RoleInfo getRoleById(String id);

    public List<RoleInfo> getSubRoles(String parentId);

    public Page<RoleInfo> getSubRolesByPage(String parentId, int pageNum, int pageSize);

    public List<RoleInfo> getRolesOfUser(String userId);

    public Page<RoleInfo> getRoles(int pageNum, int pageSize);

}
