package com.dev.app.utility;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AccountReport {

	LocalDateTime getDateTime();

	String getFullName();

	Integer getAccountNumber();

	String getAccountType();

	String getTransactionType();

	BigDecimal getOldBalance();

	BigDecimal getValue();

	BigDecimal getNewBalance();

}
