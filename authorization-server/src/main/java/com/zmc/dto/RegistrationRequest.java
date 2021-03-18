package com.zmc.dto;

import java.util.List;

import com.zmc.models.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
	
	protected String userName;
	
	protected String email;
	
	protected String password;
	protected List<Roles> roles;
}
