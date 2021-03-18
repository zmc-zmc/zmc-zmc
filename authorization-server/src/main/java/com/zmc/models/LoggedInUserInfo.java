package com.zmc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="LOGGED_IN_USER_INFO")
public class LoggedInUserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID",nullable = false,unique = true)
	private Long id;
	
	@Column(name="LOGGED_IN_USER",nullable=false)
	private User loggedInUser;
	
	@Column(name="TOKEN")
	private String token;
}
