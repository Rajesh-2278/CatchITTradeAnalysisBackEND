package com.apexon.trade.controller;

import com.apexon.trade.model.Investor;
import com.apexon.trade.model.KYCStatus;
import com.apexon.trade.service.KYCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kyc")
public class KYCController {

    @Autowired
    KYCService kycService;

    @PutMapping("/validate/{id}")
    public ResponseEntity<Investor> validateKYC(@PathVariable Long id,
                                                @RequestParam KYCStatus kycStatus){
        Investor updatedInvestor= kycService.updateKyc(id,kycStatus);
        return ResponseEntity.ok(updatedInvestor);
    }
}
