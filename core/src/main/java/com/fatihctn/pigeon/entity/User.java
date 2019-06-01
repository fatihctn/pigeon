package com.fatihctn.pigeon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatihctn.pigeon.entity.embeddedable.CreateUpdateDeleteTimestamp;
import com.fatihctn.pigeon.enums.Gender;
import com.fatihctn.pigeon.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "email", nullable = false, unique = true)
    @Max(255)
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    @Min(6)
    @Max(64)
    private String password;

    @Column(name = "firstName")
    @Max(60)
    private String firstName;

    @Column(name = "lastName")
    @Max(80)
    private String lastName;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "phone", unique = true)
    private String phone;

    @Embedded
    private CreateUpdateDeleteTimestamp timestamps;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"roleId", "userId"})}
    )
    private Set<Role> roles;
}
