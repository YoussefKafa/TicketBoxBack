package com.project.tb.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tb.dao.*;
import com.project.tb.exceptions.ModelException;
import com.project.tb.models.*;
@Service
public class TeamServices{
    @Autowired
    private TeamRepo teamRepo;
    public Team saveOrUpdate(final Team team) {
        try {
            return teamRepo.save(team);
        } catch (final Exception e) {
            throw new ModelException("Team  "+ team.getName().toLowerCase()+ " is already exists");
        }
        
    }
    public List<Team> findAll() {
        Iterable<Team> it = teamRepo.findAll();
        ArrayList<Team> teams = new ArrayList<Team>();
        it.forEach(e -> teams.add(e));
        return teams;
    }
    public Long count() {
        return teamRepo.count();
    }
    public void deleteById(Long teamId) {
       teamRepo.deleteById(teamId);
    }
    public void deleteAll() {
        teamRepo.deleteAll();
     }
    public void deleteAll(Team team) {
        teamRepo.delete(team);
     }
    public Optional<Team> findById(Long id) {
		return teamRepo.findById(id);
	}
}