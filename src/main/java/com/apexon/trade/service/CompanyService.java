package com.apexon.trade.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apexon.trade.model.ChartInfo;
import com.apexon.trade.model.Company;
import com.apexon.trade.model.Display;
import com.apexon.trade.model.Investor;
import com.apexon.trade.model.UserHistory;
import com.apexon.trade.repository.ChartRepository;
import com.apexon.trade.repository.CompanyRepository;
import com.apexon.trade.repository.DisplayRepository;
import com.apexon.trade.repository.InvestorRepository;
import com.apexon.trade.repository.UserHistoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	DisplayRepository displayRepository;

	@Autowired
	InvestorRepository investorRepository;

	@Autowired
	ChartService chartService;
	@Autowired
	UserHistoryRepository historyRepository;
	
	@Autowired
	ChartRepository chartRepository;

	public List<Investor> getInvestorsByCompanyId(Long companyId) {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new IllegalArgumentException("Company not found"));

		return List.copyOf(company.getInvestors());
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
		ChartInfo chartInfo=new ChartInfo();
		List<String> x=new ArrayList<>();
		List<Integer> y=new ArrayList<>();
		chartInfo.setXaxis(x);
		chartInfo.setYaxis(y);
		chartRepository.save(chartInfo);
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
				display = var; // Found the display, update stock count
				break;
			}
		}
//History
		UserHistory userHistory = new UserHistory();
		userHistory.setCompanyName(company.getName());
		userHistory.setInvestor(investor);
		userHistory.setDateTime(LocalDateTime.now());
		userHistory.setMoneySpent(stocksToAssign * company.getStockPrice());
		userHistory.setStockCount(stocksToAssign);
		historyRepository.save(userHistory);
		// History

		if (display != null) {
			// Update stock count of the existing Display
			display.setStockCount(display.getStockCount() + stocksToAssign);
			display.setTotalInvestedMoney(display.getStockCount() + stocksToAssign * company.getStockPrice());
			display.setStockPrice(company.getStockPrice());
			displayRepository.save(display); // Save the updated display
		} else {
			// Create a new Display if not found
			display = new Display();
			display.setInvestor(investor); // Set the investor
			display.setCompany(company); // Set the company
			display.setName(company.getName());
			display.setStockCount(stocksToAssign);
			display.setStockPrice(company.getStockPrice());
			display.setTotalInvestedMoney(stocksToAssign * company.getStockPrice());
			displayRepository.save(display); // Save the new display
		}

		// Add the display to the investor's list of displayed companies if it's new
		if (!investor.getDisplyComapies().contains(display)) {
			investor.getDisplyComapies().add(display);
		}

		// Add the company to the investor's list of companies if it's not already added
		if (!investor.getCompanies().contains(company)) {
			investor.getCompanies().add(company);
		}

		investorRepository.save(investor);
	}

	@Transactional
	public void sellStocksFromInvestor(Long companyId, Long investorId, int stocksToSell) {
	    Company company = companyRepository.findById(companyId)
	            .orElseThrow(() -> new IllegalArgumentException("Company not found"));

	    // Fetch the investor from the database
	    Investor investor = investorRepository.findById(investorId)
	            .orElseThrow(() -> new IllegalArgumentException("Investor not found"));

	    // Find the display for this investor and company combination
	    Display display = null;
	    for (Display var : investor.getDisplyComapies()) {
	        if (var.getInvestor().getId().equals(investorId) && var.getCompany().getId().equals(companyId)) {
	            display = var; // Found the display
	            break;
	        }
	    }

	    if (display == null || display.getStockCount() < stocksToSell) {
	        throw new IllegalArgumentException("Not enough stocks available for this investor to sell.");
	    }

	    // Update the stock count of the company
	    company.setStockCount(company.getStockCount() + stocksToSell);  // Company receives the stocks back
	    companyRepository.save(company);

	    // Update the stock count of the display (investor's holdings)
	    display.setStockCount(display.getStockCount() - stocksToSell);
	    display.setTotalInvestedMoney(display.getStockCount() * company.getStockPrice()); // Adjust total invested money
	    displayRepository.save(display);

	    // History for the sale
	    UserHistory userHistory = new UserHistory();
	    userHistory.setCompanyName(company.getName());
	    userHistory.setInvestor(investor);
	    userHistory.setDateTime(LocalDateTime.now());
	    userHistory.setMoneySpent(stocksToSell * company.getStockPrice());  // This is the money the investor will receive from selling
	    userHistory.setStockCount(-stocksToSell);  // Negative since this is a sale
	    historyRepository.save(userHistory);

	    // Remove the display if the investor has no stocks left in the company
	    if (display.getStockCount() == 0) {
	        investor.getDisplyComapies().remove(display);  // Remove the display from investor's list
	        displayRepository.delete(display);  // Optionally, delete the display from the DB
	    }

	    // Save the updated investor details
	    investorRepository.save(investor);
	}

	public Company updateCompany(Long id, Company company) {
		// TODO Auto-generated method stub
		// Retrieve the company by id
		Company existingCompany = companyRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Company not present"));

		if (existingCompany == null) {
			// Handle case when the company doesn't exist (e.g., return an error response)
			throw new RuntimeException("Company with id " + id + " not found");
		}

		chartService.updateChartData(id, null, company.getStockPrice());
		// Update the company's name with the new value from the request body
		existingCompany.setStockPrice(company.getStockPrice());
		return companyRepository.save(existingCompany);
	}

}
