package com.Employee.Employee.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "employeeLeaves")
@Entity
public class EmployeeLeave {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String empId;
	private String name;
	private String gender;
	private int sickLeaveNo;
	private int vacationNo;
	private String sickLeave;
	private String vacationLeave;
	private int year;
	private int maternity;

}
