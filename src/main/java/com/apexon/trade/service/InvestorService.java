package com.apexon.trade.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apexon.trade.model.Company;
import com.apexon.trade.model.Display;
import com.apexon.trade.model.Investor;
import com.apexon.trade.model.KYCStatus;
import com.apexon.trade.model.UserHistory;
import com.apexon.trade.model.Investor;
import com.apexon.trade.repository.CompanyRepository;
import com.apexon.trade.repository.InvestorRepository;

@Service
public class InvestorService {

	@Autowired
	InvestorRepository investorRepository;

	public List<Display> getCompaniesByInvestorId(Long investorId) {

		Investor investor = investorRepository.findById(investorId)
				.orElseThrow(() -> new IllegalArgumentException("Investor not found"));
		return List.copyOf(investor.getDisplyComapies());
	}

	public Investor registerComany(Investor company) {

		return investorRepository.save(company);
	}

	public List<Investor> listAllCompanies() {
		// TODO Auto-generated method stub
		return investorRepository.findAll();
	}

	public Investor getCompanyById(Long id) {
		// TODO Auto-generated method stub
		return investorRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not present"));
	}

	public List<UserHistory> getUserHistoryByInvestorId(Long investorId) {
		// TODO Auto-generated method stub
		Investor investor = investorRepository.findById(investorId)
				.orElseThrow(() -> new IllegalArgumentException("Investor not found"));
		return List.copyOf(investor.getUserHistories());
	}

	public List<Investor> getPendingInvestors()
	{
 
		return investorRepository.findAll().stream()
				.filter(investor -> KYCStatus.PENDING.equals(investor.getKycStatus()))
			.collect(Collectors.toList());
	}
	
	public Investor updateInvestor(Long id, Investor investorDetails){
		//Investor existingInvestor = getInvestorById(id);
		Investor existingInvestor = investorRepository.findById(id).orElseThrow(() -> new RuntimeException("Investor not present"));
		if (investorDetails.getFirstName() != null){
			existingInvestor.setFirstName(investorDetails.getFirstName());
		}
		if (investorDetails.getLastName() != null) {
			existingInvestor.setLastName(investorDetails.getLastName());
		}
		if (investorDetails.getPhoneNumber() != null) {
			existingInvestor.setPhoneNumber(investorDetails.getPhoneNumber());
		}
		if (investorDetails.getPanNumber() != null) {
			existingInvestor.setPanNumber(investorDetails.getPanNumber());
		}
		if (investorDetails.getAccountNumber() != null) {
			existingInvestor.setAccountNumber(investorDetails.getAccountNumber());
		}
		if (investorDetails.getAddress() != null) {
			existingInvestor.setAddress(investorDetails.getAddress());
		}
		return investorRepository.save(existingInvestor);
	}

	public Investor addFunds(Long id, Long amtAdded){
		Investor existingInvestor = investorRepository.findById(id).orElseThrow(() -> new RuntimeException("Investor not present"));
		existingInvestor.setMyFunds(existingInvestor.getMyFunds() + amtAdded);
		return investorRepository.save(existingInvestor);
	}
}
