package com.project.tb.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tb.dao.*;
import com.project.tb.models.*;
import com.project.tb.exceptions.TeamUniqueException;
@Service
public class TeamServices{
    @Autowired
    private TeamRepo teamRepo;
    public Team saveOrUpdate(final Team team) {
        try {
            return teamRepo.save(team);
        } catch (final Exception e) {
            throw new TeamUniqueException("Team  "+ team.getName().toLowerCase()+ " is already exists");
        }
        
    }
}