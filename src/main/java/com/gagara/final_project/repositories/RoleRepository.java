package com.gagara.final_project.repositories;

import com.gagara.final_project.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("select r from Role as r where r.id = :id ")
    Role getOne(Long id);
}
