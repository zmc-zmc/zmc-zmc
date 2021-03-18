package com.zmc.parentmodule.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected String status;
	protected Object content;

}
