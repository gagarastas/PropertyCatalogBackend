package com.gagara.final_project.repositories;

import com.gagara.final_project.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Photo findByPath(String path);
    boolean existsByPath(String path);
    void deleteByPath(String path);

}
