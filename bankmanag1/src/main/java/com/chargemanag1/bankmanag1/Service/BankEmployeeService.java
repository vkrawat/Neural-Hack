package com.chargemanag1.bankmanag1.Service;

import org.springframework.http.ResponseEntity;

import com.chargemanag1.bankmanag1.LoginBankEmployee;
import com.chargemanag1.bankmanag1.Entity.BankEmployeeEntity;

public interface BankEmployeeService {
	public ResponseEntity<BankEmployeeEntity> loginEmployee(LoginBankEmployee login);
}
