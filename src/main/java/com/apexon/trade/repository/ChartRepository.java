package com.apexon.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apexon.trade.model.ChartInfo;
import com.apexon.trade.model.Company;

@Repository
public interface ChartRepository extends JpaRepository<ChartInfo, Long>{

}
