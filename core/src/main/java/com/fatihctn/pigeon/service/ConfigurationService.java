package com.fatihctn.pigeon.service;

import com.fatihctn.pigeon.entity.Configuration;
import com.fatihctn.pigeon.repository.ConfigurationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigurationService {
    private final static Logger logger = LoggerFactory.getLogger(ConfigurationService.class);

    @Autowired
    private ConfigurationRepository repository;

    public Configuration findByName(String name) {
        logger.info("Finding configuration. name: {}", name);
        return Optional.of(repository.findByName(name)).orElse(new Configuration());
    }
}
