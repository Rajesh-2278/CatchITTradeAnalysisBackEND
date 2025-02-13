package com.apexon.trade.service;

import java.util.List;

import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apexon.trade.model.ChartInfo;
import com.apexon.trade.model.Company;
import com.apexon.trade.model.Display;
import com.apexon.trade.model.Investor;
import com.apexon.trade.repository.ChartRepository;
import com.apexon.trade.repository.CompanyRepository;
import com.apexon.trade.repository.DisplayRepository;
import com.apexon.trade.repository.InvestorRepository;

import jakarta.transaction.Transactional;

@Service
public class NetWorthService {

	@Autowired
	ChartRepository chartRepository;

	public ChartInfo findById(Long id) {
		// TODO Auto-generated method stub
		return chartRepository.findById(id).get();
	}

	public ChartInfo saveChartData(ChartInfo chartData) {
		// TODO Auto-generated method stub
		return chartRepository.save(chartData);
	}



	

}
