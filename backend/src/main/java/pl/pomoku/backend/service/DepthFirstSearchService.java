package pl.pomoku.backend.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.pomoku.backend.algorithm.firstSearch.BreathFirstSearch;
import pl.pomoku.backend.algorithm.firstSearch.result.BreathFirstSearchResult;
import pl.pomoku.backend.dto.request.FirstSearchRequest;
import pl.pomoku.backend.dto.response.FirstSearchResponse;
import pl.pomoku.backend.entity.algorithmEntity.DepthFirstSearchEntity;
import pl.pomoku.backend.repository.DepthFirstSearchEntityRepository;
import pl.pomoku.backend.utils.HashUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepthFirstSearchService {
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
}