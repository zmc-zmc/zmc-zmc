package com.zmc.parentmodule.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	protected LocalDateTime timeStamp;
	protected HttpStatus status;
	protected List<String> errorMessages;
	protected String path;
}
