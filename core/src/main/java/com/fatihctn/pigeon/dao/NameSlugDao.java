package com.fatihctn.pigeon.dao;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;

@Getter @Setter
public class NameSlugDao {
    private Integer id;
    @Max(64)
    private String name;
    private String slug;
}
