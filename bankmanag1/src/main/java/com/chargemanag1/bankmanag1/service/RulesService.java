package com.chargemanag1.bankmanag1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chargemanag1.bankmanag1.entity.Rules;
import com.chargemanag1.bankmanag1.repository.RuleRepository;

@Service
public class RulesService implements RuleServiceInterface {
	@Autowired
	RuleRepository rulerepo;
	
    public boolean addRule(Rules rule)
    {
    	System.out.println(" adding rule ");
    	try
    	{
    		rulerepo.save(rule);
    		return true;
    	}
    	catch(Exception ex)
    	{
    		return false;
    	}
    }
    
    public Rules updateCodeExists(long code)
    {
    	try
    	{
    		 ArrayList<Long> list=new ArrayList();
    		 list.add(code);
    		 return rulerepo.findByCodeAndStatus(code,true).get(0);
    	}
    	catch(Exception e)
    	{
    		System.out.println("some error occurred , MAY b code doesnt exists..."+e.getMessage());
    		return null;
    	}
    }
    
    public boolean deleteRule(long code)
    {
    	System.out.println(" deleting rule");
    	try {
    		System.out.println(code+"..");
    		List<Rules> rule=rulerepo.findByCodeAndStatus(code, true);
    		if(rule.size()!=0)
    			
    		{rulerepo.deleteById(code);
    		return true;
    		}
    		return false;
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    		return false;
    	}
    }
    
    public List<Rules> allRules(boolean status)
    {
    	System.out.println(" all  rules "+status);
    	List<Rules> rules= rulerepo.findAllByStatus(status);
    		return rulerepo.findAllByStatus(status);
    }
    
    public boolean rejectRule(long code)
    {
    	System.out.println(" rejecting rule");
    	try 
    	{
    		rulerepo.deleteById(code);
    		return true;
    	}
    	catch(Exception ex)
    	{
    		return false;
    	}
    }
    public boolean updateRuleData(Rules rule)
    {
    	System.out.println(" updating rule");
    	try {
    		 System.out.println(rule.getCode());
    		 long code=rule.getCode();
    		 Rules ruleobj=rulerepo.getOne(code);
    		 ruleobj.setCategory(rule.getCategory());
    		 ruleobj.setConditionType(rule.getConditionType());
    		 ruleobj.setLimitFrom(rule.getLimitFrom());
    		 ruleobj.setLimitTo(rule.getLimitTo());
    		 ruleobj.setFeesType(rule.getFeesType());
    		 ruleobj.setFeesValue(rule.getFeesValue());
    		 ruleobj.setOperationType(rule.getOperationType());
    		 rulerepo.save(ruleobj);
      		  return true;
    	}
    	catch(Exception ex)
    	{
    		  ex.getStackTrace();
    		  return false;
    	}
    }

    public boolean approveRule(long code)
    {
    	System.out.println(" approving rule");
    	try {
    		  Rules ruleobj=rulerepo.getOne(code);
    		  ruleobj.setStatus(true);
    		  rulerepo.save(ruleobj);
      		  return true;
    	}
    	catch(Exception ex)
    	{
    		  ex.getStackTrace();
    		  return false;
    	}
    }
}
