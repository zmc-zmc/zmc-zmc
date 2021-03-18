package com.zmc.parentmodule.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@ApiModel(description = "MODULESDTO")
public class ModulesDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "MODULE ENTITY PRIMARY KEY")
	private Long id;
	
	@NotNull(message = "Module Name Required ")
	@NotEmpty(message = "Module Name can't be Empty")
	@ApiModelProperty(value = "MODULE NAME")
	private String moduleName;
	
    @NotNull
	@ApiModelProperty(value = "MODULE STATUS")
	private Boolean isActive;
	
    @NotNull
	@ApiModelProperty(value = "MODULE PARENT STATUS")
	private Boolean isParent;
	
	@ApiModelProperty(value = "MODULE ICON IMAGE")
	private byte[] iconImage;

}
