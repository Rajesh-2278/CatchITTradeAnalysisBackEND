package com.apexon.trade.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.apexon.trade.model.ChartInfo;
import com.apexon.trade.model.Company;
import com.apexon.trade.repository.ChartRepository;
import com.apexon.trade.repository.CompanyRepository;

@Service
public class ChartService {

	@Autowired
	ChartRepository chartRepository;

	@Autowired
	CompanyRepository companyRepository;

	public ChartInfo findById(Long id) {
		return chartRepository.findById(id).get();
	}

	public ChartInfo saveChartData(ChartInfo chartData) {
		return chartRepository.save(chartData);
	}

	public ChartInfo getNetWorthByCompanyId(Long companyId) {
		// TODO Auto-generated method stub
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));
		return company.getChartInfo();
	}

	public ChartInfo addChartInfoToCompany(Long companyId, Long profitId) {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));
		ChartInfo chartInfo = chartRepository.findById(profitId)
				.orElseThrow(() -> new RuntimeException("Profit not found with id: " + profitId));
		company.setChartInfo(chartInfo);
		companyRepository.save(company);
		return chartInfo;
	}

	public ChartInfo updateChartData(Long id, ChartInfo chartData,int StockPrice) {
		// TODO Auto-generated method stub
		ChartInfo kk = chartRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Chart not found with id: " + id));
		List<String> ll = kk.getXaxis();
		List<Integer> ly = kk.getYaxis();
		if(ll.size()>10) {
			ll.remove(0);
			ly.remove(0);
		}
		
		LocalTime localTime=LocalTime.now();
		ll.add((localTime+"").substring(0,5));
		ly.add(StockPrice);
//		ll.remove(0);
		System.out.println(ll);
		kk.setXaxis(ll);
		kk.setYaxis(ly);
		return chartRepository.save(kk); // Ensure the ID of the chartData matches the one provided in the URL

	}

}
