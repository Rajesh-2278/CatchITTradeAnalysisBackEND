package com.apexon.trade.controller;

import java.util.ArrayList;
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

import com.apexon.trade.model.ChartInfo;
import com.apexon.trade.model.Company;
import com.apexon.trade.model.Display;
import com.apexon.trade.model.Investor;
import com.apexon.trade.model.Profit;
import com.apexon.trade.service.ChartService;
import com.apexon.trade.service.CompanyService;
import com.apexon.trade.service.InvestorService;
import com.apexon.trade.service.ProfitService;

@RestController
@CrossOrigin("*")
@RequestMapping("/profit")
public class ProfitController {

	@Autowired
	private ProfitService profitService;
	
	@Autowired
	private CompanyService companyService;

	// Save chart data (POST)
	@PostMapping
	public Profit saveChartData(@RequestBody Profit profit) {

		Profit profit1 = profitService.saveChartData(profit);
		return profit1;
	}

	@PostMapping("/addProfitInfoToCompany")
	public Profit addProfitInfoToCompany(@RequestParam Long companyId,@RequestParam Long profitId) {
      return profitService.addProfitInfoToCompany(companyId,profitId);
		
	}
	
	@GetMapping("/{companyId}")
    public Profit getProfitByCompanyId(@PathVariable Long companyId) {
        return profitService.getProfitByCompanyId(companyId);
    }

}
