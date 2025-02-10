package com.apexon.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apexon.trade.model.Company;
import com.apexon.trade.model.Display;

@Repository
public interface DisplayRepository extends JpaRepository<Display, Long>{

}
