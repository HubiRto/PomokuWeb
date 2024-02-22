package pl.pomoku.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pomoku.backend.algorithm.bfs.BreathFirstSearch;
import pl.pomoku.backend.dto.request.BFSRequest;
import pl.pomoku.backend.dto.response.BFSResponse;
import pl.pomoku.backend.entity.BFSEntity;
import pl.pomoku.backend.mapper.BFSResponseEntityMapper;
import pl.pomoku.backend.repository.BFSEntityRepository;
import pl.pomoku.backend.utils.HashUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BFSService {
    private final BFSEntityRepository repository;
    private final BFSResponseEntityMapper mapper = new BFSResponseEntityMapper();
    public BFSResponse calculateOrGet(BFSRequest request) {
        String hash = HashUtils.generateGraphHash(request.graph(), request.start());
        Optional<BFSEntity> optionalBFS = repository.findByHash(hash);

        if(optionalBFS.isPresent()){
            return mapper.toDto(optionalBFS.get());
        }else {
            BFSEntity entity = new BreathFirstSearch(request.graph(), request.start()).traverse();
            entity.setHash(hash);
            repository.save(entity);
            return mapper.toDto(entity);
        }

    }
}