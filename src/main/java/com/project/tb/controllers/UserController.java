package com.project.tb.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import com.project.tb.models.User;
import com.project.tb.payload.JWTLoginSucesResponse;
import com.project.tb.payload.LoginRequest;
import com.project.tb.security.JwtTokenProvider;
import com.project.tb.services.UserServices;
import com.project.tb.validator.UserValidator;

import javax.validation.Valid;
import com.project.tb.services.MapValidationErrorService;
import static com.project.tb.security.SecurityConstants.TOKEN_PREFIX;
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
@PostMapping("/register")
public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
userValidator.validate(user, result);
ResponseEntity<?> errorMap=mapvalidationErrorService.mapValidationErrorService(result);
if (errorMap!=null) return errorMap;
User user1=userService.saveUser(user);
return new ResponseEntity<User>(user,HttpStatus.CREATED);
}
}