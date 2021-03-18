package com.zmc.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id",nullable = false,unique = true)
	protected long userId;
	
	@Column(name="user_nm",nullable = false,unique = true)
	protected String userName;
	
	@Column(name="email")
	protected String email;
	
	@Column(name="password",nullable = false)
	protected String password;
	
	@OneToMany(fetch = FetchType.EAGER,cascade =CascadeType.PERSIST)
	@JoinColumn(name="user_id")
	protected List<Roles> roles;
}
