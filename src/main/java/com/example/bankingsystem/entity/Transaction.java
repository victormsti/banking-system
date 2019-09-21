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

@Entity
@Table(name = "transaction", schema = "public")
public class Transaction {

	public Transaction() {

	}

	public Transaction(BigDecimal value, Date transactionDate, TransactionType transactionType, Account account) {
		super();
		this.value = value;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.account = account;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transaction", updatable = false, nullable = false)
	private int id;

	@Column(name = "value")
	private BigDecimal value;

	@Column(name = "transaction_date")
	private Date transactionDate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_transaction_type")
	private TransactionType transactionType;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_account")
	private Account account;

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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", value=" + value + ", transactionDate=" + transactionDate
				+ ", transactionType=" + transactionType + ", account=" + account + "]";
	}

}
