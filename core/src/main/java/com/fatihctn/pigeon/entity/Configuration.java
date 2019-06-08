package com.fatihctn.pigeon.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "Configuration")
@Getter @Setter @NoArgsConstructor
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @Column(name = "stringValue")
    private String stringValue;

    @Column(name = "intValue")
    private Integer intValue;

    @Column(name = "boolValue")
    private Boolean boolValue;

    @Column(name = "decimalValue", precision = 10, scale = 4)
    private BigDecimal decimalValue;
}
