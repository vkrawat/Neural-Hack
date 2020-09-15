package com.virtusa.main;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import com.virtusa.model.Charge_Managment.Banking;
import com.virtusa.model.Charge_Managment;

public class DroolsTest {

	public static final void main(String[] args) {
		try {
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");
		Charge_Managment c1=new Charge_Managment();
		c1.setBank_type(Banking.RetailBanking);
		c1.setOperation_type("Withdrawl");
		c1.setBankATM("Other");
		c1.setAmount(4325.12);
		c1.setNo_of_days(27);
		c1.setTransfer_count(14);
		c1.setRange(52);
		kSession.insert(c1);
		
		Charge_Managment c12=new Charge_Managment();
		c12.setBank_type(Banking.RetailBanking);
		c12.setOperation_type("Withdrawl");
		c12.setBankATM("Parent");
		c12.setAmount(4325.12);
		c12.setNo_of_days(27);
		c12.setTransfer_count(25);
		c12.setRange(52);
		kSession.insert(c12);
		
		Charge_Managment c13=new Charge_Managment();
		c13.setBank_type(Banking.RetailBanking);
		c13.setOperation_type("statmentprint");
		kSession.insert(c13);
		
		Charge_Managment c14=new Charge_Managment();
		c14.setBank_type(Banking.RetailBanking);
		c14.setOperation_type("DuplicateDebitCard");
		kSession.insert(c14);
		
		Charge_Managment c15=new Charge_Managment();
		c15.setBank_type(Banking.RetailBanking);
		c15.setOperation_type("DuplicateCreditCard");
		kSession.insert(c15);
		

		Charge_Managment c2=new Charge_Managment();
		c2.setBank_type(Banking.All);
		c2.setOperation_type("FundTransfer");
		c2.setNo_of_days(27);
		c2.setRange(8);
		kSession.insert(c2);
		
		Charge_Managment c21=new Charge_Managment();
		c21.setBank_type(Banking.All);
		c21.setOperation_type("FundTransfer");
		c21.setNo_of_days(27);
		c21.setRange(18);
		kSession.insert(c21);
		
		Charge_Managment c22=new Charge_Managment();
		c22.setBank_type(Banking.All);
		c22.setOperation_type("FundTransfer");
		c22.setNo_of_days(27);
		c22.setRange(32);
		kSession.insert(c22);
		
		Charge_Managment c23=new Charge_Managment();
		c23.setBank_type(Banking.All);
		c23.setOperation_type("FundTransfer");
		c23.setNo_of_days(27);
		c23.setRange(59);
		
		kSession.insert(c23);
		
		
		Charge_Managment c3=new Charge_Managment();
		c3.setBank_type(Banking.RetailBanking);
		c3.setAmount(100000.000);
		c3.setAccount_type("current");
		c3.setNo_of_days(60);
		kSession.insert(c3);
		
		Charge_Managment c31=new Charge_Managment();
		c31.setBank_type(Banking.RetailBanking);
		c31.setAmount(100000.000);
		c31.setAccount_type("savings");
		c31.setNo_of_days(30);
		kSession.insert(c31);
		
		kSession.fireAllRules();
		} catch (Throwable t) {
		t.printStackTrace();
		}
		}
		}
