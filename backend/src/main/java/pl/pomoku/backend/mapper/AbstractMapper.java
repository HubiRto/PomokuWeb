package pl.pomoku.backend.mapper;

public interface AbstractMapper<D,E> {
    E toEntity(D dto);
    D toDto(E entity);
}
