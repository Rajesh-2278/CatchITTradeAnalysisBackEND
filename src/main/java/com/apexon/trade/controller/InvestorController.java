package com.apexon.trade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apexon.trade.model.Company;
import com.apexon.trade.model.Display;
import com.apexon.trade.model.Investor;
import com.apexon.trade.model.UserHistory;
import com.apexon.trade.service.InvestorService;

@RestController
@CrossOrigin("*")
@RequestMapping("/investor")
public class InvestorController {
	@Autowired
	InvestorService investorService;

	
	 @GetMapping("/getCompaniesByInvestorId/{investorId}")
	    public List<Display> getCompaniesByInvestorId(@PathVariable Long investorId) {
	        return investorService.getCompaniesByInvestorId(investorId);
	    }
	 @GetMapping("/getUserHistoryByInvestorId/{investorId}")
	    public List<UserHistory> getUserHistoryByInvestorId(@PathVariable Long investorId) {
	        return investorService.getUserHistoryByInvestorId(investorId);
	    }
	 
	@PostMapping("/registerInvestor")
	public Investor registerComany(@RequestBody Investor company) {
		return investorService.registerComany(company);

	}

	@GetMapping("/listAllInvestors")
	public List<Investor> listAllCompanies() {
		return investorService.listAllCompanies();

	}

	@GetMapping("/getInvestorById/{id}")
	public Investor getCompanyById(@PathVariable Long id) {
		return investorService.getCompanyById(id);

	}
}
