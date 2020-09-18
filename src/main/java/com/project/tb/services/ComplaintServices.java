package com.project.tb.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.tb.dao.ComplaintRepo;
import com.project.tb.models.Complaint;
@Service
public class ComplaintServices {
@Autowired
ComplaintRepo complaintRepo;
public Complaint saveOrUpdate(final Complaint complaint) {
    return complaintRepo.save(complaint);
}
public List<Complaint> findAll() {
    Iterable<Complaint> it = complaintRepo.findAll();
    ArrayList<Complaint> complaints = new ArrayList<Complaint>();
    it.forEach(e -> complaints.add(e));
    return complaints;
}
public Long count() {
    return complaintRepo.count();
}
public void deleteById(Long complaintId) {
   complaintRepo.deleteById(complaintId);
}
public void deleteAll() {
    complaintRepo.deleteAll();
 }
public Optional<Complaint> findById(Long id) {
	return complaintRepo.findById(id);
}
}
