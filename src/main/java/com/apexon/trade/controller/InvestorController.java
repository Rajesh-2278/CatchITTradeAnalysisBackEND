package com.apexon.trade.controller;

import java.util.List;

import com.apexon.trade.model.KYCStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apexon.trade.model.Company;
import com.apexon.trade.model.Display;
import com.apexon.trade.model.Investor;
import com.apexon.trade.service.InvestorService;

@RestController
@CrossOrigin("*")
@RequestMapping("/investor")
public class InvestorController {

	@Autowired
	InvestorService investorService;

	@PostMapping("/registerInvestor")
	public Investor registerInvestor(@RequestBody Investor investor) {
		return investorService.registerInvestor(investor);

	}

	@GetMapping("/getInvestorById/{id}")
	public Investor getInvestorById(@PathVariable Long id) {
		return investorService.getInvestorById(id);

	}

	@GetMapping("/listAllInvestors")
	public List<Investor> listAllInvestors() {
		return investorService.listAllInvestors();
	}

	@GetMapping("/getCompaniesByInvestorId/{investorId}")
	public List<Display> getCompaniesByInvestorId(@PathVariable Long investorId) {
		return investorService.getCompaniesByInvestorId(investorId);
	}

	@PatchMapping("/{id}")
	public Investor updateInvestor(@PathVariable Long id, @RequestBody Investor investorDetails){
		return investorService.updateInvestor(id, investorDetails);
	}

	@PatchMapping("/{id}/add-funds")
	public ResponseEntity<Investor> addFunds(@PathVariable Long id, @RequestBody Long amountToAdd) {
		// Call the service method to add funds
		Investor updatedInvestor = investorService.addFunds(id, amountToAdd);
		return ResponseEntity.ok(updatedInvestor);
	}

	@PutMapping("/{id}/update-kycstatus")
	public ResponseEntity<Investor> updateKYC(@PathVariable Long id, @RequestBody KYCStatus newKYC){
		Investor updateInvestorKYC = investorService.updateKYC(id, newKYC);
		return ResponseEntity.ok(updateInvestorKYC);
	}

}
