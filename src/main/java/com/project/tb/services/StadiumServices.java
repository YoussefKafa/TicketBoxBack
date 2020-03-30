package com.project.tb.services;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tb.dao.*;
import com.project.tb.models.*;
import com.project.tb.exceptions.StadiumUniqueException;
@Service
public class StadiumServices<E>{
    @Autowired
    private StadiumRepo stadiumRepo;
    @Autowired
    private GameRepo gameRepo;
    public Stadium saveOrUpdate(final Stadium stad) {
        try {
        	if(stad.getStadiumId()==null)
            return stadiumRepo.save(stad);
        	else {
        	Optional<Stadium> newStadium=stadiumRepo.findById(stad.getStadiumId());
        	stad.setGames(newStadium.get().getGames());
        	return stadiumRepo.save(stad);
        		}
        } catch ( Exception e) {
            throw new StadiumUniqueException("Stadium  "+ stad.getName().toLowerCase()+ " is already exists");
        }
        
    }
    public List<Stadium> findAll() {
        Iterable<Stadium> it = stadiumRepo.findAll();
        ArrayList<Stadium> stadiums = new ArrayList<Stadium>();
        it.forEach(e -> stadiums.add(e));
        return stadiums;
    }
    public Long count() {
        return stadiumRepo.count();
    }
    public void deleteById(Long stadiumId) {
       stadiumRepo.deleteById(stadiumId);
    }
    public void deleteAll() {
        stadiumRepo.deleteAll();
     }
    public void deleteAll(Stadium stadium) {
        stadiumRepo.delete(stadium);
     }
    public void deleteByName(String name) {
        stadiumRepo.deleteByName(name);
     }
    public String getIdByName(String name) {
    
  return stadiumRepo.getIdByName(name);
       }
  
    public Optional<Stadium> findByName(String name) {
        Optional<Stadium> resultStadium=stadiumRepo.findById(Long.parseLong(stadiumRepo.getIdByName(name)) );
        return resultStadium;
       }
}