package com.gagara.final_project.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name = "name")
    private String name;

    public Role(){
    }
    public Role(String name){
        this.name = name;
    }

    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
}
