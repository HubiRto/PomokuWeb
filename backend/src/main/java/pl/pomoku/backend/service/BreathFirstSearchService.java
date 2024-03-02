package pl.pomoku.backend.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.pomoku.backend.algorithm.firstSearch.BreathFirstSearch;
import pl.pomoku.backend.algorithm.firstSearch.result.BreathFirstSearchResult;
import pl.pomoku.backend.dto.request.FirstSearchRequest;
import pl.pomoku.backend.dto.response.FirstSearchResponse;
import pl.pomoku.backend.entity.BreathFirstSearchEntity;
import pl.pomoku.backend.repository.BreathFirstSearchEntityRepository;
import pl.pomoku.backend.utils.HashUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BreathFirstSearchService {
    private final BreathFirstSearchEntityRepository repository;
    private final ModelMapper mapper;

    public FirstSearchResponse calculateOrGet(FirstSearchRequest request) {
        String hash = HashUtils.generateGraphHash(request.graph(), request.start());
        Optional<BreathFirstSearchEntity> optionalBFS = repository.findByHash(hash);

        if (optionalBFS.isPresent()) {
            System.out.println(optionalBFS.get());
            return mapper.map(optionalBFS.get(), FirstSearchResponse.class);
        } else {
            BreathFirstSearchResult result = new BreathFirstSearch(request.graph(), request.start()).run();
            BreathFirstSearchEntity entity = mapper.map(result, BreathFirstSearchEntity.class);
            entity.setHash(hash);
            repository.save(entity);
            return mapper.map(result, FirstSearchResponse.class);
        }
    }
}