package com.dev.app.service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.app.model.Account;
import com.dev.app.model.Client;
import com.dev.app.repository.AccountRepository;
import com.dev.app.repository.ClientRepository;
import com.dev.app.repository.TransactionRepository;
import com.dev.app.utility.AccountReport;

@Service
public class AccountServiceImpl implements AccountService {

	Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Account save(Account obj) {
		if (obj.getClient() != null) {
			Optional<Client> tmp = clientRepository.findById(obj.getClient().getId());
			if (!tmp.isPresent())
				throw new IllegalArgumentException("Client ID does not exist!");
		} else
			throw new IllegalArgumentException("Client ID is required!");
		Optional<Account> opt = accountRepository.findByAccountNumberAndAccountType(obj.getAccountNumber(),
				obj.getAccountType());
		if (opt.isPresent()) {
			throw new IllegalArgumentException("Account number and account type are already registered!");
		} else {
			return accountRepository.save(obj);
		}
	}

	@Override
	public Account edit(Account obj) {
		if (obj.getClient() != null) {
			Optional<Client> tmp = clientRepository.findById(obj.getClient().getId());
			if (!tmp.isPresent())
				throw new IllegalArgumentException("Client ID does not exist!");
		} else
			throw new IllegalArgumentException("Client ID is required!");
		Optional<Account> opt = accountRepository.findById(obj.getId());
		if (!opt.isPresent()) {
			return null;
		}
		obj.setAccountNumber(opt.get().getAccountNumber()); // Account number is not allowed for changes.
		return accountRepository.save(obj);
	}

	@Override
	public Account update(Account obj) throws IllegalArgumentException, IllegalAccessException {
		Optional<Account> opt = accountRepository.findById(obj.getId());
		if (!opt.isPresent()) {
			return null;
		}
		Account previous = opt.get();
		Class<?> clazz = Account.class;
		Field[] originalFields = clazz.getDeclaredFields();
		for (Field field : originalFields) {
			if (field.getName().equals("accountNumber")) // Account number is not allowed for changes.
				continue;
			if (field.getName().equals("client")) // Account can't be removed from client.
				continue;
			field.setAccessible(true);
			Object value = field.get(obj);
			if (value != null) {
				field.set(previous, value);
			}
			field.setAccessible(false);
		}
		return accountRepository.save(previous);
	}

	@Override
	public Boolean delete(Long id) {
		Optional<Account> opt = accountRepository.findById(id);
		if (!opt.isPresent()) {
			return null;
		}
		Account tmp = opt.get();
		if (tmp.getTransactions() != null) {
			tmp.getTransactions().stream().forEach(tr -> {
				transactionRepository.delete(tr);
			});
		}
		accountRepository.deleteById(id);
		return true;
	}

	@Override
	public Account findOne(Long id) {
		Optional<Account> opt = accountRepository.findById(id);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}

	@Override
	public List<AccountReport> accountsByDateRangeAndClient(LocalDate startDate, LocalDate endDate, Long id) {
		return accountRepository.accountsByDateRangeAndClient(startDate, endDate, id);
	}

}