package com.bankmgmt.app.rest;

import com.bankmgmt.app.entity.Account;
import com.bankmgmt.app.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: Make this class a Rest Controller
public class BankController {

	// TODO Autowired the BankService class
	private final BankService bankService;

	public BankController(BankService bankService) {
		this.bankService = bankService;
	}

	// TODO: API to Create a new account
	@PostMapping
	public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
		return new ResponseEntity<>(
				bankService.createAccount(account.getAccountHolderName(), account.getAccountType(), account.getEmail()),
				HttpStatus.CREATED);
	}

	// TODO: API to Get all accounts
	@GetMapping
	public List<Account> getAllAccounts() {
		return bankService.getAllAccounts();
	}

	// TODO: API to Get an account by ID

	@GetMapping("/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable int id) {
		return bankService.getAccountById(id).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// TODO: API to Deposit money
	@PostMapping("/{id}/deposit")
	public ResponseEntity<Account> deposit(@PathVariable int id, @RequestParam double amount) {
		return ResponseEntity.ok(bankService.deposit(id, amount));
	}

	// TODO: API to Withdraw money

	@PostMapping("/{id}/withdraw")
	public ResponseEntity<Account> withdraw(@PathVariable int id, @RequestParam double amount) {
		return ResponseEntity.ok(bankService.withdraw(id, amount));
	}

	// TODO: API to Transfer money between accounts
	@PostMapping("/transfer")
	public ResponseEntity<String> transfer(@RequestParam int fromId, @RequestParam int toId,
			@RequestParam double amount) {
		bankService.transfer(fromId, toId, amount);
		return ResponseEntity.ok("Transfer successful");
	}

	// TODO: API to Delete an account
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable int id) {
		bankService.deleteAccount(id);
		return ResponseEntity.noContent().build();
	}

}
