package com.monkesoft.oura.controller;


import com.github.pagehelper.Page;
import com.monkesoft.oura.OURADataResponse;
import com.monkesoft.oura.OURAPageResponse;
import com.monkesoft.oura.OURAResponse;
import com.monkesoft.oura.entity.UserInfo;
import com.monkesoft.oura.entity.UserOrgVO;
import com.monkesoft.oura.entity.UserRoleVO;
import com.monkesoft.oura.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api-rest/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IUserService userService;

    @GetMapping(path = {"/", ""})
    public OURAPageResponse<List<UserInfo>> getUsers(@RequestParam(required = false, defaultValue = "0") int pageNum,
                                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        OURAPageResponse<List<UserInfo>> response = new OURAPageResponse<List<UserInfo>>();
        try {
            Page<UserInfo> page = userService.getUsers(pageNum, pageSize);
            response.buildPage(page).setData(page.getResult()).success();
        } catch (Exception e) {
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    @GetMapping(path = {"/{userId}","/{userId}/"})
    public OURADataResponse<UserInfo> getUserById(@PathVariable String userId) {
        OURADataResponse<UserInfo> response = new OURADataResponse<UserInfo>();
        try {
            response.setData(userService.getUserById(userId)).success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    @PostMapping(path = {"/", ""})
    public OURAResponse insertUser(@RequestBody UserInfo user) {
        OURAResponse response = new OURAResponse();
        try {
            userService.insertUser(user);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    @PutMapping(path = {"/", ""})
    public OURAResponse updateUser(@RequestBody UserInfo user) {
        OURAResponse response = new OURAResponse();
        try {
            userService.updateUser(user);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    @DeleteMapping(path = {"/{userId}","/{userId}/"})
    public OURAResponse deleteOrg(@PathVariable String userId) {
        OURAResponse response = new OURAResponse();
        try {
            userService.deleteUser(userId);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }


    @GetMapping(path = {"/org/{orgId}", "/org/{orgId}/"})
    public OURAPageResponse<List<UserOrgVO>> getUsersOfOrg(@PathVariable String orgId,
                                                           @RequestParam(required = false, defaultValue = "0") int pageNum,
                                                           @RequestParam(required = false, defaultValue = "10") int pageSize) {
        OURAPageResponse<List<UserOrgVO>> response = new OURAPageResponse<>();
        try {
            Page<UserOrgVO> page = userService.getUsersOfOrg(orgId,pageNum,pageSize);
            response.buildPage(page).setData(page.getResult()).success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    @GetMapping(path = {"/role/{roleId}", "/role/{roleId}/"})
    public OURAPageResponse<List<UserRoleVO>> getUsersOfRole(@PathVariable String roleId,
                                                                @RequestParam(required = false, defaultValue = "0") int pageNum,
                                                                @RequestParam(required = false, defaultValue = "10") int pageSize) {
        OURAPageResponse<List<UserRoleVO>> response = new OURAPageResponse<>();
        try {
            Page<UserRoleVO> page = userService.getUsersOfRole(roleId,pageNum,pageSize);
            response.buildPage(page).setData(page.getResult()).success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

}
