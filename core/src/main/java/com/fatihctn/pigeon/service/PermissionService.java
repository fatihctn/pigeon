package com.fatihctn.pigeon.service;

import com.fatihctn.pigeon.entity.Permission;
import com.fatihctn.pigeon.repository.PermissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionService {
    private final static Logger logger = LoggerFactory.getLogger(PermissionService.class);

    @Autowired
    private PermissionRepository repository;

    public Permission findById(int permissionId) {
        return Optional.of(repository.findOne(permissionId)).orElse(new Permission());
    }

    public Page<Permission> filter(String query, int page, int size) {
        logger.info("Filter method calling. page: {}, size: {}, query: {}", page, size, query);
        return repository.findAllByNameContaining(query, new PageRequest(page, size, new Sort(new Sort.Order(Sort.Direction.DESC, "id"))));
    }
}
