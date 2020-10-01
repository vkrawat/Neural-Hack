package com.chargemanag1.bankmanag1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chargemanag1.bankmanag1.Entity.BankEmployeeEntity;

@Repository
public interface BankEmployeeRepository  extends JpaRepository<BankEmployeeEntity,String>{
	 // using for all database operations...
	BankEmployeeEntity findByUserid(String userid);
}
