package com.chargemanag1.bankmanag1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chargemanag1.bankmanag1.service.BankEmployeeServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter {

	@Autowired
	private BankEmployeeServiceImpl jwtUserDetailsService;

	public String doFilterInternal(HttpServletRequest request)
	{

		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		String role=null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				role=request.getHeader("Role").substring(5);
				return role;
			} 
			catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
				return null;
			}
			catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
				return null;
			}
		} 
		else {
			System.out.println("JWT Token does not begin with Bearer String");
			return null;
		}
	}
}
