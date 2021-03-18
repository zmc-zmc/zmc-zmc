package com.zmc.parentmodule.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zmc.parentmodule.dto.ModulesDTO;
import com.zmc.parentmodule.dto.ResponseDto;
import com.zmc.parentmodule.service.ModuleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(description = "Operations For Modules")
@RestController
@RequestMapping("/api/module")
public class ModuleController {

	@Autowired
	private ModuleService moduleService;

	// Get single Module using  module id
	@GetMapping(path ="/getModuleAgainstId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "FETCH MODULE BY ID")
	public @ResponseBody ResponseEntity<ModulesDTO> getModuleAgainstId(@PathVariable Long id) {
		ModulesDTO modulesDTO = null;
		modulesDTO = moduleService.getModuleAgainstId(id);
		log.info("module dto in controller{}" + modulesDTO);
		return new ResponseEntity<ModulesDTO>(modulesDTO, HttpStatus.OK);	
	}

	// Get All Module
	@GetMapping(path = "/getAllModules", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "FETCH ALL MODULES")
	public @ResponseBody ResponseEntity<List<ModulesDTO>> getAllModules() {
		List<ModulesDTO> modulesDTOs = null;
		modulesDTOs = moduleService.getAllModules();
		return new ResponseEntity<List<ModulesDTO>>(modulesDTOs, HttpStatus.OK);
	}

	// Get single Module using module name
	@GetMapping(path = "/getModuleAgainstName", produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiOperation(value = "FETCH MODULE AGAINST NAME")
	public @ResponseBody ResponseEntity<List<ModulesDTO>> getModuleAgainstModuleName(@RequestParam(value = "name", required = true) String name) {
		List<ModulesDTO> modulesDTOs = null;
		modulesDTOs = moduleService.getModuleAgainstName(name);
		log.info("module dto in controller{}" + modulesDTOs);
		return new ResponseEntity<List<ModulesDTO>>(modulesDTOs, HttpStatus.OK);
	}


	// create new module in database
	@PostMapping(path = "/saveNewModule", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "SAVE MODULE")
	public @ResponseBody ResponseEntity<ResponseDto> saveModule(@RequestBody @Valid ModulesDTO moduleDTO)
			throws Exception {
		ResponseDto responseDto=null;
		ModulesDTO savedMOdulesDTO = null;
		savedMOdulesDTO = moduleService.saveOrUpdate(moduleDTO);
		responseDto=new ResponseDto("Module Has Been Created Sucessfully", savedMOdulesDTO);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.CREATED);
	}

	// Updating exiting module 
	@PutMapping(path = "/putModuleInActive/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "MODFYING THE EXISTING MODULE")
	public ResponseEntity<ResponseDto> markModuleInActive(@PathVariable(value = "id") Long id, @RequestBody @Valid ModulesDTO module) {
		ResponseDto responseDto=null;
		ModulesDTO modulesDTO = null;
		modulesDTO = moduleService.getModuleAgainstId(id);
		modulesDTO.setModuleName(module.getModuleName());
		modulesDTO.setIsActive(module.getIsActive());
		modulesDTO.setIsParent(module.getIsParent());

		final ModulesDTO updatedModule = moduleService.saveOrUpdate(modulesDTO);
		responseDto=new ResponseDto("Module Has Been Updated Sucessfully", updatedModule);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.RESET_CONTENT);
	}


	@DeleteMapping(value = "/deleteModuleById/{id}")
	@ApiOperation(value = "DELETE MODULE BY ID")
	public ResponseEntity<String> deleteModuleById(@PathVariable Long id) {
		moduleService.deleteModuleById(id);
		return new ResponseEntity<String>("module has deleted sucessfully", HttpStatus.GONE);
	}

}
