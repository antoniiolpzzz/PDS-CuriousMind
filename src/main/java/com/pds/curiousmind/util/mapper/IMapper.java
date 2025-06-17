package com.pds.curiousmind.util.mapper;

public interface IMapper<T, R> {
    /**
     * Converts data to an entity.
     *
     * @param data the data to convert
     * @return the entity
     */
    T toEntity(R data);

    /**
     * Converts an entity to data.
     *
     * @param entity the entity to convert
     * @return the converted data
     */
    R fromEntity(T entity);
}
