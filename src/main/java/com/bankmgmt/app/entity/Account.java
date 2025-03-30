package com.bankmgmt.app.entity;

public class Account {
    
    private Integer id;
    @NotBlank
    private String accountHolderName;
    @NotNull
    private String accountType;

    @Min(0)
    private Double balance;

    @Email
    @NotBlank
    private String email;

    // Constructors, getters, and setters

    public Account(Integer id, String accountHolderName, String accountType, Double balance, String email) {
        this.id = id;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = balance;
        this.email = email;
    }

    // TODO: Add getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
