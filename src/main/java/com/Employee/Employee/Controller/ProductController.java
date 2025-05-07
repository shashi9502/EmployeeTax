package com.Employee.Employee.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Employee.Employee.Model.Product;
import com.Employee.Employee.Model.ProductResponse;
import com.Employee.Employee.Model.SalesDTO;
import com.Employee.Employee.Model.SalesResponse;
import com.Employee.Employee.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
private ProductService productService;
@PostMapping("/purchase")
public ProductResponse productService(@RequestBody Product dto) {
	ProductResponse data=productService.productService(dto);
	return data;
}
@PostMapping("/salesPersonProfit")
public SalesResponse salesPersonProfits(@RequestBody SalesDTO dto) {
	SalesResponse data=productService.salesPersonProfits(dto);
	return data;
}

}
