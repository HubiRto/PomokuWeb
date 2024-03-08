package pl.pomoku.backend.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.pomoku.backend.algorithm.firstSearch.BreathFirstSearch;
import pl.pomoku.backend.algorithm.firstSearch.result.BreathFirstSearchResult;
import pl.pomoku.backend.algorithm.firstSearch.result.DepthFirstSearchResult;
import pl.pomoku.backend.dto.request.FirstSearchRequest;
import pl.pomoku.backend.dto.response.FirstSearchResponse;
import pl.pomoku.backend.entity.algorithmEntity.DepthFirstSearchEntity;
import pl.pomoku.backend.repository.DepthFirstSearchEntityRepository;
import pl.pomoku.backend.utils.HashUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepthFirstSearchService implements AbstractFirstSearchService<DepthFirstSearchResult, DepthFirstSearchEntity> {
    private final DepthFirstSearchEntityRepository repository;
    private final ModelMapper mapper;

    public FirstSearchResponse calculateOrGet(FirstSearchRequest request) {
        String hash = HashUtils.generateGraphHash(request.graph(), request.start());
        Optional<DepthFirstSearchEntity> optionalBFS = repository.findByHash(hash);

        if (optionalBFS.isPresent()) {
            return mapper.map(optionalBFS.get(), FirstSearchResponse.class);
        } else {
            BreathFirstSearchResult result = new BreathFirstSearch(request.graph(), request.start()).run();
            DepthFirstSearchEntity entity = mapper.map(result, DepthFirstSearchEntity.class);
            entity.setHash(hash);
            repository.save(entity);
            return mapper.map(result, FirstSearchResponse.class);
        }
    }

    @Override
    public FirstSearchResponse fromEntityToResponse(DepthFirstSearchEntity entity) {
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
    public DepthFirstSearchEntity fromResultToEntity(DepthFirstSearchResult result) {
        return new DepthFirstSearchEntity(
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
    public DepthFirstSearchEntity fromResultToEntity(DepthFirstSearchResult result, String hash) {
        DepthFirstSearchEntity entity = fromResultToEntity(result);
        entity.setHash(hash);
        return entity;
    }

    @Override
    public FirstSearchResponse fromResultToResponse(DepthFirstSearchResult result) {
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