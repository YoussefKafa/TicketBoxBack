package com.project.tb.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import com.project.tb.dao.CreditRequestRepo;
import com.project.tb.dao.ReturnPasswordCodeModelRepo;
import com.project.tb.dao.UserRepo;
import com.project.tb.exceptions.ModelException;
import com.project.tb.models.Ticket;
import com.project.tb.models.User;
import com.project.tb.payload.ChangeForgPassword;
import com.project.tb.payload.ChangePasswordRequest;
import com.project.tb.services.UserServices;
import javax.validation.Valid;
import com.project.tb.services.MapValidationErrorService;
import com.project.tb.services.ReturnPasswordCodeModelServices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
class UserController {
	@Autowired
	private MapValidationErrorService mapvalidationErrorService;
	@Autowired
	private UserServices userService;
	@Autowired
	private UserRepo userRepository;
	@Autowired
	private ReturnPasswordCodeModelServices returnPasswordCodeModelServices;
	@Autowired
	private CreditRequestRepo creditRequestRepo;
	@Secured({"ROLE_USER","ROLE_ADMIN","ROLE_DISTU"})
	@RequestMapping(value = "/save")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) throws Exception {
		User user1 = userService.saveUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	@Secured({"ROLE_USER","ROLE_ADMIN","ROLE_DISTU"})
	@GetMapping("/show/findById/{userId}")
	public User findById(@PathVariable Long userId) {
		return userService.findById(userId);
	}
//tested
	@Secured({"ROLE_ADMIN","ROLE_DISTU"})
	@GetMapping("/show/admin/findAll")
	public List<User> allUsers() {
		return userService.findAll();
	}

//tested
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/count")
	public Long count() {
		return userService.count();
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/statistics/countMaleUsers")
	public int countMaleUsers() {
		return userService.countMaleUsers();
	}

    @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/statistics/countFemaleUsers")
	public int countFemaleUsers() {
		return userService.countFemaleUsers();
	}
    @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/statistics/countAgeGroupsOfUsers")
	public List<Object[]> countAgeGroupsOfUsers() {
		List<Object[]> results = userService.countAgeGroupsOfUsers();
		results.stream().forEach((record) -> {
			int Aclass = ((BigDecimal) record[0]).intValue();
			int Bclass = ((BigDecimal) record[1]).intValue();
			int Cclass = ((BigDecimal) record[2]).intValue();
			int Dclass = ((BigDecimal) record[3]).intValue();
			int Eclass = ((BigDecimal) record[4]).intValue();
			int Fclass = ((BigDecimal) record[5]).intValue();
			int total = ((BigDecimal) record[6]).intValue();
		});
		return results;
	}

//tested
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/admin/deleteById/{userId}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable String userId) {
		User user = userService.findById(Long.parseLong(userId));
		if (user == null) {
			throw new ModelException("User with id : " + userId + " does not exist");
		}
		userService.deleteById(Long.parseLong(userId));
		return new ResponseEntity<String>("User with id " + userId + " was deleted", HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/admin/deleteAll")
	public ResponseEntity<?> deleteAll() {
		userService.deleteAll();
		return new ResponseEntity<String>("All Users were deleted", HttpStatus.OK);
	}
	@Secured({"ROLE_ADMIN","ROLE_DISTU","ROLE_USER"})
	@GetMapping("/getTicketsByUserId/{userId}")
	public ArrayList<Ticket> getTicketsByUserId(@PathVariable String userId){
		return userService.getTicketsByUserId(Integer.parseInt(userId));
	}
	@Secured({"ROLE_USER","ROLE_ADMIN","ROLE_DISTU"})
	@GetMapping("/getRoleIdFromRoleName/{name}")
	public int getRoleIdFromRoleName(@PathVariable String name) {
		return userService.getRoleIdFromRoleName(name);
	}
	@Secured({"ROLE_ADMIN","ROLE_DISTU","ROLE_USER"})
@PostMapping("/changePassword")
public String changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
	userService.changePassword(changePasswordRequest);
	return "Password Updated successfully";
}
	@PostMapping("/show/returnPassword")
	public String changeForgPassword(@Valid @RequestBody ChangeForgPassword changeForgPassword) {
		try {
			userService.returnPassword(changeForgPassword);
		} catch (Exception e) {
		throw new ModelException("Incorrect Information");
		}
		return "Password Updated successfully";
	}
	@GetMapping("/show/sendCodeMessage/{email}")
	public String changePassword(@PathVariable String email) {
		userService.forgetPassword(email);
		return "Please Check Your Email";
	}
	@GetMapping("/show/getIdFromEmail/{email}")
	public long getIdFromEmail(@PathVariable String email) {
		return userRepository.getIdFromEmail(email);
	}
	@DeleteMapping("/show/deleteForgEmail/{email}")
	public void deleteForgEmail(@PathVariable String email) {
		try {
			returnPasswordCodeModelServices.deleteById(returnPasswordCodeModelServices.getIdFromEmail(email)); 

		} catch (Exception e) {
			throw new ModelException("Invalid Date");
		}
	}
}