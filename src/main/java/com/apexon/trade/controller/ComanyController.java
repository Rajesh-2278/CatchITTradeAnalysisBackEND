package com.apexon.trade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apexon.trade.model.Company;
import com.apexon.trade.model.Investor;
import com.apexon.trade.service.CompanyService;

@RestController
@CrossOrigin("*")
@RequestMapping("/company")
public class ComanyController {
	@Autowired
	CompanyService companyService;

	@PostMapping("/assignStocks")
    public String assignStocksToInvestor(
            @RequestParam Long companyId, 
            @RequestParam Long investorId, 
            @RequestParam int stocksToAssign) {
        
        companyService.assignStocksToInvestor(companyId, investorId, stocksToAssign);
        return "Stocks assigned successfully to the investor!";
    }
	
	@GetMapping("/getInvestorsByCompanyId/{companyId}")
	public List<Investor> getInvestorsByCompanyId(@PathVariable Long companyId) {
		return companyService.getInvestorsByCompanyId(companyId);
	}

	@PostMapping("/registerCompany")
	public Company registerComany(@RequestBody Company company) {
		return companyService.registerComany(company);

	}

	@GetMapping("/listAllCompanies")
	public List<Company> listAllCompanies() {
		return companyService.listAllCompanies();

	}

	@GetMapping("/getCompanyById/{id}")
	public Company getCompanyById(@PathVariable Long id) {
		return companyService.getCompanyById(id);

	}
}
