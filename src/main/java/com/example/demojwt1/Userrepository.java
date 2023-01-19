package com.example.demojwt1;




import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import org.springframework.data.repository.CrudRepository;

public interface Userrepository extends JpaRepository<User, Integer>{

	User findByName(String username);
	
}
	
	