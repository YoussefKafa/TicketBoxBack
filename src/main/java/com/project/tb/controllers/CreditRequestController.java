package com.project.tb.controllers;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.tb.payload.CreditRequest;
import com.project.tb.services.CreditRequestServices;
import com.project.tb.services.UserServices;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/creditRequest")
public class CreditRequestController {
	@Autowired
	private UserServices userService;
	@Autowired
	private CreditRequestServices creditRequestServices;
	@Secured({"ROLE_DISTU","ROLE_ADMIN"})
	@PostMapping("/admin/addCreditByEmail")
	public void addCredit(@RequestBody CreditRequest creditRequest) {
		creditRequestServices.saveOrUpdate(creditRequest);
		userService.addCredit(creditRequest.getCredit(), userService.findByEmail(creditRequest.getEmail()).getId());
		
	}
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/admin/findAll")
	public ArrayList<CreditRequest> findAll() {
		return creditRequestServices.findAll();
	}
	@Secured({"ROLE_DISTU","ROLE_ADMIN"})
	@PostMapping("/addUser/{credit_id}/{user_id}")
	public void addGame(@PathVariable Long credit_id,@PathVariable Long user_id) {
		 creditRequestServices.addUser(credit_id, user_id);
	}
}
