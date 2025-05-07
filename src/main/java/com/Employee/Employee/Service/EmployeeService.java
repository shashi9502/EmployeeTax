package com.Employee.Employee.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.Employee.EmployeeException;
import com.Employee.Employee.Model.EmployeeLeave;
import com.Employee.Employee.Repository.EmployeeRepository;


@Service
public class EmployeeService {
	@Autowired(required=true)
	private EmployeeRepository employeeRepository;

	public String saveEmployee(EmployeeLeave dto) {
		
		EmployeeLeave data = employeeRepository.findByEmployeeIdAndYear(dto.getEmpId(),dto.getYear());
		
		if (data == null) {
			EmployeeLeave old = employeeRepository.findByNameAndYear(dto.getName(),dto.getYear()-1);
			if(old.getSickLeaveNo()!=0) {
				int leave=old.getSickLeaveNo()>5?5:old.getSickLeaveNo();
				dto.setSickLeaveNo(dto.getSickLeaveNo()+leave);
			}
			EmployeeLeave emp = employeeRepository.save(dto);
			return "employee details saved succefully";
		}
		throw new EmployeeException("Employee allready Exist!");
	}

	public String leaveApplyEmployee( String empId, String type,int noOfLeave,int year) {
		EmployeeLeave data = employeeRepository.findByEmployeeIdAndYear(empId,year);
		if (data != null) {
			if (data.getSickLeave().equalsIgnoreCase(type)) {
				 if(data.getSickLeaveNo()<=noOfLeave) {
					 data.setSickLeaveNo( data.getSickLeaveNo()-noOfLeave);
				 }
				 throw new EmployeeException("you are not elgible for leaves!");
			}
			if (data.getVacationLeave().equalsIgnoreCase(type)) {
				if(data.getSickLeaveNo()<=noOfLeave) {
					 data.setVacationNo(data.getVacationNo()-noOfLeave);
            				 }
				 throw new EmployeeException("you are not elgible for leaves!");
			}
			if(data.getGender().equals("feMale") ) {
				if( data.getMaternity()!=1) {
				data.setMaternity(1);
				}
				throw new EmployeeException("you are not elgible for leaves!");
			}
			employeeRepository.save(data);
			return "your leave application accepted";
		}
		throw new EmployeeException("Employee Details not Exist");
	}

}
