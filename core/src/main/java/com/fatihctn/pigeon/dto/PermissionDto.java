package com.fatihctn.pigeon.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PermissionDto {
    private int id;
    private String name;
    private String slug;
    private boolean status;
    private List<RoleDto> roles;
}
