package com.Employee.Employee.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employee.Employee.Model.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
