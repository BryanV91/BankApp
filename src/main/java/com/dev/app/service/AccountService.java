package com.dev.app.service;

import java.time.LocalDate;
import java.util.List;

import com.dev.app.model.Account;
import com.dev.app.utility.AccountReport;

public interface AccountService {

	public Account save(Account obj);

	public Account edit(Account obj);

	public Account update(Account obj) throws IllegalArgumentException, IllegalAccessException;

	public Boolean delete(Long id);

	public Account findOne(Long id);

	public List<AccountReport> accountsByDateRangeAndClient(LocalDate startDate, LocalDate endDate, Long id);

}