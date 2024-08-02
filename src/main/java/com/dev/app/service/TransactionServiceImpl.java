package com.dev.app.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.app.model.Account;
import com.dev.app.model.Transaction;
import com.dev.app.repository.AccountRepository;
import com.dev.app.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Transaction save(Transaction obj) {
		Optional<Account> tmp = null;
		if (obj.getAccount() != null) {
			tmp = accountRepository.findById(obj.getAccount().getId());
			if (!tmp.isPresent())
				throw new IllegalArgumentException("Account does not exist!");
		} else
			throw new IllegalArgumentException("Account is required!");
		if (obj.getTransactionType() == null)
			throw new IllegalArgumentException("Transaction type is required!");
		if (obj.getValue().compareTo(new BigDecimal(0)) == 0) {
			throw new IllegalArgumentException("Value can not be zero!");
		}
		if (obj.getValue().compareTo(new BigDecimal(0)) < 0) {
			obj.setTransactionType("EGR");
			obj.setValue(obj.getValue().multiply(new BigDecimal(-1)));
		}
		Account acc = tmp.get();
		obj.setOldBalance(acc.getBalance());
		switch (obj.getTransactionType()) {
		case "ING":
			acc.setBalance(acc.getBalance().add(obj.getValue()));
			break;
		case "EGR":
			if (obj.getValue().compareTo(acc.getBalance()) > 0) {
				throw new IllegalArgumentException("Your balance is not sufficient.");
			} else {
				acc.setBalance(acc.getBalance().subtract(obj.getValue()));
			}
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + obj.getTransactionType());
		}
		accountRepository.save(acc);
		obj.setDateTime(LocalDateTime.now());
		obj.setNewBalance(acc.getBalance());
		return transactionRepository.save(obj);
	}

	// Balance is not updated with this operation.
	@Override
	public Transaction edit(Transaction obj) {
		if (obj.getAccount() != null) {
			Optional<Account> tmp = accountRepository.findById(obj.getAccount().getId());
			if (!tmp.isPresent())
				throw new IllegalArgumentException("Account does not exist!");
		} else
			throw new IllegalArgumentException("Account is required!");
		return transactionRepository.save(obj);
	}

	// Balance is not updated with this operation.
	@Override
	public Transaction update(Transaction obj) throws IllegalArgumentException, IllegalAccessException {
		Optional<Transaction> opt = transactionRepository.findById(obj.getId());
		if (!opt.isPresent()) {
			return null;
		}
		Transaction previous = opt.get();
		Class<?> clazz = Transaction.class;
		Field[] originalFields = clazz.getDeclaredFields();
		for (Field field : originalFields) {
			field.setAccessible(true);
			Object value = field.get(obj);
			if (value != null) {
				field.set(previous, value);
			}
			field.setAccessible(false);
		}
		return transactionRepository.save(previous);
	}

	// Balance is not updated with this operation.
	@Override
	public Boolean delete(Long id) {
		Optional<Transaction> opt = transactionRepository.findById(id);
		if (!opt.isPresent()) {
			return null;
		}
		transactionRepository.deleteById(id);
		return true;
	}

	@Override
	public Transaction findOne(Long id) {
		Optional<Transaction> opt = transactionRepository.findById(id);
		if (!opt.isPresent()) {
			return null;
		}
		return opt.get();
	}

}