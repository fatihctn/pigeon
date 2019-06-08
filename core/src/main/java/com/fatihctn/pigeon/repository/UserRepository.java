package com.fatihctn.pigeon.repository;

import com.fatihctn.pigeon.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    @Query(value = "FROM User WHERE username=:usernameOrEmail or email=:usernameOrEmail")
    User findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

    @Query(value = "SELECT user FROM User user WHERE user.username like %:query% or user.email like %:query% or user.firstName like %:query% or user.lastName like %:query%")
    Page<User> findAllByFilterQuery(@Param("query") String query, Pageable pageable);
}
