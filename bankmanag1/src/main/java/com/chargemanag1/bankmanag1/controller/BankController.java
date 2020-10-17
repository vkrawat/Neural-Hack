package com.chargemanag1.bankmanag1.controller;
import com.rules.rulesapi.Program;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.chargemanag1.bankmanag1.JwtRequestFilter;
import com.chargemanag1.bankmanag1.JwtResponse;
import com.chargemanag1.bankmanag1.JwtTokenUtil;
import com.chargemanag1.bankmanag1.LoginBankEmployee;
import com.chargemanag1.bankmanag1.entity.BankEmployeeEntity;
import com.chargemanag1.bankmanag1.entity.Rules;
import com.chargemanag1.bankmanag1.service.BankEmployeeServiceImpl;
import com.chargemanag1.bankmanag1.service.RulesService;
import com.rules.DAO.database;
import com.rules.Entities.ChargeManagment;
import com.rules.Entities.RulesOutput;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BankController {
	
	@Autowired
	private BankEmployeeServiceImpl empser;

	@Autowired
	private RulesService ruler;
	
	JwtRequestFilter filter= new JwtRequestFilter();
	
	@GetMapping("/getrules")
	public ResponseEntity<List<RulesOutput>> getRules()
	{
		try
		{
		Program p=new Program();
		return ResponseEntity.ok(p.getRules());
		}
		catch(Exception e)
		{
			System.out.println("some excpetion occurred...."+e);
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE).build();
		}
	}
	
	@PostMapping("/login")
	//@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<JwtResponse> loginUser(@RequestBody(required=false) LoginBankEmployee login,HttpServletRequest req,
			HttpServletResponse res)throws Exception
	{
	    JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
		System.out.println("in login method..");
		ResponseEntity<BankEmployeeEntity> employee=empser.loginEmployee(login);
		System.out.println(employee.getBody());
		BankEmployeeEntity user= employee.getBody();
		if(user==null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		else
		{
		     JwtTokenUtil jwtToken= new JwtTokenUtil();
		     String token= jwtToken.generateToken(user);
		     return ResponseEntity.ok(new JwtResponse(token,login.getEmail(),user.getRole()));
		}
	}

	
		// done
	@GetMapping("/approve")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity approveRule(@RequestParam String paramname, HttpServletRequest req)
	{
		System.out.println(" approving code ");
		try
		  {
			String role = filter.doFilterInternal(req);
			if(role==null )
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			if( "approver".equals(role)==false)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			long code=Long.valueOf(paramname);
			if(ruler.approveRule(code))
				return ResponseEntity.ok().build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		  }
			catch (Exception e) {
				System.out.println("exception: "+e.getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	}

	// done
	@DeleteMapping("/reject")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity rejectRule(@RequestParam String paramname,HttpServletRequest req)
	{
		System.out.println(" reject code ");
		try
		  {
			String role = filter.doFilterInternal(req);
			if(role==null )
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			if( "approver".equals(role)==false)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			long code=Long.valueOf(paramname);
			if(ruler.rejectRule(code))
				return ResponseEntity.ok().build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		  }
			catch (Exception e) {
				System.out.println("exception: "+e.getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	}
	
	// done
	@DeleteMapping("/delete")
	@CrossOrigin(origins = "http://localhost:4200")
	  public ResponseEntity deleteRule(@RequestParam String paramname,HttpServletRequest req) 
	{
		// chk for session
		System.out.println(" calling method to delete rule..");
		try
		  {
			String role = filter.doFilterInternal(req);
			if(role==null )
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			System.out.println(role);
			if( "admin".equals(role)==false)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			long code=Long.valueOf(paramname);
			if(ruler.deleteRule(code))
				return ResponseEntity.ok().build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		  }
			catch (Exception e) {
				System.out.println("exception: "+e.getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	  }
	
	// done
	@PostMapping("/createrule")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Rules> addRule(@RequestBody(required=false) Rules rule,HttpServletRequest req)
	{
		System.out.println(" insert rule..");
		try 
		{
			String role = filter.doFilterInternal(req);
			System.out.println(role);
		if(role==null )
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		if( "creator".equals(role)==false)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		if(ruler.addRule(rule))
			return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		catch (Exception e) {
			System.out.println("exception: "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/updaterule")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Rules> updateRuleData(@RequestBody(required=false) Rules rule,HttpServletRequest req)
	{
		System.out.println(" updating rule..");
		try 
		{
			String role = filter.doFilterInternal(req);
			if(role==null )
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			if( "admin".equals(role)==false)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			if(ruler.updateRuleData(rule))
				return ResponseEntity.ok().build();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		catch (Exception e) {
			System.out.println("exception: "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	// done
	@GetMapping("/list")
	@CrossOrigin(origins = "http://localhost:4200")
	  public ResponseEntity allRules(HttpServletRequest req, HttpServletResponse res) 
	{
		try 
		{
		System.out.println(" rules ");
		String role=filter.doFilterInternal(req);
		if(role==null)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		return ResponseEntity.ok(ruler.allRules(true));
		}
		catch (Exception e) {
			System.out.println("exception: "+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	 }
	
	// done
	
	@PostMapping("/accountfees")
	public ResponseEntity<ChargeManagment> getFees(@RequestBody ChargeManagment account)
	{
		try {
			System.out.println(account);
		Program p=new Program();
		account=p.getFees(account);
		if(account==null)
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE).build();
		return ResponseEntity.ok(account);
		}
		catch(Exception ex)
		{
			System.out.println(" exception raised.. ");
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE).build();
		}
	}

	
	@GetMapping("/pendinglist")
	@CrossOrigin(origins = "http://localhost:4200")
	  public ResponseEntity pendingRules(HttpServletRequest req) 
	{
		try
		  {
			System.out.println(" pending rules ");
			String role=filter.doFilterInternal(req);
			if(role==null)
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			if(!("creator".equals(role) || "approver".equals(role)))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			return ResponseEntity.ok(ruler.allRules(false));
		  }
			catch (Exception e) {
				System.out.println("exception: "+e);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	  }
	
	
	// done -- if code exists 
	@GetMapping("/update")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Rules> update(@RequestParam String paramname, HttpServletRequest req){
		try
		  {
			String role = filter.doFilterInternal(req);
			System.out.println(role);
			if(role==null )
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			if( "admin".equals(role)==false) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();}
			long code=Long.valueOf(paramname);
			Rules rule=ruler.updateCodeExists(code);
			if(rule==null)
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			return ResponseEntity.ok(rule); 
		  }
			catch (Exception e) {
				System.out.println("exception: "+e.getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	}

}