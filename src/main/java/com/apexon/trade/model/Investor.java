package com.apexon.trade.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Investor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String email;

	@ManyToMany
	@JsonIgnore
	private List<Company> companies;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Display> displyComapies=new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "investor")
	@JsonIgnore
	List<UserHistory> userHistories=new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	KYCStatus kycStatus=KYCStatus.PENDING;

}
