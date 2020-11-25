package com.gagara.final_project.controllers;

import com.gagara.final_project.entities.Property;
import com.gagara.final_project.payload.PropertyRequest;
import com.gagara.final_project.services.PropertyService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class PropertyController {
    private PropertyService propertyService;
    private ServletContext servletContext;

    public PropertyController(PropertyService propertyService, ServletContext servletContext){
        this.propertyService = propertyService;
        this.servletContext = servletContext;
    }

    @GetMapping("/allProperty")
    public List<Property> getAllProperty(){
        return propertyService.getAll();
    }

    @PostMapping("/addProperty")
    @PreAuthorize("hasAuthority('admin')")
    public Property addNewProperty(@Valid @RequestBody PropertyRequest information){

        return propertyService.addProperty(information);
    }

    @PostMapping("/updateProperty/{id}")
    public Property updateProperty(@Valid @RequestBody PropertyRequest information, @PathVariable ("id") Long id){
        return propertyService.setProperty(information, id);
    }

    @PostMapping("/addPhotos/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<String> addNewPhotos(@RequestParam("photo") MultipartFile[] photos, @PathVariable ("id") Long id){
        try {
            propertyService.addPhotos(photos, id);
            return ResponseEntity.ok("photos has been added");
        }
        catch (IOException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/getPhoto/{id}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable ("id") Long id){
       try{
           Path path = propertyService.getPhoto(id);
           byte[] photo = Files.readAllBytes(path);
           return ResponseEntity.ok().contentType(MediaType.parseMediaType(servletContext.getMimeType(path.toAbsolutePath().toString())))
               .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + path.getFileName() + "\"")
               .body(photo);
       }
       catch (IOException exception){
           return ResponseEntity.ok().body(null);
       }
    }


    @GetMapping("/property/{id}")
    public Property getProperty(@PathVariable("id") Long id){

        return propertyService.getProperty(id);
    }

    @GetMapping("/deleteProperty/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<String> deleteProperty(@PathVariable Long id){
        try {
            propertyService.removeProperty(id);
            return ResponseEntity.ok("property has been removed");
        }
        catch (IOException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
