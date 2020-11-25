package com.gagara.final_project.payload;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class PropertyRequest {
    @NotBlank
    private String type;
    @NotBlank
    private String address;
    @NotBlank
    private String description;

    private Set<String> information;

    private Set<String> convenience;


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

    public Set<String> getInformation(){
        return this.information;
    }

    public void setInformation(Set<String> information){
        this.information = information;
    }

    public Set<String> getConvenience(){
        return this.convenience;
    }

    public void setConvenience(Set<String> convenience){
        this.convenience = convenience;
    }

}
