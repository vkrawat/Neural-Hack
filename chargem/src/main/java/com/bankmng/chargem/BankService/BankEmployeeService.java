package com.bankmng.chargem.BankService;

import org.springframework.http.ResponseEntity;

import com.bankmng.chargem.LoginBankEmployee;
import com.bankmng.chargem.Entity.BankEmployeeEntity;

public interface BankEmployeeService {
	public ResponseEntity<BankEmployeeEntity> loginEmployee(LoginBankEmployee login);
}
