package com.apexon.trade.service;

import java.util.List;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apexon.trade.model.ChartInfo;
import com.apexon.trade.model.Company;
import com.apexon.trade.model.Display;
import com.apexon.trade.model.Investor;
import com.apexon.trade.model.Profit;
import com.apexon.trade.repository.ChartRepository;
import com.apexon.trade.repository.CompanyRepository;
import com.apexon.trade.repository.DisplayRepository;
import com.apexon.trade.repository.InvestorRepository;
import com.apexon.trade.repository.ProfitRepository;

import jakarta.transaction.Transactional;

@Service
public class ProfitService {

	@Autowired
	ProfitRepository profitRepository;

	@Autowired
	CompanyRepository companyRepository;

	public Profit findById(Long id) {
		// TODO Auto-generated method stub
		return profitRepository.findById(id).get();
	}

	public Profit saveChartData(Profit profit) {
		// TODO Auto-generated method stub
		return profitRepository.save(profit);
	}

	public Profit addProfitInfoToCompany(Long companyId, Long profitId) {
		// TODO Auto-generated method stub
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));

		// Fetch the Profit by its ID
		Profit profit = profitRepository.findById(profitId)
				.orElseThrow(() -> new RuntimeException("Profit not found with id: " + profitId));

		// Associate the Profit with the Company
		company.setProfit(profit);

		// Save the updated Company
		companyRepository.save(company);

		// Return the Profit object (or the Company object if you prefer)
		return profit;
	}

	public Profit getProfitByCompanyId(Long companyId) {
		// TODO Auto-generated method stub
		Company company = companyRepository.findById(companyId)
	            .orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));
	        return company.getProfit();
	}

}
