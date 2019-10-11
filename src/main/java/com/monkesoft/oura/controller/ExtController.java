package com.monkesoft.oura.controller;


import com.monkesoft.oura.OURADataResponse;
import com.monkesoft.oura.entity.ExtInfo;
import com.monkesoft.oura.service.IExtService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api-rest/ext")
@Api(tags = "扩展信息查询接口")
public class ExtController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IExtService extService;

    @GetMapping(path = {"/{group}/{objId}","/{group}/{objId}/"})
    public OURADataResponse<List<ExtInfo>> getExtOfOjb(@PathVariable String group, @PathVariable String objId) {
        OURADataResponse<List<ExtInfo>> response = new OURADataResponse<>();
        try {
            response.setData(extService.getExtOfObj(group,objId)).success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }
}
