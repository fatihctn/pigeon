package com.fatihctn.pigeon.repository;

import com.fatihctn.pigeon.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

    Role findBySlug(String slug);

    Page<Role> findAllByNameContaining(String name, Pageable pageable);
}
