package com.gnolivos.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gnolivos.users.entity.Users;

@Repository
public interface IUserRepository extends JpaRepository<Users, String> {
	
	Users findByEmail(String email);
	
}
