package com.zmc.parentmodule.service;

import java.util.Collection;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zmc.parentmodule.dto.ModulesDTO;
import com.zmc.parentmodule.exception.ModuleNotFoundException;
import com.zmc.parentmodule.model.Modules;
import com.zmc.parentmodule.modelMapper.ModuleFacade;
import com.zmc.parentmodule.repository.ModuleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModuleService {

	@Autowired
	ModuleRepository moduleRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ModuleFacade<?> moduleFacade;


	public ModulesDTO getModuleAgainstId(Long id) {
		Modules modules = moduleRepository
							.findById(id)
							.orElseThrow(() -> new ModuleNotFoundException("Module Not Found for ID:"+id));
		log.info("module"+modules);
		ModulesDTO modulesDTO = (ModulesDTO) moduleFacade.convertToDTO(modules, ModulesDTO.class);
		log.info("module dto{}" + modulesDTO);
		return modulesDTO;
	}
	
	public List<ModulesDTO> getAllModules() {
		List<Modules> modules = moduleRepository.findAll();
		if(modules.isEmpty()) {
			throw new ModuleNotFoundException("No Modules Found");
		}
		Collection<Modules> collectionModule = modules;
		List<ModulesDTO> moDtos = moduleFacade.convertListToDTOList(collectionModule, ModulesDTO.class);
		return moDtos;
	}

	public List<ModulesDTO> getModuleAgainstName(String name) {
		List<Modules> modules = moduleRepository.findByModuleName(name);
		if(modules.isEmpty()) {
			throw new ModuleNotFoundException("Module Not found for Name:"+name);
		}
		Collection<Modules> collectionModule = modules;
		List<ModulesDTO> moDtos = moduleFacade.convertListToDTOList(collectionModule, ModulesDTO.class);
		return moDtos;
	}

	
	public ModulesDTO saveOrUpdate(ModulesDTO modulesDTO) {
		try {
			Modules modules = modelMapper.map(modulesDTO, Modules.class);
			Modules savedModule = moduleRepository.save(modules);
			ModulesDTO updatedModuleDto = (ModulesDTO) moduleFacade.convertToDTO(savedModule, ModulesDTO.class);
			if (updatedModuleDto == null || updatedModuleDto.getId() == null) {
				return modulesDTO;
			}
			log.info("module dto{}" + updatedModuleDto);
			return updatedModuleDto;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	public void deleteModuleById(Long id) {
		try{
			moduleRepository.deleteById(id);
		}catch (Exception e) {
			throw e;
		}
	}

}
