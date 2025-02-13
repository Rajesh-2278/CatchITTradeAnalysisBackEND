package com.apexon.trade.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
import org.aspectj.bridge.IMessage;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Data
public class Investor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100)
	private String firstname;
	@Column(length = 75)
	private String lastName;
	private String email;
	private String password;
	private String phoneNumber;
	@Column(length = 500)
	private String address;

	private String accountNumber;
	@Column(length = 10)
	private String panNumber;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@Temporal(TemporalType.DATE)
	private Date dateOfAccountopening;
	//Net worth/ Net Value of investor = fundAvailable + investedAmount
	private Long fundsAvailable;
	private Long investedAmount = 0L;

	@Enumerated(EnumType.STRING)
	private KYCStatus kycStatus=KYCStatus.PENDING;

	private Boolean emailNotifications;
	private Boolean smsNotifications;

	@ManyToMany
	@JsonIgnore
	private List<Company> companies;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Display> displyComapies = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Date getDateOfAccountopening() {
		return dateOfAccountopening;
	}

	public void setDateOfAccountopening(Date dateOfAccountopening) {
		this.dateOfAccountopening = dateOfAccountopening;
	}

	public Long getFundsAvailable() {
		return fundsAvailable;
	}

	public void setFundsAvailable(Long fundsAvailable) {
		this.fundsAvailable = fundsAvailable;
	}

	public Long getInvestedAmount() {
		return investedAmount;
	}

	public void setInvestedAmount(Long investedAmount) {
		this.investedAmount = investedAmount;
	}

	public KYCStatus getKycStatus() {
		return kycStatus;
	}

	public void setKycStatus(KYCStatus kycStatus) {
		this.kycStatus = kycStatus;
	}

	public Boolean getEmailNotifications() {
		return emailNotifications;
	}

	public void setEmailNotifications(Boolean emailNotifications) {
		this.emailNotifications = emailNotifications;
	}

	public Boolean getSmsNotifications() {
		return smsNotifications;
	}

	public void setSmsNotifications(Boolean smsNotifications) {
		this.smsNotifications = smsNotifications;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public List<Display> getDisplyComapies() {
		return displyComapies;
	}

	public void setDisplyComapies(List<Display> displyComapies) {
		this.displyComapies = displyComapies;
	}
}

