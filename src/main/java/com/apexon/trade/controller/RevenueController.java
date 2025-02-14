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
import com.apexon.trade.model.NetWorth;
import com.apexon.trade.model.Profit;
import com.apexon.trade.model.Revenue;
import com.apexon.trade.repository.RevenueRepository;
import com.apexon.trade.service.ChartService;
import com.apexon.trade.service.CompanyService;
import com.apexon.trade.service.InvestorService;
import com.apexon.trade.service.NetWorthService;
import com.apexon.trade.service.ProfitService;
import com.apexon.trade.service.RevenueService;

@RestController
@CrossOrigin("*")
@RequestMapping("/revenue")
public class RevenueController {

	@Autowired
	private RevenueService revenueService;

	@PostMapping
	public Revenue saveNetWorth(@RequestBody Revenue revenue) {

		return revenueService.saveRevenue(revenue);
	}

	@PostMapping("/addRevenueInfoToCompany")
	public Revenue addRevenueInfoToCompany(@RequestParam Long companyId, @RequestParam Long profitId) {
		return revenueService.addRevenueInfoToCompany(companyId, profitId);

	}

	@GetMapping("/{companyId}")
	public Revenue getNetWorthByCompanyId(@PathVariable Long companyId) {
		return revenueService.getRevenueByCompanyId(companyId);
	}

}
