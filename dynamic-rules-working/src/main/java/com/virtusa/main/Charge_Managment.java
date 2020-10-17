package com.virtusa.main;

import java.io.Serializable;

public class Charge_Managment implements Serializable {
	public String bank_type;
	public int transfer_count;
	public String period;
	public String tax_sym;
	public int acc_no;
	public double amount;
	public double tax;
	public String acc_name;
	public String operation_type;
	public String res;

	public Charge_Managment(){
	}
	public String toString()
	{
		return "bank_type :"+bank_type+"transfer_count: "+transfer_count+
				"period: "+period+"taxsymbol: "+tax_sym+"amount: "+amount
				+"opera_type: "+operation_type;
	}
	
	public Charge_Managment(String bank_type, int transfer_count, String period, String tax_sym, int acc_no,
			double amount, double tax, String acc_name, String operation_type, String res) {
		super();
		this.bank_type = bank_type;
		this.transfer_count = transfer_count;
		this.period = period;
		this.tax_sym = tax_sym;
		this.acc_no = acc_no;
		this.amount = amount;
		this.tax = tax;
		this.acc_name = acc_name;
		this.operation_type = operation_type;
		this.res = res;
	}
	public Charge_Managment(String bank_type,String operation_type,String period,int transfer_count){
		this.bank_type=bank_type;
		this.operation_type=operation_type;
		this.period=period;
		this.transfer_count=transfer_count;
	}
	
	
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
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
	public String getAcc_name() {
		return acc_name;
	}
	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}
	public int getAcc_no() {
		return acc_no;
	}
	public void setAcc_no(int acc_no) {
		this.acc_no = acc_no;
	}
	
	public String getTax_sym() {
		return tax_sym;
	}
	public void setTax_sym(String tax_sym) {
		System.out.println("setter called");
		this.tax_sym = tax_sym;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getOperation_type() {
		return operation_type;
	}
	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public int getTransfer_count() {
		return transfer_count;
	}
	public void setTransfer_count(int transfer_count) {
		this.transfer_count = transfer_count;
	}
}