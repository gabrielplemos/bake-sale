package com.bakesale.app.common;

import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public abstract class BaseMapper<E, D> {

    private final Class<E> entityClass;
    private final Class<D> dtoClass;
    private final ModelMapper modelMapper;

    public BaseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.entityClass =
                (Class<E>)
                        ((ParameterizedType) getClass().getGenericSuperclass())
                                .getActualTypeArguments()[0];
        this.dtoClass =
                (Class<D>)
                        ((ParameterizedType) getClass().getGenericSuperclass())
                                .getActualTypeArguments()[1];
    }

    public E convertToEntity(D dto) {
        return modelMapper.map(dto, entityClass);
    }

    public D convertToDTO(E entity) {
        return modelMapper.map(entity, dtoClass);
    }

    public Set<E> convertToEntity(Collection<D> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toSet());
    }

    public Set<D> convertToDTO(Collection<E> entities) {
        return entities.stream().map(this::convertToDTO).collect(Collectors.toSet());
    }
}
