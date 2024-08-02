package com.dev.app.model;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Client extends Person {

	@Column(name = "client_id")
	private String clientId;
	@Column(name = "password")
	private String password;
	@Column(name = "status")
	private Boolean status;
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Account> accounts;

	public Client() {

	}

	public Client(String clientId, String password, Boolean status) {
		this.clientId = clientId;
		this.password = password;
		this.status = status;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(clientId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(clientId, other.clientId);
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + "]";
	}

}