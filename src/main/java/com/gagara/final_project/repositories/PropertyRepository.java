package com.gagara.final_project.repositories;

import com.gagara.final_project.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property,Long> {
    @Query("select p from Property as p left join p.conveniences join p.information")
    List<Property> findAllWithFullInfo();

    @Query("select p from Property as p where p.id = :id ")
    Property getOne(Long id);
}
