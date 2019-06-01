package com.fatihctn.pigeon.repository;

import com.fatihctn.pigeon.entity.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {

    Configuration findByName(String name);
}
