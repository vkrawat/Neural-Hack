package com.chargemanag1.bankmanag1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chargemanag1.bankmanag1.LoginBankEmployee;
import com.chargemanag1.bankmanag1.Repository.BankEmployeeRepository;
import com.chargemanag1.bankmanag1.Entity.BankEmployeeEntity;

@Service
public class BankEmployeeServiceImpl implements BankEmployeeService{

	@Autowired
	private BankEmployeeRepository emp;

	
	@Override
	public ResponseEntity<BankEmployeeEntity> loginEmployee(LoginBankEmployee login) {
		 //public ApiResponse login(LoginDto loginDto) {
		System.out.println(emp);
		        BankEmployeeEntity user = emp.findByUserid(login.getUserid());
		        if(user == null) {

			        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND) ;
		        }
		        if(!user.getPassword().equals(login.getPassword())){
		        	return new ResponseEntity<>(null,HttpStatus.NOT_FOUND) ;
				      
		        }
		       
		        return new ResponseEntity<BankEmployeeEntity>(user,HttpStatus.FOUND) ;

		    }
	}
	
