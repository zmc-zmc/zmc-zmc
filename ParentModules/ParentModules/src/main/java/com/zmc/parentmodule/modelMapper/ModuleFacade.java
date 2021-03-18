package com.zmc.parentmodule.modelMapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModuleFacade<K> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public <T,C> C convertToDTO( final T t, Class<C> c) {
		C k = modelMapper.map(t, c);
		return k;
	}
	
	public <T,C> List<C> convertListToDTOList(final Collection<T> t,Class<C> c){
		
	 List<C> tempDtoList=t.stream().map(l ->convertToDTO(l, c)).collect(Collectors.toList());
		return tempDtoList;
	};

	
	
}
