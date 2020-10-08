package com.chargemanag1.bankmanag1.Service;

import java.util.List;

import com.chargemanag1.bankmanag1.Entity.Rules;

public interface RuleServiceInterface {
	 boolean addRule(Rules rule);
	 boolean deleteRule(long code);
	 boolean approveRule(long code);
	 boolean rejectRule(long code);
	 boolean updateRuleData(Rules rule);
	 List<Rules> allRules(boolean status);
	 Rules updateCodeExists(long code);
}
