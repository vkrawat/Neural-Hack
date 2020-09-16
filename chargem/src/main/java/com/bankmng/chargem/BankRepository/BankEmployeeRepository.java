package com.bankmng.chargem.BankRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankmng.chargem.Entity.BankEmployeeEntity;

@Repository
public interface BankEmployeeRepository  extends JpaRepository<BankEmployeeEntity,String>{
	 // using for all database operations...
	BankEmployeeEntity findByUserid(String userid);
}
