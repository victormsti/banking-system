package com.example.bankingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_type", schema = "public")
public class AccountType {

	public static AccountType jointAccount = new AccountType(1,"Joint account");
	public static AccountType singleAccount = new AccountType(2,"Single account");
	
	public AccountType() {

	}

	public AccountType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_account_type", updatable = false, nullable = false)
	private int id;

	@Column(name = "type")
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "AccountType [id=" + id + ", type=" + type + "]";
	}
}
