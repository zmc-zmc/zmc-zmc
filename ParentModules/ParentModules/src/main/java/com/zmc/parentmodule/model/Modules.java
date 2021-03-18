package com.zmc.parentmodule.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


@Table (name="MODULES")
@Entity
@Data
@ToString
@ApiModel()
public class Modules {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	@ApiModelProperty(hidden = true)
	//@SequenceGenerator(name="MODULE_SEQ", allocationSize=25)
	private Long id;
	
	@Column(name="MODULE_NAME",unique=true)
	@ApiModelProperty(hidden = true)
	private String moduleName;
	
	@Column(name="IS_ACTIVE")
	@ApiModelProperty(hidden = true)
	private Boolean isActive = Boolean.FALSE;
	
	@Column(name="IS_PARENT")
	@ApiModelProperty(hidden = true)
	private Boolean isParent = Boolean.FALSE;
	
	@Column(name="ICON_IMAGE")
	@ApiModelProperty(hidden = true)
	private byte[] iconImage;

	/*
	 * @Column(name="CRETAED_BY") private Long createdBy;
	 * 
	 * @Column(name="CREATED_DATE_TIME") private Date createdDateTime;
	 * 
	 * @Column(name="UPDATED_BY") private Long updatedBy;
	 * 
	 * @Column(name="UPDATED_DATE_TIME") private Date updatedDateTime;
	 * 
	 * @Column(name="HIBERNATE_VERSION") private long hibernateVersion;
	 * 
	 * @Column(name="IMAGE") private String image;
	 */
	
}
