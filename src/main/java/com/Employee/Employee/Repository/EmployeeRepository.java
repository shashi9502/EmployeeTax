package com.Employee.Employee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Employee.Employee.Model.EmployeeLeave;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeLeave,Long>{

	//EmployeeLeave findByEmpId(String empId);
	@Query(value="select * from employee_Leaves where name=:name and year=:year",nativeQuery=true)
	EmployeeLeave findByNameAndYear(String name, int year);
	@Query(value="select * from employee_Leaves where emp_Id=:empId and year=:year",nativeQuery=true)
	EmployeeLeave findByEmployeeIdAndYear(String empId, int year);

}
