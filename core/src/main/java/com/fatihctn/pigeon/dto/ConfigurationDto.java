package com.fatihctn.pigeon.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class ConfigurationDto {
    private int id;
    private String name;
    private String stringValue;
    private Boolean boolValue;
    private Integer intValue;
    private BigDecimal decimalValue;
}
