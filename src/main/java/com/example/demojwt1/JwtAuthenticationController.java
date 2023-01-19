package com.example.demojwt1;



import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demojwt1.JwtUserDetailsService;

import com.example.demojwt1.*;
import com.example.demojwt1.JwtTokenUtil;
import com.example.demojwt1.JwtRequest;
import com.example.demojwt1.JwtResponse;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	@Autowired
	private Userrepository userrepo;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public com.example.demojwt1.User save(@RequestBody User user) {
		com.example.demojwt1.User newUser = new com.example.demojwt1.User();
		System.out.println("TEst" +user.getpassword() +user.getname() );
		newUser.setname(user.getname());
	newUser.setpassword(user.getpassword());
		return userDetailsService.save(newUser);
	}
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST,headers = "Accept=application/json")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}


