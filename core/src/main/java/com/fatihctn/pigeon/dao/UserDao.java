package com.fatihctn.pigeon.dao;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDao {
    private int id;
    private String username;
    private Integer status;
    private String email;
    private String firstName;
    private String lastName;
    private char gender;
    private String phone;
}
