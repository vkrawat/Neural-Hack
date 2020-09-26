package com.virtusa.main;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import com.virtusa.model.Charge_Managment.Banking;
import com.virtusa.model.Charge_Managment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;  
public class ChargeTestCopy {

	static int acc_no;
	static String acc_name;
	static String acc_type;
	static String category;
	static int with_drawl_count_parent;
	static int fund_transfer_count;
	static int with_drawl_count_other;
	static int Amount;
	public static final void main(String[] args) {
		try {
			final String url = "jdbc:mysql:///bankaccount";
		      final String user = "root";
		      final String password = "deepak";
		      Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection cn=DriverManager.getConnection(url,user,password);
		    Statement ps=cn.createStatement();
		    ResultSet rs=ps.executeQuery("select * from charges ");
		    while(rs.next())
		    {
		     acc_no=rs.getInt(1);
		     acc_name=rs.getString(2);
		    acc_type=rs.getString(3);
		    category=rs.getString(4);
		    with_drawl_count_parent=rs.getInt(5);
		    fund_transfer_count=rs.getInt(6);
		    with_drawl_count_other=rs.getInt(7);
		    Amount=rs.getInt(8);
			
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");
		Charge_Managment c1=new Charge_Managment();
		//System.out.println(acc_no+" "+acc_name+" "+acc_type+" "+category+" "+with_drawl_count_parent+" "+fund_transfer_count+" "+with_drawl_count_other);
		c1.setBank_type(Banking.RetailBanking);
		c1.setAcc_no(acc_no);
		c1.setAcc_name(acc_name);
		c1.setAccount_type(acc_type);
		c1.setOperation_type("Withdrawl");
		c1.setOperation_type("statmentprint");
		c1.setOperation_type("DuplicateDebitCard");
		c1.setOperation_type("DuplicateCreditCard");
		c1.setBankATM("Parent");
		c1.setNo_of_days(22);
		c1.setNo_of_days(30);
		c1.setTransfer_count(with_drawl_count_parent);
		c1.setAmount(Amount);
		kSession.insert(c1);		
		
		Charge_Managment c2=new Charge_Managment();
		//System.out.println(acc_no+" "+acc_name+" "+acc_type+" "+category+" "+with_drawl_count_parent+" "+fund_transfer_count+" "+with_drawl_count_other);
		c2.setBank_type(Banking.All);
		c2.setAcc_no(acc_no);
		c2.setAcc_name(acc_name);
		c2.setOperation_type("FundTransfer");
		c2.setNo_of_days(22);
		c2.setTransfer_count(fund_transfer_count);
		c2.setAmount(Amount);
		kSession.insert(c2);		
		
		
		kSession.fireAllRules();
		    }
		} catch (Throwable t) {
		t.printStackTrace();
		}
		}
		}
