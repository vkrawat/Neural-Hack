package com.chargemanag1.bankmanag1.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chargemanag1.bankmanag1.LoginBankEmployee;
import com.chargemanag1.bankmanag1.Repository.BankEmployeeRepository;
import com.chargemanag1.bankmanag1.Service.BankEmployeeServiceImpl;
import com.chargemanag1.bankmanag1.Entity.BankEmployeeEntity;

@RestController
public class BankController {
	BankEmployeeServiceImpl impl=new BankEmployeeServiceImpl();
	@Autowired
	private BankEmployeeRepository empser;

	@GetMapping("/employees")
	public List<BankEmployeeEntity> getAllEmployees(){
		return empser.findAll();	
	}

	@GetMapping("/userprofile")
	public BankEmployeeEntity getUser(HttpServletRequest req){
		if(req.getSession().getAttribute("user")==null)
			return null;
		else
			return (BankEmployeeEntity)req.getSession().getAttribute("user");
	}

	
	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<BankEmployeeEntity> loginUser(@RequestBody LoginBankEmployee login,HttpServletRequest req)
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
        req.getSession().setAttribute("user",user);
        System.out.println("user created");
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