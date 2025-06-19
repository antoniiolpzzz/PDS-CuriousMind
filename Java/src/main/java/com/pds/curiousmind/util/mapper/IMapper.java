package com.pds.curiousmind.util.mapper;

/**
 * Generic mapper interface for converting between entities and data transfer objects (DTOs).
 * <p>
 * This interface defines methods for mapping data from a DTO or data object to an entity and vice versa.
 * It is intended to be implemented by classes that handle the transformation between domain models
 * and their external representations, such as for persistence or API communication.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     IMapper<User, UserDTO> userMapper = new UserMapper();
 *     User user = userMapper.toEntity(userDTO);
 *     UserDTO dto = userMapper.fromEntity(user);
 * </pre>
 * </p>
 *
 * @param <T> the entity type
 * @param <R> the data transfer object (DTO) or data type
 */
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
