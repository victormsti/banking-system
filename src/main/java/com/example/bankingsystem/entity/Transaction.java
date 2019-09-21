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
@Table(name = "transaction", schema = "public")
public class Transaction {

	public Transaction() {

	}

	public Transaction(BigDecimal value, Date transactionDate, TransactionType transactionType, Account accountOrigin,
			Account accountDestiny) {
		super();
		this.value = value;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.accountOrigin = accountOrigin;
		this.accountDestiny = accountDestiny;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transaction", updatable = false, nullable = false)
	private int id;

	@Column(name = "value")
	private BigDecimal value;

	@Column(name = "transaction_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date transactionDate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_transaction_type")
	private TransactionType transactionType;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_account_origin")
	private Account accountOrigin;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_account_destiny")
	private Account accountDestiny;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Account getAccountOrigin() {
		return accountOrigin;
	}

	public void setAccountOrigin(Account account) {
		this.accountOrigin = account;
	}

	public Account getAccountDestiny() {
		return accountDestiny;
	}

	public void setAccountDestiny(Account accountDestiny) {
		this.accountDestiny = accountDestiny;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", value=" + value + ", transactionDate=" + transactionDate
				+ ", transactionType=" + transactionType + ", accountOrigin=" + accountOrigin + ", accountDestiny="
				+ accountDestiny + "]";
	}

}
