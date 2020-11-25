package com.gagara.final_project.repositories;

import com.gagara.final_project.entities.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Long> {
    Information findByKey(String key);
}
