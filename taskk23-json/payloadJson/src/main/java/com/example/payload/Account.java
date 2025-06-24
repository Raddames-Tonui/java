package com.example.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    @JsonProperty("account_name")
    private String accountName;

    @JsonProperty("account_label")
    private String accountLabel;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("account_type")
    private String accountType;

    @JsonProperty("account_balance")
    private double accountBalance;

    @JsonProperty("book_balance")
    private double bookBalance;

    @JsonProperty("account_status")
    private String accountStatus;

    @JsonProperty("can_withdraw")
    private String canWithdraw;

    @JsonProperty("max_withdrawable_amount")
    private double maxWithdrawableAmount;

    @JsonProperty("can_deposit")
    private String canDeposit;

    @JsonProperty("max_deposit_amount")
    private long maxDepositAmount;

    @JsonProperty("can_withdraw_ift")
    private String canWithdrawIft;

    @JsonProperty("can_deposit_ift")
    private String canDepositIft;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("product_name")
    private String productName;

    // Getters and setters 
    

    
}
