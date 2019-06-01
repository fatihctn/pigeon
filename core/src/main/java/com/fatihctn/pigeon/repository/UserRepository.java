package com.fatihctn.pigeon.repository;

import com.fatihctn.pigeon.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findByUsernameOrEmail(String usernameOrEmail);

    @Query(value = "FROM User WHERE username like %:query% or email like %:query% or firstName like %:query% or lastName like %:query%")
    Page<User> findAllByFilterQuery(String query, Pageable pageable);
}
