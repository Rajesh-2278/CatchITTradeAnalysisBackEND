package com.apexon.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apexon.trade.model.Company;
import com.apexon.trade.model.NetWorth;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

	Company findByNetWorth(NetWorth netWorth);

	Company findByNetWorthId(Long id);

}
