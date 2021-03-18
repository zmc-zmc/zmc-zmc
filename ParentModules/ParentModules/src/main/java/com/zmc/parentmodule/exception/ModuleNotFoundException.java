package com.zmc.parentmodule.exception;

public class ModuleNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ModuleNotFoundException(String message) {
		super(message);
	}
}
