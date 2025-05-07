package com.Employee.Employee.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Employee.Employee.Model.SalesPerson;

public interface SalesPersonRepository  extends JpaRepository<SalesPerson, Long>{
	@Query(value="select sum(commission) from sales_person where tier=:tier and user_Id=:userId",nativeQuery=true)
	double findByTierAndUserId(String tier, String userId);

}
