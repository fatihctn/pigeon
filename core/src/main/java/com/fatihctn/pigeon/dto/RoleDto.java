package com.fatihctn.pigeon.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RoleDto {
    private int id;
    private String name;
    private String slug;
    private boolean status;
    private List<PermissionDto> permissions;
    private List<UserDto> users;
}
