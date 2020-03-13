package com.project.tb.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import javax.validation.Valid;
import com.project.tb.services.MapValidationErrorService;
import com.project.tb.exceptions.EmployeeUniqueException;
import com.project.tb.models.Employee;
import com.project.tb.services.EmployeeServices;
@RestController
@RequestMapping("/api/employee")
class EmployeeController{
@Autowired
private MapValidationErrorService mapvalidationErrorService;
@Autowired
private EmployeeServices employeeService;
//tested
@PostMapping("/save")
public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee, BindingResult result){
      ResponseEntity<?> errorMap=mapvalidationErrorService.mapValidationErrorService(result);
      if (errorMap!=null) return errorMap;
    Employee employee2=employeeService.saveOrUpdate(employee);
return new ResponseEntity<Employee>(employee,HttpStatus.CREATED);
}
//tested
@GetMapping("/getById/{id}")
public ResponseEntity<?> getEmployeeById(@PathVariable String id){
    Employee employee = employeeService.findById(Long.parseLong(id));
return new ResponseEntity<Employee>(employee,HttpStatus.OK);
}
//tested
@GetMapping("/findAll")
public List<Employee> allEmployees() {
	return employeeService.findAll();
}
//tested
@GetMapping("/count")
public Long count() {
    return employeeService.count();
}
//tested
@DeleteMapping("/deleteById/{empId}")
public ResponseEntity<?> deleteEmployeeById(@PathVariable String empId){
	Employee employee=employeeService.findById(Long.parseLong(empId));
	if (employee ==null) {
		throw new EmployeeUniqueException("Employee with id : " + empId + " does not exist");
	}
  employeeService.deleteById(Long.parseLong(empId));
  return new ResponseEntity<String>("Employee with id " + empId + " was deleted", HttpStatus.OK);
}
//tested
@DeleteMapping("/deleteAll")
public ResponseEntity<?> deleteAll(){
	employeeService.deleteAll();
  return new ResponseEntity<String>("All Employees were deleted", HttpStatus.OK);
}
}