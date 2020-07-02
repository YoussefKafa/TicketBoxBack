package com.project.tb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;

import com.project.tb.dao.UserRepo;
import com.project.tb.exceptions.EmployeeUniqueException;
import com.project.tb.exceptions.ResourceNotFoundException;
import com.project.tb.models.User;
import com.project.tb.payload.UserIdentityAvailability;
import com.project.tb.payload.UserProfile;
import com.project.tb.payload.UserSummary;
import com.project.tb.security.CurrentUser;
import com.project.tb.services.UserServices;
import com.project.tb.validator.UserValidator;
import com.project.tb.wrappers.UserPrincipal;

import javax.validation.Valid;
import com.project.tb.services.MapValidationErrorService;

import java.math.BigDecimal;
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
	private UserValidator userValidator;
	@Autowired
	private UserRepo userRepository;

	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(),
				currentUser.getName());
		return userSummary;
	}

	@GetMapping("/user/checkEmailAvailability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		Boolean isAvailable = !userRepository.existsByEmail(email);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/users/{email}")
	public UserProfile getUserProfile(@PathVariable(value = "email") String email) {
		User user;
		try {
			user = userRepository.findByEmail(email);
		} catch (Exception e) {
			throw new ResourceNotFoundException("User", "email", email);
		}
		UserProfile userProfile = new UserProfile(user.getId(), user.getName(), user.getName(), user.getCreatedAt());

		return userProfile;
	}

	@RequestMapping(value = "/save")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) throws Exception {
		userValidator.validate(user, result);
		ResponseEntity<?> errorMap = mapvalidationErrorService.mapValidationErrorService(result);
		if (errorMap != null)
			return errorMap;
		User user1 = userService.saveUser(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/show/findById/{userId}")
	public User findById(@PathVariable Long userId) {
		return userService.findById(userId);
	}
//tested
	@GetMapping("/show/admin/findAll")
	public List<User> allUsers() {
		return userService.findAll();
	}

//tested
	@GetMapping("/admin/count")
	public Long count() {
		return userService.count();
	}

	@GetMapping("/admin/statistics/countMaleUsers")
	public int countMaleUsers() {
		return userService.countMaleUsers();
	}
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/admin/addCredit/{userId}/{credit}")
	public void addCredit(@PathVariable long userId,@PathVariable int credit) {
		userService.addCredit(credit, userId);
	}
	@GetMapping("/admin/statistics/countFemaleUsers")
	public int countFemaleUsers() {
		return userService.countFemaleUsers();
	}

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
	@DeleteMapping("/admin/deleteById/{userId}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable String userId) {
		User user = userService.findById(Long.parseLong(userId));
		if (user == null) {
			throw new EmployeeUniqueException("User with id : " + userId + " does not exist");
		}
		userService.deleteById(Long.parseLong(userId));
		return new ResponseEntity<String>("User with id " + userId + " was deleted", HttpStatus.OK);
	}

	@DeleteMapping("/admin/deleteAll")
	public ResponseEntity<?> deleteAll() {
		userService.deleteAll();
		return new ResponseEntity<String>("All Users were deleted", HttpStatus.OK);
	}
}