package com.bankmng.chargem.BankService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankmng.chargem.LoginBankEmployee;
import com.bankmng.chargem.BankRepository.BankEmployeeRepository;
import com.bankmng.chargem.Entity.BankEmployeeEntity;

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
	
