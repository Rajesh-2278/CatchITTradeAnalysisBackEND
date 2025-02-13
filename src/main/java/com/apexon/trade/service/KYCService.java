package com.apexon.trade.service;


import com.apexon.trade.model.Investor;
import com.apexon.trade.model.KYCStatus;
import com.apexon.trade.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KYCService {
    @Autowired
    InvestorRepository investorRepo;

    public Investor updateKyc(Long id, KYCStatus kycStatus){
        Optional<Investor> investorOptional=investorRepo.findById(id);
        if(investorOptional.isPresent())
        {
            Investor actualInvestor=investorOptional.get();
            actualInvestor.setKycStatus(kycStatus);
            return  investorRepo.save(actualInvestor);
        }
        else {
            throw new RuntimeException("Investor doesn't exist with "+id);
        }
    }
}