package com.jwt.restapi.dto;

import lombok.Data;

@Data
public class UserPrivilegeAssignmentDTO {
    private Long id;
    private Long userid;
    private Long privilegeid;
}
