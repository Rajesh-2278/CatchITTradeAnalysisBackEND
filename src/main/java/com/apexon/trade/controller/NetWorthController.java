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
import com.apexon.trade.service.ChartService;
import com.apexon.trade.service.CompanyService;
import com.apexon.trade.service.InvestorService;
import com.apexon.trade.service.NetWorthService;
import com.apexon.trade.service.ProfitService;

@RestController
@CrossOrigin("*")
@RequestMapping("/netWorth")
public class NetWorthController {

	@Autowired
	private NetWorthService netWorthService;

	@PostMapping
	public NetWorth saveNetWorth(@RequestBody NetWorth netWorth) {

		return netWorthService.saveNetWorth(netWorth);
		 
	}

	@PostMapping("/addNetWorthInfoToCompany")
	public NetWorth addProfitInfoToCompany(@RequestParam Long companyId,@RequestParam Long profitId) {
      return netWorthService.addNetWorthInfoToCompany(companyId,profitId);
		
	}
	
	@GetMapping("/{companyId}")
    public NetWorth getNetWorthByCompanyId(@PathVariable Long companyId) {
        return netWorthService.getNetWorthByCompanyId(companyId);
    }

}
