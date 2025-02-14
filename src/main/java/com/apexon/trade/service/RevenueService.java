package com.apexon.trade.service;

import java.util.List;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apexon.trade.model.ChartInfo;
import com.apexon.trade.model.Company;
import com.apexon.trade.model.Display;
import com.apexon.trade.model.Investor;
import com.apexon.trade.model.NetWorth;
import com.apexon.trade.model.Profit;
import com.apexon.trade.model.Revenue;
import com.apexon.trade.repository.ChartRepository;
import com.apexon.trade.repository.CompanyRepository;
import com.apexon.trade.repository.DisplayRepository;
import com.apexon.trade.repository.InvestorRepository;
import com.apexon.trade.repository.RevenueRepository;

import jakarta.transaction.Transactional;

@Service
public class RevenueService {

	@Autowired
	RevenueRepository revenueRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	public Revenue saveRevenue(Revenue revenue) {
		// TODO Auto-generated method stub
		return revenueRepository.save(revenue);
	}

	public Revenue addRevenueInfoToCompany(Long companyId, Long profitId) {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));

		// Fetch the Profit by its ID
		Revenue revenue = revenueRepository.findById(profitId)
				.orElseThrow(() -> new RuntimeException("Profit not found with id: " + profitId));
		company.setRevenue(revenue);
		companyRepository.save(company);
		return revenue;
	}

	public Revenue getRevenueByCompanyId(Long companyId) {
		
		Company company = companyRepository.findById(companyId)
	            .orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));
	        return company.getRevenue();
	}


}
