package com.gagara.final_project.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "property")
public class Property implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String type;
    private String address;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "property_information",
        joinColumns = @JoinColumn(name = "property_id"),
        inverseJoinColumns = @JoinColumn(name = "information_id")
    )
    private Set<Information> information = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "property_convenience",
        joinColumns = @JoinColumn(name = "property_id"),
        inverseJoinColumns = @JoinColumn(name = "convenience_id")
    )
    private Set<Convenience> conveniences = new HashSet<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "property", cascade = CascadeType.ALL)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name ="property_images",
        joinColumns = @JoinColumn(name = "property_id"),
        inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private Set<Photo> photos = new HashSet<>();

    public Property(){
    }

    public Property(String type, String address, String description){
        this.type = type;
        this.address = address;
        this.description = description;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Set<Information> getInformation(){
        return this.information;
    }

    public void setInformation(Set<Information> information){
        this.information = information;
    }

    public Set<Convenience> getConveniences(){
        return this.conveniences;
    }

    public void setConveniences (Set<Convenience> conveniences){
        this.conveniences = conveniences;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }
}
