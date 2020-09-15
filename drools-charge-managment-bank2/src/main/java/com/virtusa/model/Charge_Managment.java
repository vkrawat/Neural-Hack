package com.virtusa.model;
public class Charge_Managment {
	public enum Banking{
		RetailBanking,All;
	}
	private Banking bank_type;
	private int transfer_count;
	private String operation_type;
	private String bankATM;
	private int no_of_days;
	private String account_type;
	private double amount;
	private double tax;
	private int range;
	public Banking getBank_type() {
		return bank_type;
	}
	public void setBank_type(Banking bank_type) {
		this.bank_type = bank_type;
	}
	public int getRange() {
		return range;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getTransfer_count() {
		return transfer_count;
	}
	public void setTransfer_count(int transfer_count) {
		this.transfer_count = transfer_count;
	}
	public String getOperation_type() {
		return operation_type;
	}
	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}
	public String getBankATM() {
		return bankATM;
	}
	public void setBankATM(String bankATM) {
		this.bankATM = bankATM;
	}
	public int getNo_of_days() {
		return no_of_days;
	}
	public void setNo_of_days(int no_of_days) {
		this.no_of_days = no_of_days;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
}