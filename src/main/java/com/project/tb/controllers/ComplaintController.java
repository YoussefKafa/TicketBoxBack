package com.project.tb.controllers;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.tb.models.Complaint;
import com.project.tb.services.ComplaintServices;
import com.project.tb.services.MapValidationErrorService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {
	@Autowired
	private ComplaintServices complaintServices;
	@Autowired
	private MapValidationErrorService mapvalidationErrorService;
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody Complaint complaint, BindingResult result){
	      ResponseEntity<?> errorMap=mapvalidationErrorService.mapValidationErrorService(result);
	      if (errorMap!=null) return errorMap;
	      complaintServices.saveOrUpdate(complaint);
	return new ResponseEntity<Complaint>(complaint,HttpStatus.CREATED);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findAll")
	public List<Complaint> allComplaints() {
		return complaintServices.findAll();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/count")
	public Long count() {
	    return complaintServices.count();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteById/{id}")
	public void deleteById(@PathVariable Long id) {
		complaintServices.deleteById(id);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findById/{id}")
	public Optional<Complaint> findById(@PathVariable Long id) {
	    return complaintServices.findById(id);
	}
}
