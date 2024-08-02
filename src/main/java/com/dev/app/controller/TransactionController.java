package com.dev.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.app.model.Transaction;
import com.dev.app.service.TransactionService;

@Controller
@RequestMapping("/movimientos")
public class TransactionController {

	Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/crear")
	@ResponseBody
	public ResponseEntity<Object> save(@RequestBody Transaction obj) {
		try {
			Transaction tmp = transactionService.save(obj);
			return ResponseEntity.ok(tmp);
		} catch (IllegalArgumentException ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/editar")
	@ResponseBody
	public ResponseEntity<Transaction> edit(@RequestBody Transaction obj) {
		Transaction tmp = transactionService.edit(obj);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(tmp);
		}
	}

	@PatchMapping("/actualizar")
	@ResponseBody
	public ResponseEntity<Transaction> update(@RequestBody Transaction obj)
			throws IllegalArgumentException, IllegalAccessException {
		Transaction tmp = transactionService.update(obj);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(tmp);
		}
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean tmp = transactionService.delete(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(tmp);
		}
	}

	@GetMapping("/buscar/{id}")
	@ResponseBody
	public ResponseEntity<Transaction> findOne(@PathVariable Long id) {
		Transaction tmp = transactionService.findOne(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(tmp);
		}
	}

}