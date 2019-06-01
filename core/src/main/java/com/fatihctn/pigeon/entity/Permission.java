package com.fatihctn.pigeon.entity;

import com.fatihctn.pigeon.entity.embeddedable.CreateUpdateTimestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
import java.util.Set;

@Entity
@Table(name = "permissions")
@Getter @Setter @NoArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "slug", nullable = false, unique = true)
    private String slug;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Embedded
    private CreateUpdateTimestamp createUpdateTimestamp;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "permissionId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"roleId", "permissionId"})}
    )
    private Set<Role> roles;
}
