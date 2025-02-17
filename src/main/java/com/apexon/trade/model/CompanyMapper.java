package com.apexon.trade.model;

import java.util.Map;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Component
public class CompanyMapper {

	 public Company convert(Map<?, ?> map) {
	        Company company = new Company();
	        company.setSector(String.valueOf(map.get("Sector")));
	        company.setTickerSymbol(String.valueOf(map.get("Symbol")));
	        company.setName(String.valueOf(map.get("Name")));
	        company.setDescription(String.valueOf(map.get("Description")));
	        company.setAddress(String.valueOf(map.get("Address")));
	        company.setStockExchange(String.valueOf(map.get("Exchange")));
	        company.setIndustry(String.valueOf(map.get("Industry")));
	        company.setMarketCap(Long.valueOf((String) map.get("MarketCapitalization")));
	        company.setStockCount(Long.valueOf((String) map.get("SharesOutstanding")));
	        company.setCountry(String.valueOf(map.get("Country")));
	        company.setOfficialSite(String.valueOf(map.get("OfficialSite")));
	        return company;
	    }

}
