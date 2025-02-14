package com.apexon.trade.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	private int stockCount;

	@ManyToMany(mappedBy = "companies", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Investor> investors;

	@OneToOne
	private Profit profit;

	@OneToOne(cascade = CascadeType.ALL)
	private NetWorth netWorth;

	@OneToOne
	private Revenue revenue;
	
	@OneToOne
	private ChartInfo chartInfo;

}
