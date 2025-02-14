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
import com.apexon.trade.repository.ChartRepository;
import com.apexon.trade.repository.CompanyRepository;
import com.apexon.trade.repository.DisplayRepository;
import com.apexon.trade.repository.InvestorRepository;
import com.apexon.trade.repository.NetWorthRepository;

import jakarta.transaction.Transactional;

@Service
public class NetWorthService {

	@Autowired
	NetWorthRepository netWorthRepository;

	@Autowired
	CompanyRepository companyRepository;

	public NetWorth saveNetWorth(NetWorth netWorth) {
		// TODO Auto-generated method stub
		return netWorthRepository.save(netWorth);
	}

	@Transactional
	public NetWorth addNetWorthInfoToCompany(Long companyId, Long profitId) {

		Company company = companyRepository.findById(companyId)
	            .orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));

	    // Fetch the Profit (NetWorth) by its ID
	    NetWorth networth = netWorthRepository.findById(profitId)
	            .orElseThrow(() -> new RuntimeException("Profit not found with id: " + profitId));

	    // Check if the company already has a NetWorth associated with it
	    if (company.getNetWorth() != null && company.getNetWorth().getId().equals(networth.getId())) {
	        // No need to set the NetWorth again if it already exists
	        return networth;
	    }

	    // Check if the NetWorth is already associated with another company
	    Company existingCompany = companyRepository.findByNetWorthId(networth.getId());
	    if (existingCompany != null) {
	        throw new RuntimeException("NetWorth is already associated with another company.");
	    }

	    // Assign the NetWorth to the company
	    company.setNetWorth(networth);
	    companyRepository.save(company);

	    return networth;
	}

	public NetWorth getNetWorthByCompanyId(Long companyId) {

		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));
		return company.getNetWorth();
	}

}
