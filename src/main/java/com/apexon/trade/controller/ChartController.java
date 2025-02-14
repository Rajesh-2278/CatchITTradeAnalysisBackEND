package com.apexon.trade.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apexon.trade.model.ChartInfo;
import com.apexon.trade.model.Company;
import com.apexon.trade.model.Display;
import com.apexon.trade.model.Investor;
import com.apexon.trade.model.NetWorth;
import com.apexon.trade.service.ChartService;
import com.apexon.trade.service.InvestorService;

@RestController
@CrossOrigin("*")
@RequestMapping("/chart")
public class ChartController {

	@Autowired
	private ChartService chartService;

//	@GetMapping("/{id}")
//	public ChartInfo getChartData(@PathVariable Long id) {
//		ChartInfo chartInfo = chartService.findById(id);
//		if (chartInfo == null) {
//			return null;
//		}
//		return chartInfo;
//	}

	// Save chart data (POST)
	@PostMapping
	public ChartInfo saveChartData(@RequestBody ChartInfo chartData) {

		ChartInfo savedChartData = chartService.saveChartData(chartData);
		return savedChartData;
	}

	@PostMapping("/addChartInfoToCompany")
	public ChartInfo addChartInfoToCompany(@RequestParam Long companyId, @RequestParam Long profitId) {
		return chartService.addChartInfoToCompany(companyId, profitId);

	}

	@GetMapping("/{companyId}")
	public ChartInfo getChartinfoByCompanyId(@PathVariable Long companyId) {
		return chartService.getNetWorthByCompanyId(companyId);
	}

	@PutMapping("/{id}")
	public ChartInfo updateChartData(@PathVariable("id") Long id, @RequestBody ChartInfo chartData) {

		return chartService.updateChartData(id, chartData, 0); // Assuming update logic exists in service

	}
}
