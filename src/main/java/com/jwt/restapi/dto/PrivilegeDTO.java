package com.jwt.restapi.dto;

import lombok.Data;

@Data
public class PrivilegeDTO {
    private Long id;
    private String description;
    private Long userid;
    private Long roleid;
}
