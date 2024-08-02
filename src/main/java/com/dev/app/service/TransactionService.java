package com.dev.app.service;

import com.dev.app.model.Transaction;

public interface TransactionService {

	public Transaction save(Transaction obj);

	public Transaction edit(Transaction obj);

	public Transaction update(Transaction obj) throws IllegalArgumentException, IllegalAccessException;

	public Boolean delete(Long id);

	public Transaction findOne(Long id);

}