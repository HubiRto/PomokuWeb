package pl.pomoku.backend.service;

import pl.pomoku.backend.algorithm.firstSearch.result.AbstractFirstSearchResult;
import pl.pomoku.backend.entity.algorithmEntity.AbstractFirstSearchEntity;
import pl.pomoku.model.dto.response.FirstSearchResponse;

public interface AbstractFirstSearchService<R extends AbstractFirstSearchResult, E extends AbstractFirstSearchEntity> {
    FirstSearchResponse fromEntityToResponse(E entity);

    E fromResultToEntity(R result);

    E fromResultToEntity(R result, String hash);

    FirstSearchResponse fromResultToResponse(R result);
}
