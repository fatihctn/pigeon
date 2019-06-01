package com.fatihctn.pigeon.service;

import com.fatihctn.pigeon.entity.User;
import com.fatihctn.pigeon.repository.UserRepository;
import com.fatihctn.pigeon.util.BCryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    private BCryptUtil bcrypt = new BCryptUtil(10);

    public User findByUsername(String username) {
        return Optional.of(repository.findByUsername(username)).orElse(new User());
    }

    public User findByEmail(String email) {
        return Optional.of(repository.findByEmail(email)).orElse(new User());
    }

    public User findById(int id) {
        return Optional.of(repository.findOne(id)).orElse(new User());
    }

    public boolean checkUsernameAndPassword(String usernameOrEmail, String password) {
        User entity = repository.findByUsernameOrEmail(usernameOrEmail);
        if (Objects.isNull(entity)) {
            logger.debug("User not found!");
            return false;
        }
        if (bcrypt.verifyHash(password, entity.getPassword())) {
            return true;
        }
        logger.debug("User saved password and input password not match!");
        return false;
    }

    public User checkAndGetUsernameAndPassword(String usernameOrEmail, String password) {
        User entity = repository.findByUsernameOrEmail(usernameOrEmail);
        if (Objects.isNull(entity)) {
            logger.info("User not found!");
            return null;
        }
        if (bcrypt.verifyHash(password, entity.getPassword())) {
            return entity;
        }
        logger.debug("User saved password and input password not match!");
        return null;
     }

    public Page<User> filter(String query, int page, int size) {
        logger.info("Filter method calling. page: {}, size: {}, query: {}", page, size, query);
        return repository.findAllByFilterQuery(query, new PageRequest(page, size, new Sort(new Sort.Order(Sort.Direction.DESC, "id"))));
    }
}
