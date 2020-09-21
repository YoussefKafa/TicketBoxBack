package com.project.tb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tb.dao.ReturnPasswordCodeModelRepo;
import com.project.tb.exceptions.ModelException;
import com.project.tb.models.ReturnPasswordCodeModel;
@Service
public class ReturnPasswordCodeModelServices {
@Autowired
ReturnPasswordCodeModelRepo returnPasswordCodeModelRepo;
public ReturnPasswordCodeModel saveOrUpdate(final ReturnPasswordCodeModel returnPasswordCodeModel) {
    try {
        return returnPasswordCodeModelRepo.save(returnPasswordCodeModel);
    } catch (final Exception e) {
        throw new ModelException("Invalid");
    } 
}
public Long getIdFromEmail(String email) {
	return returnPasswordCodeModelRepo.getIdFromEmail(email);
}
public Optional<ReturnPasswordCodeModel> findById(Long id) {
	return returnPasswordCodeModelRepo.findById(id);
}
public void deleteById(Long id) {
	returnPasswordCodeModelRepo.deleteById(id);
}
public boolean existsById(Long id) {
	return returnPasswordCodeModelRepo.existsById(id);
}
/*public boolean existsByEmail(String email) {
	return returnPasswordCodeModelRepo.existsByEmail(email);
}*/
}
