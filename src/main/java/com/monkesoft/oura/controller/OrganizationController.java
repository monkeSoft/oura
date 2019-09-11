package com.monkesoft.oura.controller;


import com.github.pagehelper.Page;
import com.monkesoft.oura.OURADataResponse;
import com.monkesoft.oura.OURAPageResponse;
import com.monkesoft.oura.OURAResponse;
import com.monkesoft.oura.entity.OrgUserVO;
import com.monkesoft.oura.entity.OrganizationInfo;
import com.monkesoft.oura.inter.IOrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api-rest/org")
@Api(tags = "组织操作接口")
public class OrganizationController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IOrganizationService orgService;

    /**
     * 列表获取所有组织，不分层级
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return
     */
    @GetMapping(path = {"/",""})
    @ApiOperation(value="获取组织列表", notes="分页获取所有组织列表")
    public OURAPageResponse<List<OrganizationInfo>> getOrgs(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                                            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        OURAPageResponse<List<OrganizationInfo>> response = new OURAPageResponse<>();
        try {
            Page<OrganizationInfo> page = orgService.getOrgs(pageNum,pageSize);
            response.buildPage(page).setData(page.getResult()).success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    /**
     * 分页获取子组织列表，若不传分页参数则返回所有
     * @param parentId 父组织ID
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return
     */
    @GetMapping(path = {"/subs/{parentId}","/subs/{parentId}/"})
    public OURAPageResponse<List<OrganizationInfo>> getSubOrgs(@PathVariable String parentId,
                                                                     @RequestParam(required = false, defaultValue = "1") int pageNum,
                                                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        OURAPageResponse<List<OrganizationInfo>> response = new OURAPageResponse<>();
        try {
            Page<OrganizationInfo> page = orgService.getSubOrgs(parentId, pageNum, pageSize);
            response.buildPage(page).setData(page.getResult()).success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    @GetMapping(path = {"/user/{userId}","/user/{userId}/"})
    public OURADataResponse<List<OrgUserVO>> getOrgsOfUser(@PathVariable String userId) {
        OURADataResponse<List<OrgUserVO>> response = new OURADataResponse<>();
        try {
            response.setData(orgService.getOrgsOfUser(userId)).success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    /**
     * 查询单个组织的详情
     * @param orgId
     * @return
     */
    @GetMapping(path = {"/{orgId}","/{orgId}/"})
    @ApiOperation(value="获取组织信息", notes="根据ID获取某个组织的详细数据")
    @ApiImplicitParam(name = "orgId", value = "组织ID", paramType = "path", required = true)
    public OURADataResponse<OrganizationInfo> getOrgById(@PathVariable String orgId) {
        OURADataResponse<OrganizationInfo> response = new OURADataResponse<>();
        try {
            response.setData(orgService.getOrgById(orgId)).success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    /**
     * 增加组织
     * @param org
     * @return
     */
    @PostMapping(path = {"/", ""})
    public OURAResponse insertOrg(@RequestBody OrganizationInfo org) {
        OURAResponse response = new OURAResponse();
        try {
            orgService.insertOrg(org);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    /**
     * 更新组织
     * @param org
     * @return
     */
    @PutMapping(path = {"/", ""})
    public OURAResponse updateOrg(@RequestBody OrganizationInfo org) {
        OURAResponse response = new OURAResponse();
        try {
            orgService.updateOrg(org);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    @DeleteMapping(path = {"/{orgId}","/{orgId}/"})
    public OURAResponse deleteOrg(@PathVariable String orgId) {
        OURAResponse response = new OURAResponse();
        try {
            orgService.deleteOrg(orgId);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }
}
