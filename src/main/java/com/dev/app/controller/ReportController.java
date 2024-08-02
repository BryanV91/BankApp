package com.dev.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.app.service.AccountService;
import com.dev.app.utility.AccountReport;

@Controller
@RequestMapping("/reportes")
public class ReportController {

	Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private AccountService accountService;

	@GetMapping("/cuentasPorCliente/{startDate}/{endDate}/{id}")
	@ResponseBody
	public ResponseEntity<List<AccountReport>> accountsByDateRangeAndClient(@PathVariable LocalDate startDate,
			@PathVariable LocalDate endDate, @PathVariable Long id) {
		try {
			List<AccountReport> tmp = accountService.accountsByDateRangeAndClient(startDate, endDate, id);
			return ResponseEntity.ok(tmp);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}