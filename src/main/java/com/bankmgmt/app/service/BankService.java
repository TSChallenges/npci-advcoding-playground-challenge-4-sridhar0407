package com.bankmgmt.app.service;

import com.bankmgmt.app.entity.Account;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.*;

public class BankService {

	private List<Account> accounts = new ArrayList<>();
	private Integer currentId = 1;

	// TODO: Method to Create a new Account
	public Account createAccount(String accountHolderName, String accountType, String email) {

		try {
			Account.AccountType.valueOf(accountType);
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Invalid account type. Must be SAVINGS or CURRENT.");
		}

		if (accounts.stream().anyMatch(acc -> acc.getEmail().equalsIgnoreCase(email))) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use.");
		}

		Account newAccount = new Account(currentId++, accountHolderName, Account.AccountType.valueOf(accountType), 0.0,
				email);
		accounts.add(newAccount);
		return newAccount;
	}

	// TODO: Method to Get All Accounts
	public List<Account> getAllAccounts() {
		return accounts;
	}

	// TODO: Method to Get Account by ID

	public Optional<Account> getAccountById(int id) {
		return accounts.stream().filter(acc -> acc.getId().equals(id)).findFirst();
	}

	// TODO: Method to Deposit Money to the specified account id

	public Account deposit(int id, double amount) {
		if (amount <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deposit amount must be positive.");
		}

		Account account = getAccountById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found."));
		account.setBalance(account.getBalance() + amount);
		return account;
	}
	// TODO: Method to Withdraw Money from the specified account id

	public Account withdraw(int id, double amount) {
		if (amount <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Withdrawal amount must be positive.");
		}

		Account account = getAccountById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found."));
		if (account.getBalance() < amount) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance.");
		}

		account.setBalance(account.getBalance() - amount);
		return account;
	}
	// TODO: Method to Transfer Money from one account to another

	public void transfer(int fromId, int toId, double amount) {
		if (amount <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transfer amount must be positive.");
		}

		Account sender = getAccountById(fromId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender account not found."));
		Account receiver = getAccountById(toId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiver account not found."));

		if (sender.getBalance() < amount) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance.");
		}

		sender.setBalance(sender.getBalance() - amount);
		receiver.setBalance(receiver.getBalance() + amount);
	}

	// TODO: Method to Delete Account given a account id

	public void deleteAccount(int id) {
		if (!accounts.removeIf(acc -> acc.getId().equals(id))) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found.");
		}
	}
}
