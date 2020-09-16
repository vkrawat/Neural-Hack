package com.bankmng.chargem.BankController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankmng.chargem.LoginBankEmployee;
import com.bankmng.chargem.BankRepository.BankEmployeeRepository;
import com.bankmng.chargem.BankService.BankEmployeeServiceImpl;
import com.bankmng.chargem.Entity.BankEmployeeEntity;

@RestController
public class BankController {
	BankEmployeeServiceImpl impl=new BankEmployeeServiceImpl();
	@Autowired
	private BankEmployeeRepository empser;

	@GetMapping("/employees")
	public List<BankEmployeeEntity> getAllEmployees(){
		return empser.findAll();	
	}
	@PostMapping("/login")
	public ResponseEntity<BankEmployeeEntity> login(@RequestBody LoginBankEmployee login)
	{
		System.out.println("in login..\n");
		System.out.println(empser);
		System.out.println(login.getPassword()+" "+login.getUserid());
        BankEmployeeEntity user = empser.findByUserid(login.getUserid());
        if(user == null) {

	        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND) ;
        }
        if(!user.getPassword().equals(login.getPassword())){
        	return new ResponseEntity<>(null,HttpStatus.NOT_FOUND) ;
		      
        }
       
        return new ResponseEntity<BankEmployeeEntity>(user,HttpStatus.FOUND) ;


	 //   	return impl.loginEmployee(loginemp);
	}
	@PostMapping("/saveEmployee")
	public BankEmployeeEntity saveEmployee(@RequestBody BankEmployeeEntity newEmployee) 
	{
		empser.save(newEmployee);
	//	BankEmployeeEntity employee=new BankEmployeeEntity();
		return new BankEmployeeEntity();
	}
	
	@GetMapping("/employee/{id}")
	public Optional<BankEmployeeEntity> getEmployee(@PathVariable String id) {
		return empser.findById(id);
	}
	
	@DeleteMapping("/delemployee/{id}")
	  public void deleteEmployee(@PathVariable String id) {
	    empser.deleteById(id);
	  }
	
}