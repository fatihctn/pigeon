package com.fatihctn.pigeon.dao;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Getter @Setter
public class ConfigurationDao{
    @Null
    private Integer id;
    @NotNull
    @Max(64)
    private String name;
    private String stringValue;
    private Boolean boolValue;
    private Integer intValue;
    private BigDecimal decimalValue;
}
