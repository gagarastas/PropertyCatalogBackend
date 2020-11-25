package com.gagara.final_project.repositories;

import com.gagara.final_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

    boolean existsByLogin(String login);
}
