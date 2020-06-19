package com.project.tb.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import com.project.tb.exceptions.EmployeeUniqueException;
import com.project.tb.models.Employee;
import com.project.tb.models.User;
import com.project.tb.payload.JWTLoginSucesResponse;
import com.project.tb.payload.LoginRequest;
import com.project.tb.security.JwtTokenProvider;
import com.project.tb.services.UserServices;
import com.project.tb.validator.UserValidator;
import javax.validation.Valid;
import com.project.tb.services.MapValidationErrorService;
import static com.project.tb.security.SecurityConstants.TOKEN_PREFIX;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
@RestController
@RequestMapping("/api/users")
@CrossOrigin
class UserController{
    @Autowired
    private MapValidationErrorService mapvalidationErrorService;
    @Autowired
    private UserServices userService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
   //tested
    @PostMapping("/login")
    public ResponseEntity<?> authinticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
    	ResponseEntity<?> errorMap=mapvalidationErrorService.mapValidationErrorService(result);
    	if (errorMap!=null) return errorMap;
    	
    	Authentication authentication=authenticationManager.authenticate(
    			new UsernamePasswordAuthenticationToken
    			(loginRequest.getEmail(), loginRequest.getPassword()));
    	SecurityContextHolder.getContext().setAuthentication(authentication);
    	String jwt=TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
    	return ResponseEntity.ok(new JWTLoginSucesResponse(true, jwt));
    }
    //tested
    @RequestMapping(value = "/save")
public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) throws Exception{
userValidator.validate(user, result);
ResponseEntity<?> errorMap=mapvalidationErrorService.mapValidationErrorService(result);
if (errorMap!=null) return errorMap;
User user1=userService.saveUser(user);
return new ResponseEntity<User>(user,HttpStatus.CREATED);
}
//tested
@GetMapping("/findAll")
public List<User> allUsers() {
	return userService.findAll();
}
//tested
@GetMapping("/count")
public Long count() {
    return userService.count();
}
@GetMapping("/statistics/countMaleUsers")
public int countMaleUsers() {
    return userService.countMaleUsers();
}
@GetMapping("/statistics/countFemaleUsers")
public int countFemaleUsers() {
    return userService.countFemaleUsers();
}
@GetMapping("/statistics/countAgeGroupsOfUsers")
public List<Object[]> countAgeGroupsOfUsers() {
 List<Object[]> results=  userService.countAgeGroupsOfUsers();
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
@DeleteMapping("/deleteById/{userId}")
public ResponseEntity<?> deleteEmployeeById(@PathVariable String userId){
	User user=userService.findById(Long.parseLong(userId));
	if (user ==null) {
		throw new EmployeeUniqueException("User with id : " + userId + " does not exist");
	}
  userService.deleteById(Long.parseLong(userId));
  return new ResponseEntity<String>("User with id " + userId+ " was deleted", HttpStatus.OK);
}
@DeleteMapping("/deleteAll")
public ResponseEntity<?> deleteAll(){
	userService.deleteAll();
  return new ResponseEntity<String>("All Users were deleted", HttpStatus.OK);
}
}