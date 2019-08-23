package com.monkesoft.oura.entity;

import lombok.Data;

@Data
public class UserOrgVO extends UserInfo {

    private String orgId; //组织ID
    private String jobId; //职务ID
}
