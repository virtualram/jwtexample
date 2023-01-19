package com.example.demojwt1;
import java.util.ArrayList;
// add jwt.secret=abcd to application properties

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	Userrepository userrepo;
   
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.example.demojwt1.User user = userrepo.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getname(), user.getpassword(),
				new ArrayList<>());
	}

	public com.example.demojwt1.User save(com.example.demojwt1.User user) {
		//com.example.demojwt1.User newUser = new com.example.demojwt1.User();
		//newUser.setname(user.getname());
		user.setpassword(bcryptEncoder.encode(user.getpassword()));
		return userrepo.save(user);
	}

/*
		if ("abcd".equals(username)) {
			return new User("abcd", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
	}*/
	/*	public Userrepository save(UserDTO user) {
			com.stockexchange.phase3.User newUser = new com.stockexchange.phase3.User(null, null, null);
			newUser.setname(user.getname());
			newUser.setpassword(bcryptEncoder.encode(user.getPassword()));
			return (Userrepository) userrepo.save(newUser);
		}*/



	}