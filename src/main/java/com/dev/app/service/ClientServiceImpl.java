package com.dev.app.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.app.model.Client;
import com.dev.app.repository.AccountRepository;
import com.dev.app.repository.ClientRepository;
import com.dev.app.repository.TransactionRepository;

@Service
public class ClientServiceImpl implements ClientService {

	Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Client save(Client client) {
		Optional<Client> opt = clientRepository.findByClientId(client.getClientId());
		if (opt.isPresent()) {
			throw new IllegalArgumentException("Client ID is already registered!");
		} else {
			return clientRepository.save(client);
		}
	}

	@Override
	public Client edit(Client client) {
		Optional<Client> opt = clientRepository.findById(client.getId());
		if (!opt.isPresent()) {
			return null;
		}
		client.setClientId(opt.get().getClientId()); // Client ID is not allowed for changes.
		return clientRepository.save(client);
	}

	@Override
	public Client update(Client client) throws IllegalArgumentException, IllegalAccessException {
		Optional<Client> opt = clientRepository.findById(client.getId());
		if (!opt.isPresent()) {
			return null;
		}
		Client previous = opt.get();
		Class<?> clazz = Client.class;
		Field[] originalFields = clazz.getDeclaredFields();
		Field[] superClazzFields = clazz.getSuperclass().getDeclaredFields();
		List<Field> fieldList = new ArrayList<>();
		Collections.addAll(fieldList, originalFields);
		Collections.addAll(fieldList, superClazzFields);
		for (Field field : fieldList) {
			if (field.getName().equals("clientId")) // Client ID is not allowed for changes.
				continue;
			field.setAccessible(true);
			Object value = field.get(client);
			if (value != null) {
				field.set(previous, value);
			}
			field.setAccessible(false);
		}
		return clientRepository.save(previous);
	}

	@Override
	public Boolean delete(Long id) {
		Optional<Client> opt = clientRepository.findById(id);
		if (!opt.isPresent()) {
			return null;
		}
		Client tmp = opt.get();
		if (tmp.getAccounts() != null) {
			tmp.getAccounts().stream().forEach(acc -> {
				if (acc.getTransactions() != null) {
					acc.getTransactions().stream().forEach(tr -> {
						transactionRepository.delete(tr);
					});
				}
				accountRepository.delete(acc);
			});
		}
		clientRepository.deleteById(id);
		return true;
	}

	@Override
	public Client findOne(Long id) {
		Optional<Client> opt = clientRepository.findById(id);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}

}