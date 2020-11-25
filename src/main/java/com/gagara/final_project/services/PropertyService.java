package com.gagara.final_project.services;

import com.gagara.final_project.customExceptions.AppException;
import com.gagara.final_project.entities.Convenience;
import com.gagara.final_project.entities.Information;
import com.gagara.final_project.entities.Photo;
import com.gagara.final_project.entities.Property;
import com.gagara.final_project.payload.PropertyRequest;
import com.gagara.final_project.repositories.ConvenienceRepository;
import com.gagara.final_project.repositories.InformationRepository;
import com.gagara.final_project.repositories.PhotoRepository;
import com.gagara.final_project.repositories.PropertyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PropertyService {
    private PropertyRepository propertyRepository;
    private InformationRepository informationRepository;
    private ConvenienceRepository convenienceRepository;
    private PhotoRepository photoRepository;

    public PropertyService(PropertyRepository propertyRepository, InformationRepository informationRepository, ConvenienceRepository convenienceRepository, PhotoRepository photoRepository){
        this.convenienceRepository = convenienceRepository;
        this.informationRepository = informationRepository;
        this.propertyRepository = propertyRepository;
        this.photoRepository = photoRepository;
    }

    @Transactional
    public List<Property> getAll(){
        List<Property> bb = propertyRepository.findAll();
        return bb;
    }


    @Transactional
    public Property addProperty(PropertyRequest propertyRequest){
        Property property = new Property(propertyRequest.getType(), propertyRequest.getAddress(), propertyRequest.getDescription());

        Set<String> convenienceKeySet = propertyRequest.getConvenience();
        Set<String> informationKeySet = propertyRequest.getInformation();

        fillProperty(property, convenienceKeySet, informationKeySet);

        return propertyRepository.save(property);
    }

    @Transactional
    public Property setProperty(PropertyRequest propertyRequest, Long id){
        if(!propertyRepository.existsById(id)){
            throw new EntityNotFoundException("property does not exist");
        }
        Property property = propertyRepository.getOne(id);

        property.setType(propertyRequest.getType());
        property.setAddress(propertyRequest.getAddress());
        property.setDescription(propertyRequest.getDescription());
        property.setPhotos(null);

        Set<String> convenienceKeySet = propertyRequest.getConvenience();
        Set<String> informationKeySet = propertyRequest.getInformation();

        fillProperty(property, convenienceKeySet, informationKeySet);

        return propertyRepository.save(property);


    }

    @Transactional
    public void addPhotos(MultipartFile[] photos, Long id) throws IOException {
        if(!propertyRepository.existsById(id)){
            throw new EntityNotFoundException("property does not exist");
        }
        Property property = propertyRepository.getOne(id);
        Set<Photo> imagesSet = new HashSet<>();

        deletePhotos(id);

        Files.createDirectory(Paths.get("src","main","resources","images",id.toString()));

        for(MultipartFile photo: photos){
            byte[] photoBytes = photo.getBytes();
            Path imagePath = Paths.get("src","main","resources","images",id.toString(),photo.getOriginalFilename());
            Files.write(imagePath, photoBytes);
            Photo newPhoto = new Photo(imagePath.toString());
            imagesSet.add(newPhoto);

        }
        property.setPhotos(imagesSet);
        propertyRepository.save(property);
    }

    @Transactional
    public Path getPhoto(Long id) {
        Photo photo = photoRepository.getOne(id);
        String stringPath = photo.getPath();
        Path path = Paths.get(stringPath);

        return path;

//        return Files.readAllBytes(path);
    }

    @Transactional
    public Property getProperty(long id){
        Property property = propertyRepository.getOne(id);
        if(property == null){
            throw new EntityNotFoundException("property does not exist. id: "+ id);
        }
        return property;
    }


    public void removeProperty(Long id) throws IOException {
        if(!propertyRepository.existsById(id)){
            throw new EntityNotFoundException("property does not exist. id: "+ id);
        }

        propertyRepository.deleteById(id);
        deletePhotos(id);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================
    private void fillProperty(Property property,Set<String> convenienceKeySet, Set<String> informationKeySet){
        Set<Convenience> convenienceSet = new HashSet<>();
        Set<Information> informationSet = new HashSet<>();


        for(String convenienceKey: convenienceKeySet){
            Convenience convenience = convenienceRepository.findByKey(convenienceKey);
            convenienceSet.add(convenience);
        }
        for(String informationKey: informationKeySet){
            Information information = informationRepository.findByKey(informationKey);
            informationSet.add(information);
        }

        property.setConveniences(convenienceSet);
        property.setInformation(informationSet);
    }

    private void deletePhotos(Long propertyId) throws IOException {
        Path directoryPath = Paths.get("src","main","resources","images",propertyId.toString());

        if(Files.exists(directoryPath)){
            File imageDirectory = new File(directoryPath.toString());
            File[] listOfFiles = imageDirectory.listFiles();
            if(listOfFiles != null) {
                for (File file : listOfFiles) {
                    String imagePath = directoryPath.toString() + "\\" + file.getName();
                    file.delete();
                    photoRepository.deleteByPath(imagePath);
                }
            }
            Files.delete(directoryPath);
        }
    }

}
