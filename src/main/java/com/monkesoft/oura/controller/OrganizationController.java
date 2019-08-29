package com.monkesoft.oura.controller;


import com.github.pagehelper.Page;
import com.monkesoft.oura.OURADataResponse;
import com.monkesoft.oura.OURAPageResponse;
import com.monkesoft.oura.entity.OrganizationInfo;
import com.monkesoft.oura.inter.IOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api-rest/org")
public class OrganizationController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IOrganizationService orgService;

    @GetMapping(path = {"/",""})
    public OURAPageResponse<List<OrganizationInfo>> getOrgs(@RequestParam(required = false, defaultValue = "0") int pageNum,
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

    @GetMapping(path = {"/{orgId}","/{orgId}/"})
    public OURADataResponse<OrganizationInfo> getUserById(@PathVariable String orgId) {
        OURADataResponse<OrganizationInfo> response = new OURADataResponse<>();
        try {
            response.setData(orgService.getOrgById(orgId)).success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }
}
