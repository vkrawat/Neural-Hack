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


	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<BankEmployeeEntity> loginUser(@RequestBody(required=false) LoginBankEmployee login,HttpServletRequest req)
	{
		System.out.println("in login..\n");
		//System.out.println(empser);
		//System.out.println();
		if(login==null)
			return null;
		System.out.println(login.getPassword()+" pp"+login.getEmail()+" inn");
        BankEmployeeEntity user = empser.findByUserid(login.getEmail());
        if(user == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
        }
        if(!user.getPassword().equals(login.getPassword())){
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
        }
        req.getSession().setAttribute("user",user);
        System.out.println("user created");
       // user=null;
        //new Respons
        return ResponseEntity.ok(user) ;

	 //   	return impl.loginEmployee(loginemp);
	}

	@GetMapping("/logout")
	@CrossOrigin(origins = "http://localhost:4200")
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
	@GetMapping("/users")
	public Optional<BankEmployeeEntity> users() {
		System.out.println("in mapping.....");
		//return empser.findById(id);
		return null;
	}
	
	@DeleteMapping("/delemployee/{id}")
	  public void deleteEmployee(@PathVariable String id) {
	    empser.deleteById(id);
	  }
	
}