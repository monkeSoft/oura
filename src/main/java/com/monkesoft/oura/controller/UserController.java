package com.monkesoft.oura.controller;


import com.github.pagehelper.Page;
import com.monkesoft.oura.OURADataResponse;
import com.monkesoft.oura.OURAPageResponse;
import com.monkesoft.oura.OURAResponse;
import com.monkesoft.oura.entity.UserInfo;
import com.monkesoft.oura.inter.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api-rest/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping(path = {"/", ""})
    public OURAPageResponse<List<UserInfo>> getUsers(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        OURAPageResponse<List<UserInfo>> response = new OURAPageResponse<List<UserInfo>>();
        try {
            Page<UserInfo> page = userService.getUsers(pageNum, pageSize);
            response.setRowTotal(page.getTotal()).setRowStart(page.getStartRow()).setRowEnd(page.getEndRow())
                    .setPageTotal(page.getPages()).setPageNum(page.getPageNum()).setPageSize(page.getPageSize())
                    .setData(page.getResult())
                    .success();
        } catch (Exception e) {
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    @GetMapping(path = "/{userId}")
    public OURADataResponse<UserInfo> getUserById(@PathVariable String userId) {
        OURADataResponse<UserInfo> response = new OURADataResponse<UserInfo>();
        try {
            response.setData(userService.getUserById(userId))
                    .success();
        } catch (Exception e) {
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    @PostMapping(path = {"/", ""})
    public void insertUser(@RequestBody UserInfo user) {
        userService.insertUser(user);
        return;
    }

}
