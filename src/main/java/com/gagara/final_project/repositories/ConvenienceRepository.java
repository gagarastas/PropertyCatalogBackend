package com.gagara.final_project.repositories;

import com.gagara.final_project.entities.Convenience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvenienceRepository extends JpaRepository<Convenience, Long> {
    Convenience findByKey(String key);
}
