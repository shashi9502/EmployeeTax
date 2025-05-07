package com.Employee.Employee.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.Employee.Model.EmployeeLeave;
import com.Employee.Employee.Service.EmployeeService;

@RestController
@RequestMapping("/api/v1.0/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeInService;
	
	@PostMapping("/create")
public	String saveEmployee(@RequestBody EmployeeLeave dto){
		String data=employeeInService.saveEmployee(dto);
		return data;
}
	@GetMapping("/leaveApply/{empId}/{type}/{noOfLeave}/{year}")
public	String leaveApplyEmployee(@PathVariable String empId,@PathVariable String type,@PathVariable int noOfLeave,@PathVariable int year){
		String data=employeeInService.leaveApplyEmployee(empId,type,noOfLeave,year);
		return data;
}	
}
