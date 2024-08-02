package com.dev.app.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.app.model.Account;
import com.dev.app.utility.AccountReport;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByAccountNumberAndAccountType(Integer accountNumber, String accountType);

	@Query(value = """
			SELECT t.date_time, c.full_name, a.account_number, a.account_type, t.transaction_type, t.old_balance, t.value, t.new_balance
			FROM transaction t
			join account a on t.account_fk = a.id
			join client c on c.id = a.client_fk
			where cast(t.date_time as date) between ?1 and ?2 and c.id = ?3 order by t.date_time
						""", nativeQuery = true)
	List<AccountReport> accountsByDateRangeAndClient(LocalDate startDate, LocalDate endDate, Long id);

}
