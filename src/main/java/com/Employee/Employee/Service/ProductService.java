 package com.Employee.Employee.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.Employee.Model.Product;
import com.Employee.Employee.Model.ProductResponse;
import com.Employee.Employee.Model.SalesDTO;
import com.Employee.Employee.Model.SalesPerson;
import com.Employee.Employee.Model.SalesResponse;
import com.Employee.Employee.Repository.ProductRepository;
import com.Employee.Employee.Repository.SalesPersonRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
   @Autowired
   private SalesPersonRepository salesPersonRepository;
	public ProductResponse productService(Product dto) {
		ProductResponse output=new ProductResponse();
		double afrPrice = 0;
		if (dto.getDiscount() > 0 && dto.getQuantity() < 10) {

			double discountAmount = (dto.getPrice() * dto.getDiscount()) / 100;
			afrPrice = (dto.getPrice() + discountAmount);
			dto.setFinalPrice(dto.getFlatdiscount() > afrPrice ? dto.getFlatdiscount() : afrPrice);
		}
		if (dto.getQuantity() >= 10 && dto.getDiscount() == 0) {
			// double afrPrice=(dto.getPrice()*dto.getQuantity())+dto.getPrice()*0.05;
			afrPrice = dto.getPrice() + dto.getPrice() * 0.05;
			dto.setFinalPrice(dto.getFlatdiscount() > afrPrice ? dto.getFlatdiscount() : afrPrice);
		}
		if (dto.getDiscount() > 0 && dto.getQuantity() >= 10) {
			double discountAmount = (dto.getPrice() * dto.getDiscount()) / 100;
			double offer = dto.getPrice() + dto.getPrice() * 0.05;
			if (discountAmount > offer) {
				dto.setFinalPrice(dto.getFlatdiscount() > discountAmount ? dto.getFlatdiscount() : discountAmount);
			} else {
				dto.setFinalPrice(dto.getFlatdiscount() > offer ? dto.getFlatdiscount() : offer);
			}
		}
		output.setFinalPrice(dto.getFinalPrice()*dto.getQuantity());
		output.setSalesTax(dto.getFinalPrice()*0.05*dto.getQuantity());
		productRepository.save(dto);
		return output;
	}

	public SalesResponse salesPersonProfits(SalesDTO dto) {
		String tier=null;
		SalesPerson sale=new SalesPerson();
		sale.setUserId(dto.getUserId());
		if(dto.getPrice()<=5000) {
			sale.setTier("Tier1");
			tier="Tier1";
			sale.setCommission(dto.getPrice()*0.05);
		}
		else if(dto.getPrice()>5000 && dto.getPrice()<=10000) {
			sale.setTier("Tier2");
			tier="Tier2";
			double firstcomm=5000*0.1;
			double aftercomm=(dto.getPrice()-5000)*0.15;
			sale.setCommission(firstcomm+aftercomm);
		}
		else{
			sale.setTier("Tier3");
			tier="Tier3";
			double firstcomm=(5000*0.1)+10000*0.15;
			double aftercomm=(dto.getPrice()-10000)*0.2;
			sale.setCommission(firstcomm+aftercomm);
		}
		salesPersonRepository.save(sale);
		double data=salesPersonRepository.findByTierAndUserId(tier,dto.getUserId());
		SalesResponse res=new SalesResponse();
		res.setEarnAmount(data);
		res.setTier(tier);
		return res;
	}

	public double salesPersonEranAmount(String tier, String userId) {
		double data=salesPersonRepository.findByTierAndUserId(tier,userId);
		return data;
	}

}
