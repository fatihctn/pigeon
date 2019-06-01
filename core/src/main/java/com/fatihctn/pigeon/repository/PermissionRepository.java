package com.fatihctn.pigeon.repository;

import com.fatihctn.pigeon.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    Permission findByName(String name);

    Permission findBySlug(String slug);

    Page<Permission> findAllByNameContaining(String name, Pageable pageable);
}
