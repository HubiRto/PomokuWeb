package pl.pomoku.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pomoku.backend.algorithm.firstSearch.BreathFirstSearch;
import pl.pomoku.backend.algorithm.firstSearch.result.BreathFirstSearchResult;
import pl.pomoku.backend.dto.request.FirstSearchRequest;
import pl.pomoku.backend.dto.response.FirstSearchResponse;
import pl.pomoku.backend.entity.algorithmEntity.BreathFirstSearchEntity;
import pl.pomoku.backend.repository.BreathFirstSearchEntityRepository;
import pl.pomoku.backend.utils.HashUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BreathFirstSearchService implements AbstractFirstSearchService<BreathFirstSearchResult, BreathFirstSearchEntity> {
    private final BreathFirstSearchEntityRepository repository;

    public FirstSearchResponse calculateOrGet(FirstSearchRequest request) {
        String hash = HashUtils.generateGraphHash(request.graph(), request.start());
        Optional<BreathFirstSearchEntity> optionalBFS = repository.findByHash(hash);

        if (optionalBFS.isPresent()) {
            return fromEntityToResponse(optionalBFS.get());
        } else {
            BreathFirstSearchResult result = new BreathFirstSearch(request.graph(), request.start()).run();
            save(fromResultToEntity(result, hash));
            return fromResultToResponse(result);
        }
    }

    public void save(BreathFirstSearchEntity entity) {
        repository.save(entity);
    }

    @Override
    public FirstSearchResponse fromEntityToResponse(BreathFirstSearchEntity entity) {
        return new FirstSearchResponse(
                entity.getTraverse(),
                entity.getTreeHeight(),
                entity.getMaxQueueSize(),
                entity.getInternalCount(),
                entity.getExternalCount(),
                entity.getInOperationsCount(),
                entity.getOutOperationsCount()
        );
    }

    @Override
    public BreathFirstSearchEntity fromResultToEntity(BreathFirstSearchResult result) {
        return new BreathFirstSearchEntity(
                result.getTreeHeight(),
                result.getMaxQueueSize(),
                result.getInternalCount(),
                result.getExternalCount(),
                result.getInOperationsCount(),
                result.getOutOperationsCount(),
                result.getTraverse()
        );
    }

    @Override
    public BreathFirstSearchEntity fromResultToEntity(BreathFirstSearchResult result, String hash) {
        BreathFirstSearchEntity entity = fromResultToEntity(result);
        entity.setHash(hash);
        return entity;
    }

    @Override
    public FirstSearchResponse fromResultToResponse(BreathFirstSearchResult result) {
        return new FirstSearchResponse(
                result.getTraverse(),
                result.getTreeHeight(),
                result.getMaxQueueSize(),
                result.getInternalCount(),
                result.getExternalCount(),
                result.getInOperationsCount(),
                result.getOutOperationsCount()
        );
    }
}