package com.fatihctn.pigeon.service;

import com.fatihctn.pigeon.entity.Role;
import com.fatihctn.pigeon.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final static Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleRepository repository;

    public Role findByName(String name) {
        return Optional.of(repository.findByName(name)).orElse(new Role());
    }

    public Role findBySlug(String slug) {
        return Optional.of(repository.findByName(slug)).orElse(new Role());
    }

    public Role findById(int id) {
        return Optional.of(repository.findOne(id)).orElse(new Role());
    }

    public Page<Role> filter(String query, int page, int size) {
        logger.info("Filter method calling. page: {}, size: {}, query: {}", page, size, query);
        return repository.findAllByNameContaining(query, new PageRequest(page, size, new Sort(new Sort.Order(Sort.Direction.DESC, "id"))));
    }
}
