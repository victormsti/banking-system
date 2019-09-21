package com.example.bankingsystem.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "account", schema = "public")
public class Account {

	public Account() {

	}

	public Account(BigDecimal accountBalance, BigDecimal interestRate, Date lastAccess, AccountType accountType) {
		super();
		this.accountBalance = accountBalance;
		this.interestRate = interestRate;
		this.lastAccess = lastAccess;
		this.accountType = accountType;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_account", updatable = false, nullable = false)
	private int id;

	@Column(name = "account_balance")
	private BigDecimal accountBalance;

	@Column(name = "interest_rate")
	private BigDecimal interestRate;

	@Column(name = "last_access")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date lastAccess;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_account_type")
	public AccountType accountType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountBalance=" + accountBalance + ", interestRate=" + interestRate
				+ ", lastAccess=" + lastAccess + ", accountType=" + accountType + "]";
	}

}
