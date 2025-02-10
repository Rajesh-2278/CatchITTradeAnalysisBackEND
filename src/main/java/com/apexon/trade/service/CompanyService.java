package com.apexon.trade.service;

import java.util.List;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apexon.trade.model.Company;
import com.apexon.trade.model.Display;
import com.apexon.trade.model.Investor;
import com.apexon.trade.repository.CompanyRepository;
import com.apexon.trade.repository.DisplayRepository;
import com.apexon.trade.repository.InvestorRepository;

import jakarta.transaction.Transactional;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	DisplayRepository displayRepository;

	@Autowired
	InvestorRepository investorRepository;

	public List<Investor> getInvestorsByCompanyId(Long companyId) {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new IllegalArgumentException("Company not found"));

		return List.copyOf(company.getInvestors()); // Return a copy of the set to avoid direct modification
	}

	public Company registerComany(Company company) {
		// TODO Auto-generated method stub
		return companyRepository.save(company);
	}

	public List<Company> listAllCompanies() {
		// TODO Auto-generated method stub
		List<Company> ll = companyRepository.findAll();
		return ll;
	}

	public Company getCompanyById(Long id) {
		// TODO Auto-generated method stub
		return companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not present"));
	}///

	@Transactional
	public void assignStocksToInvestor(Long companyId, Long investorId, int stocksToAssign) {
	    Company company = companyRepository.findById(companyId)
	            .orElseThrow(() -> new IllegalArgumentException("Company not found"));

	    if (company.getStockCount() < stocksToAssign) {
	        throw new IllegalArgumentException("Not enough stocks available in the company.");
	    }

	    // Update the stock count of the company
	    company.setStockCount(company.getStockCount() - stocksToAssign);
	    companyRepository.save(company);

	    // Fetch the investor from the database
	    Investor investor = investorRepository.findById(investorId)
	            .orElseThrow(() -> new IllegalArgumentException("Investor not found"));

	    // Check if the Display exists for this investor and company combination
	    Display display = null;
	    for (Display var : investor.getDisplyComapies()) {
	        if (var.getInvestor().getId().equals(investorId) && var.getCompany().getId().equals(companyId)) {
	            display = var;  // Found the display, update stock count
	            break;
	        }
	    }

	    if (display != null) {
	        // Update stock count of the existing Display
	        display.setStockCount(display.getStockCount() + stocksToAssign);
	        displayRepository.save(display);  // Save the updated display
	    } else {
	        // Create a new Display if not found
	        display = new Display();
	        display.setInvestor(investor);  // Set the investor
	        display.setCompany(company);    // Set the company
	        display.setName(company.getName());
	        display.setStockCount(stocksToAssign);
	        displayRepository.save(display);  // Save the new display
	    }

	    // Add the display to the investor's list of displayed companies if it's new
	    if (!investor.getDisplyComapies().contains(display)) {
	        investor.getDisplyComapies().add(display);
	    }

	    // Add the company to the investor's list of companies if it's not already added
	    if (!investor.getCompanies().contains(company)) {
	        investor.getCompanies().add(company);
	    }

	    // Save the investor (the company with updated stock count is now a part of the investor's companies list)
	    investorRepository.save(investor);
	}

}
