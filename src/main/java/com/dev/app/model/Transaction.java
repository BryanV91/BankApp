package com.dev.app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	@Column(name = "transaction_type")
	private String transactionType;
	@Column(name = "value")
	private BigDecimal value;
	@Column(name = "new_balance")
	private BigDecimal newBalance;
	@Column(name = "old_balance")
	private BigDecimal oldBalance;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "account_fk", nullable = false)
	@JsonBackReference
	private Account account;

	public Transaction() {

	}

	public Transaction(long id, LocalDateTime dateTime, String transactionType, BigDecimal value, BigDecimal newBalance,
			BigDecimal oldBalance) {
		this.id = id;
		this.dateTime = dateTime;
		this.transactionType = transactionType;
		this.value = value;
		this.newBalance = newBalance;
		this.oldBalance = oldBalance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(BigDecimal newBalance) {
		this.newBalance = newBalance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BigDecimal getOldBalance() {
		return oldBalance;
	}

	public void setOldBalance(BigDecimal oldBalance) {
		this.oldBalance = oldBalance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", dateTime=" + dateTime + ", transactionType=" + transactionType + ", value="
				+ value + ", newBalance=" + newBalance + ", oldBalance=" + oldBalance + ", account=" + account + "]";
	}

}