package com.monkesoft.oura.entity;

import lombok.Data;

@Data
public class OrgUserVO extends OrganizationInfo {

    private String userId; //用户ID
    private String jobId; //职务ID
    private String jobName; //职务名称

}
