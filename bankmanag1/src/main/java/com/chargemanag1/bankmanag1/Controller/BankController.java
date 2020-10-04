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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chargemanag1.bankmanag1.Entity.BankEmployeeEntity;
import com.chargemanag1.bankmanag1.Entity.Rules;
import com.chargemanag1.bankmanag1.RuleEngine.Program;
import com.chargemanag1.bankmanag1.Service.BankEmployeeServiceImpl;
import com.chargemanag1.bankmanag1.Service.RulesService;

@RestController
@CrossOrigin
public class BankController {
	//BankEmployeeServiceImpl impl=new BankEmployeeServiceImpl();
	@Autowired
	private BankEmployeeServiceImpl empser;

	@Autowired
	private RulesService ruler;

	//@Autowired
	//private AuthenticationManager authenticationManager;

	//@Autowired
	//private JwtTokenUtil jwtTokenUtil;
/*

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			System.out.println("disbled ???"+e.getMessage());
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			System.out.println("invalid +e/"+e.getMessage());
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<JwtResponse> loginUser(@RequestBody(required=false) LoginBankEmployee login,HttpServletRequest req,
			HttpServletResponse res)throws Exception
	{
		System.out.println("in login method..");
		authenticate(login.getEmail(), login.getPassword());
		System.out.println();
		final UserDetails userDetails = empser.loadUserByUsername(login.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token,userDetails.getPassword(),login.getEmail()));
        //return response;
	}
*/
	@GetMapping("/api/getcharge")
	@CrossOrigin(origins = "http://localhost:4200")
	public void charge() throws Exception
	{
		Program p=new Program();
		p.mainProgram();
	}
	
	@GetMapping("/logout")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity logout(HttpServletRequest req)
	{
		System.out.println(" logging out");
		return empser.logout(req);
	}

	// done
	@GetMapping("/approve")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity approveRule(@RequestParam String hd, HttpServletRequest req)
	{
		System.out.println(" approving code ");
		long code=Long.valueOf(hd);
		if(ruler.approveRule(code))
			return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	// done
	@DeleteMapping("/reject")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity rejectRule(@RequestParam String hd,HttpServletRequest req)
	{
		System.out.println(" reject code ");
		long code=Long.valueOf(hd);
		if(ruler.rejectRule(code))
			return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	// done
	@DeleteMapping("/delete")
	@CrossOrigin(origins = "http://localhost:4200")
	  public ResponseEntity deleteRule(@RequestParam String hd,HttpServletRequest req) 
	{
		// chk for session
		System.out.println(" calling method to delete rule..");
		System.out.println(hd);
		long code=Long.valueOf(hd);
		System.out.println(hd);
		if(ruler.deleteRule(code))
			return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	  }
	
	// done
	@PostMapping("/createrule")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Rules> addRule(@RequestBody(required=false) Rules rule,HttpServletRequest req)
	{
		System.out.println(" insert rule..");
		if(ruler.addRule(rule))
			return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	// done
	@GetMapping("/rules")
	@CrossOrigin(origins = "http://localhost:4200")
	  public ResponseEntity allRules(HttpServletRequest req) 
	{
		System.out.println(" rules ");
		return ResponseEntity.ok(ruler.allRules(true));
	 }
	
	// done
	@GetMapping("/pendingrules")
	@CrossOrigin(origins = "http://localhost:4200")
	  public ResponseEntity pendingRules(HttpServletRequest req) 
	{
		  System.out.println(" pending rules ");
		  return ResponseEntity.ok(ruler.allRules(false));
	  }
	
	
	// done -- if code exists 
	@GetMapping("/update")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Rules> update(@RequestParam String hd){
		System.out.println(" updated called ");
		long code=Long.valueOf(hd);
		Rules rule=ruler.updateCodeExists(code);
		if(rule==null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(rule); 
		//return empser.findAll();	
	}

	@GetMapping("/employees")
	public List<BankEmployeeEntity> getAllEmployees(){
		return null;
		//return empser.findAll();	
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
		//empser.save(newEmployee);
	    //	BankEmployeeEntity employee=new BankEmployeeEntity();
		//return empser.saveEmp(newEmployee);
		return new BankEmployeeEntity();
	}
	
	@GetMapping("/employee/{id}")
	public Optional<BankEmployeeEntity> getEmployee(@PathVariable String id) {
		return null;
		//return empser.findById(id);
	}
	@GetMapping("/users")
	public Optional<BankEmployeeEntity> users() {
		System.out.println("in mapping.....");
		//return empser.findById(id);
		return null;
	}
	
	@DeleteMapping("/delemployee/{id}")
	  public void deleteEmployee(@PathVariable String id) {
	    //empser.deleteById(id);
	  }

	@GetMapping("/list")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity loginList(HttpServletRequest req)
	{
        System.out.println("lists ");
        //List<BankEmployeeEntity> list= empser.findAll();
        return null;
       // return ResponseEntity.ok(list);
	}
	
}