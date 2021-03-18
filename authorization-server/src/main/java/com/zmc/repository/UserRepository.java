package com.zmc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zmc.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUserName(String username);

}
