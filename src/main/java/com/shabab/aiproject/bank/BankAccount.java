package com.shabab.aiproject.bank;

import java.security.SecureRandom;

public class BankAccount {

    private String name;
    private Long balance;
    private String accountNumber;

    private String branchName;
    private String mobileNumber;
    private String passportNumber;


    public BankAccount(String name, Long balance, String branchName, String mobileNumber, String passportNumber) {
        this.name = name;
        this.balance = balance;
        SecureRandom secureRandom = new SecureRandom();
        this.accountNumber = String .valueOf(secureRandom.nextInt(100000000 - 1000000) + 1000000);

        this.branchName = branchName;
        this.mobileNumber = mobileNumber;
        this.passportNumber = passportNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }



    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}
