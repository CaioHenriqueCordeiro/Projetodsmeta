package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
@Slf4j
public class SaleService {
	
	@Autowired
	private SaleRepository repository;
	
	public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable) {
		LocalDate today = LocalDate.now(ZoneId.systemDefault());

		System.out.println("Data de hoje: " + today);

		LocalDate min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate); 
		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		
		return repository.findSales(min, max, pageable);
	}

	public Sale sendSale(SaleDto saleDto){

		Sale sale = new Sale();
		sale.setAmount(saleDto.getAmount());
		sale.setVisited(saleDto.getVisited());
		sale.setDate(saleDto.getDate());
		sale.setDeals(saleDto.getDeals());
		sale.setSellerName(saleDto.getSellerName());

		return repository.save(sale);
	}

	public Optional<Sale> findSaleById(Long id){
		return repository.findById(id);
	}

	public Sale updateSaleById(SaleDto saleDto, Long id){
		Sale sale = new Sale();

		sale.setId(id);
		sale.setAmount(saleDto.getAmount());
		sale.setVisited(saleDto.getVisited());
		sale.setDate(saleDto.getDate());
		sale.setDeals(saleDto.getDeals());
		sale.setSellerName(saleDto.getSellerName());


		return repository.save(sale);
	}

	public void deleteSaleById(Long id){
		repository.deleteById(id);
	}
}
