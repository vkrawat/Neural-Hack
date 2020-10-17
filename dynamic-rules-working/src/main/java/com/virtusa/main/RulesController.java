package com.virtusa.main;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

	
@RestController
//@CrossOrigin(origins="*")
public class RulesController 
{
	@GetMapping("/get")
	public ResponseEntity<ArrayList<RulesOutput>> getCharges()
	{
		try
		{
		System.out.println("hii");
		Statement st=database.getStatement();
		ResultSet rs=st.executeQuery("select Banking_Type,Operation,Period,Upper,Lower,Tax,Symbol from rulesdb ");
		System.out.println(rs.getFetchSize());
		//rs.first();
		ArrayList<RulesOutput> rules=new ArrayList();
		while(rs.next()) {
			RulesOutput output=new RulesOutput(rs.getString("Banking_Type"),rs.getString("Operation"));
			output.setCondition(rs.getInt("Lower"),rs.getInt("Upper") ,rs.getString("Period"));
			output.setFees(rs.getDouble("Tax"),rs.getString("Symbol"));
			rules.add(output);
			System.out.println(output);
		}
		return ResponseEntity.ok(rules);
		}
		catch(Exception e)
		{
			System.out.println("catched...."+e);
			return ResponseEntity.status(HttpStatus.METHOD_FAILURE).build();
		}
	}
	@PostMapping("/accountfees")
	public ResponseEntity<Charge_Managment> getFees(@RequestBody Charge_Managment account)
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
}
