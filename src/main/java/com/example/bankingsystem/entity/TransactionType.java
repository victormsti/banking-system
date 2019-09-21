package com.example.bankingsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_type", schema = "public")
public class TransactionType {

	public static TransactionType checking = new TransactionType(1,"Checking");
	public static TransactionType saving = new TransactionType(1,"Saving");
	public static TransactionType transfer = new TransactionType(1,"Transfer");
	public static TransactionType withdraw = new TransactionType(1,"Withdraw");
	
	public TransactionType() {

	}

	public TransactionType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transaction_type", updatable = false, nullable = false)
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
		return "TransactionType [id=" + id + ", type=" + type + "]";
	}

}
