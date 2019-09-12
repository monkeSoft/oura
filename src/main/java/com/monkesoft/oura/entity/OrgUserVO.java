package com.monkesoft.oura.entity;

import lombok.Data;

/**
 * 用户归属的组织信息值对象，附加了用户在组织下的职务信息
 */
@Data
public class OrgUserVO extends OrganizationInfo {

    private String userId; //用户ID
    private String jobId; //职务ID
    private String jobName; //职务名称

}
