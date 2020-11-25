package com.gagara.final_project.entities;

import com.gagara.final_project.entities.Role;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS_T")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    private String login;

    @Column(name = "hash_password")
    private String hashPassword;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "EMPLOYEE_ROLE",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(){
    }
    public User(String login, String hashPassword, Set<Role> roles){
        this.login = login;
        this.hashPassword = hashPassword;
        this.roles = roles;
    }

    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getLogin(){
        return this.login;
    }
    public void setLogin(String login){
        this.login = login;
    }
    public String getHashPassword(){
        return this.hashPassword;
    }
    public void setHashPassword(String hashPassword){
        this.hashPassword = hashPassword;
    }
    public Set<Role> getRoles(){
        return this.roles;
    }
    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }

}
