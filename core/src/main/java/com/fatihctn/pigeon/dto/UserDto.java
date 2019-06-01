package com.fatihctn.pigeon.dto;

import com.fatihctn.pigeon.enums.Gender;
import com.fatihctn.pigeon.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserDto {
    private int id;
    private String username;
    private Status status;
    private String email;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phone;
    private List<RoleDto> roles;
}
