package com.chargemanag1.bankmanag1.Service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.chargemanag1.bankmanag1.LoginBankEmployee;
import com.chargemanag1.bankmanag1.Entity.BankEmployeeEntity;
import com.chargemanag1.bankmanag1.Repository.BankEmployeeRepository;


@Service
public class BankEmployeeServiceImpl implements BankEmployeeService{

	@Autowired
	private BankEmployeeRepository emp;

	
	@Override
	public ResponseEntity<BankEmployeeEntity> loginEmployee(LoginBankEmployee login) {
		 //public ApiResponse login(LoginDto loginDto) {
		System.out.println(emp);
		BankEmployeeEntity user = emp.findByUserid(login.getEmail());
		if(user == null) {
			return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED) ;
		        }
		       if(!user.getPassword().equals(login.getPassword())){
		        	return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED) ;
		        }
		       
		      return new ResponseEntity<BankEmployeeEntity>(user,HttpStatus.FOUND) ;

		    }
	
public ResponseEntity logout(HttpServletRequest req)
{
	System.out.println(" logging out");
if(req.getSession().getAttribute("user")==null)
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
else
{
	System.out.println(" logged out");
		req.getSession().invalidate();
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}


}