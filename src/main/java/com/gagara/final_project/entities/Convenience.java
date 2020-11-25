package com.gagara.final_project.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "convenience")
public class Convenience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    @Column(name = "c_key")
    private String key;

    @Column(name = "convenience_description")
    private String convenienceDescription;

    public Convenience(){
    }

    public Convenience(String key, String convenienceDescription){
        this.key = key;
        this.convenienceDescription = convenienceDescription;
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

    public String getConvenienceDescription(){
        return this.convenienceDescription;
    }

    public void setConvenienceDescription(String convenienceDescription){
        this.convenienceDescription = convenienceDescription;
    }
}
