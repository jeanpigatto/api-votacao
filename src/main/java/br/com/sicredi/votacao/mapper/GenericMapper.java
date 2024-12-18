package br.com.sicredi.votacao.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenericMapper {
	 private final ModelMapper modelMapper;

	    public GenericMapper(ModelMapper modelMapper) {
	        this.modelMapper = modelMapper;
	    }

	    // Converter de Entity para DTO
	    public <E, D> D toDTO(E entity, Class<D> dtoClass) {
	        return modelMapper.map(entity, dtoClass);
	    }

	    // Converter de DTO para Entity
	    public <D, E> E toEntity(D dto, Class<E> entityClass) {
	        return modelMapper.map(dto, entityClass);
	    }

	    // Converter lista de Entities para lista de DTOs
	    public <E, D> List<D> toDTOList(List<E> entities, Class<D> dtoClass) {
	        return entities.stream()
	                .map(entity -> toDTO(entity, dtoClass))
	                .collect(Collectors.toList());
	    }

	    // Converter lista de DTOs para lista de Entities
	    public <D, E> List<E> toEntityList(List<D> dtos, Class<E> entityClass) {
	        return dtos.stream()
	                .map(dto -> toEntity(dto, entityClass))
	                .collect(Collectors.toList());
	    }

}
