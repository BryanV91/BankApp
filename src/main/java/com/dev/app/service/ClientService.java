package com.dev.app.service;

import com.dev.app.model.Client;

public interface ClientService {

	public Client save(Client obj);

	public Client edit(Client obj);

	public Client update(Client obj) throws IllegalArgumentException, IllegalAccessException;

	public Boolean delete(Long id);

	public Client findOne(Long id);

}