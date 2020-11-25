package com.gagara.final_project.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "information")
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    @Column(name = "i_key")
    private String key;

    @Column(name = "info_description")
    private String infoDescription;


    public Information(){
    }
    public Information(String key, String infoDescription){
        this.key = key;
        this.infoDescription = infoDescription;
    }
    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getKey(){
        return this.key;
    }

    public void setKey(String key){
        this.key = key;
    }

    public String getInfoDescription(){
        return this.infoDescription;
    }

    public void setInfoDescription(String infoDescription){
        this.infoDescription = infoDescription;
    }

}
