package com.monkesoft.oura.controller;


import com.monkesoft.oura.OURADataResponse;
import com.monkesoft.oura.entity.OrganizationInfo;
import com.monkesoft.oura.entity.UserInfo;
import com.monkesoft.oura.inter.IOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api-rest/org")
public class OrganizationController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IOrganizationService orgService;

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
