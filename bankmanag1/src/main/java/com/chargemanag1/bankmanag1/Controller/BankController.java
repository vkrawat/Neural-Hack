package com.chargemanag1.bankmanag1.Controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chargemanag1.bankmanag1.LoginBankEmployee;
import com.chargemanag1.bankmanag1.Entity.BankEmployeeEntity;
import com.chargemanag1.bankmanag1.Repository.BankEmployeeRepository;
import com.chargemanag1.bankmanag1.Service.BankEmployeeServiceImpl;

@RestController
public class BankController {
	BankEmployeeServiceImpl impl=new BankEmployeeServiceImpl();
	@Autowired
	private BankEmployeeRepository empser;

	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<BankEmployeeEntity> loginUser(@RequestBody(required=false) LoginBankEmployee login,HttpServletRequest req)
	{
		System.out.println("in login method..");
		System.out.println(login.getPassword()+" "+login.getEmail()+" ");
        BankEmployeeEntity user = empser.findByUserid(login.getEmail());
        if(user == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
        }
        if(!user.getPassword().equals(login.getPassword())){
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
        }
        req.getSession().setAttribute("user",user);
        HttpSession ssn=req.getSession();
        
        //System.out.println("user created");
        System.out.println("user--"+user.getRole());
        return ResponseEntity.ok(user) ;
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

	
	@GetMapping("/approve")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity approveRule(@RequestParam String hd,HttpServletRequest req){
		System.out.println(" approveing ");
		BankEmployeeEntity user= (BankEmployeeEntity)req.getSession().getAttribute("user");
		System.out.println("user "+user);
		// check for logged in / valid user
		if(user==null || !user.getRole().equalsIgnoreCase("approver"))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		//valid
		// add to rule engine
		// return list of all rules
		System.out.println(" empty ");
		return 	ResponseEntity.ok().build();
	}

	
	@DeleteMapping("/reject")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity rejectRule(@RequestParam String hd,HttpServletRequest req){
		System.out.println(" reject ");
		BankEmployeeEntity user= (BankEmployeeEntity)req.getSession().getAttribute("user");
		System.out.println("user"+user);
		// check for logged in / valid user
		if(user==null || !user.getRole().equalsIgnoreCase("approver"))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		//valid
		// remove from temporary db
		// return list of all pending rules 
		return 	ResponseEntity.of(null);
	}
	
	@DeleteMapping("/delete")
	@CrossOrigin(origins = "http://localhost:4200")
	  public ResponseEntity deleteRule(@RequestParam String hd,HttpServletRequest req) {
		System.out.println(" in delete ");
		if(req.getSession().getAttribute("user")==null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    return ResponseEntity.status(HttpStatus.OK).build();
	   
	  }
	
	
	@GetMapping("/update")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<BankEmployeeEntity> update(@RequestParam String hd){
		System.out.println(" updated called ");
		return empser.findAll();	
	}

	@GetMapping("/employees")
	public List<BankEmployeeEntity> getAllEmployees(){
		return empser.findAll();	
	}

	@GetMapping("/userprofile")
	public ResponseEntity getUser(HttpServletRequest req){
		if(req.getSession().getAttribute("user")==null)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		else
			return ResponseEntity.ok((BankEmployeeEntity)req.getSession().getAttribute("user"));
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

	@GetMapping("/list")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity loginList(HttpServletRequest req)
	{
        System.out.println("lists ");
        List<BankEmployeeEntity> list= empser.findAll();
        return ResponseEntity.ok(list);
	}
}