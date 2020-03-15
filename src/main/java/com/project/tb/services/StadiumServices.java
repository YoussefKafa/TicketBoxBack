package com.project.tb.services;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tb.dao.*;
import com.project.tb.models.*;
import com.project.tb.exceptions.StadiumUniqueException;
@Service
public class StadiumServices{
    @Autowired
    private StadiumRepo stadiumRepo;
    public Stadium saveOrUpdate(final Stadium stad) {
        try {
            return stadiumRepo.save(stad);
        } catch (final Exception e) {
            throw new StadiumUniqueException("Stadium  "+ stad.getName().toLowerCase()+ " is already exists");
        }
        
    }
    public List<Stadium> findAll() {
        var it = stadiumRepo.findAll();
        var stadiums = new ArrayList<Stadium>();
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
}