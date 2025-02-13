package com.apexon.trade.service;

import java.util.Date;
import java.util.List;

import com.apexon.trade.model.*;
import jakarta.persistence.Column;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Investor registerInvestor(Investor investor) {
		investor.setDateOfAccountopening(new Date());
		return investorRepository.save(investor);
	}

	public List<Investor> listAllInvestors() {
		// TODO Auto-generated method stub
		return investorRepository.findAll();
	}

	public Investor getInvestorById(Long id) {
		// TODO Auto-generated method stub
		return investorRepository.findById(id).orElseThrow(() -> new RuntimeException("Investor not present"));
	}

	public Investor updateInvestor(Long id, Investor investorDetails){
		Investor existingInvestor = getInvestorById(id);
		//Investor existingInvestor = investorRepository.findByEmail(updatedInv.getEmail()).orElseThrow(() -> new RuntimeException("Investor not present"));
		if (investorDetails.getFirstname() != null){
			existingInvestor.setFirstname(investorDetails.getFirstname());
		}
		if (investorDetails.getLastName() != null) {
			existingInvestor.setLastName(investorDetails.getLastName());
		}
		if (investorDetails.getPhoneNumber() != null) {
			existingInvestor.setEmail(investorDetails.getPhoneNumber());
		}
		if (investorDetails.getPanNumber() != null) {
			existingInvestor.setPanNumber(investorDetails.getPanNumber());
		}
		if (investorDetails.getAccountNumber() != null) {
			existingInvestor.setAccountNumber(investorDetails.getAccountNumber());
		}

		return investorRepository.save(existingInvestor);
	}

	public Investor addFunds(Long id, Long amtAdded){
		Investor existingInvestor = getInvestorById(id);
		existingInvestor.setFundsAvailable(existingInvestor.getFundsAvailable() + amtAdded);
		return investorRepository.save(existingInvestor);
	}

	public Investor updateKYC(Long id, KYCStatus newKYC){
		Investor existingInvestor = getInvestorById(id);
		existingInvestor.setKycStatus(newKYC);
		return investorRepository.save(existingInvestor);
	}

}
