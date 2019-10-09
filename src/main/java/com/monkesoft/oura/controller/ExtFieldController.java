package com.monkesoft.oura.controller;

import com.monkesoft.oura.OURADataResponse;
import com.monkesoft.oura.OURAResponse;
import com.monkesoft.oura.entity.ExtFieldInfo;
import com.monkesoft.oura.inter.IExtFieldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api-rest/extfield")
@Api(tags = "扩展字段配置接口")
public class ExtFieldController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IExtFieldService extFieldService;

    @GetMapping(path = {"/{group}","/{group}/"})
    @ApiOperation(value="获取扩展字段信息", notes="获取某一个分组下的扩展配置信息")
    public OURADataResponse<List<ExtFieldInfo>> getExtFields(@PathVariable String group) {
        OURADataResponse<List<ExtFieldInfo>> response = new OURADataResponse<>();
        try {
            response.setData(extFieldService.getExtFieldsByGroup(group)).success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    @PostMapping(path = {"/", ""})
    public OURAResponse insertExtField(@RequestBody ExtFieldInfo extFieldInfo) {
        OURAResponse response = new OURAResponse();
        try {
            extFieldService.insertExtField(extFieldInfo);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    @PutMapping(path = {"/", ""})
    public OURAResponse updateExtField(@RequestBody ExtFieldInfo extFieldInfo) {
        OURAResponse response = new OURAResponse();
        try {
            extFieldService.updateExtField(extFieldInfo);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }

    @DeleteMapping(path = {"/{group}/{extFieldId}", "/{group}/{extFieldId}/"})
    public OURAResponse deleteExtField(@PathVariable String group, @PathVariable String extFieldId) {
        OURAResponse response = new OURAResponse();
        try {
            extFieldService.deleteExtField(group,extFieldId);
            response.success();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.fail().setDesc(e.getMessage());
        }
        return response;
    }
}
