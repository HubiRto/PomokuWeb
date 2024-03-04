package pl.pomoku.backend.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.pomoku.backend.algorithm.stackSequence.Sequence;
import pl.pomoku.backend.algorithm.stackSequence.StackSequence;
import pl.pomoku.backend.algorithm.stackSequence.result.StackSequenceResult;
import pl.pomoku.backend.dto.request.StackSequenceRequest;
import pl.pomoku.backend.dto.response.StackSequenceResponse;
import pl.pomoku.backend.entity.algorithmEntity.SequenceEntity;
import pl.pomoku.backend.entity.algorithmEntity.StackSequenceEntity;
import pl.pomoku.backend.repository.StackSequenceRepository;
import pl.pomoku.backend.utils.HashUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StackSequenceService {
    private final StackSequenceRepository stackSequenceRepository;
    private final ModelMapper mapper;

    private void save(StackSequenceRequest request, StackSequenceResult result, String hash) {
        stackSequenceRepository.save(StackSequenceEntity.builder()
                .hash(hash)
                .sequence(mapListToEntity(request.sequences()))
                .initArray(Arrays.asList(request.initArray()))
                .top(result.getTop())
                .endSize(result.getEndSize())
                .maxSize(result.getMaxSize())
                .build());
    }

    public StackSequenceResponse get(StackSequenceRequest request) {
        String hash = HashUtils.generateHash(request);
        Optional<StackSequenceEntity> optionalStackSequence = stackSequenceRepository.findByHash(hash);
        if (optionalStackSequence.isEmpty()) {
            return calculate(request, hash);
        } else {
            return mapper.map(optionalStackSequence.get(), StackSequenceResponse.class);
        }
    }

    private StackSequenceResponse calculate(StackSequenceRequest request, String hash) {
        StackSequenceResult result = new StackSequence(request.initArray()).exec(request.sequences());
        save(request, result, hash);
        return mapper.map(result, StackSequenceResponse.class);
    }

    private List<SequenceEntity> mapListToEntity(List<Sequence> sequences) {
        List<SequenceEntity> sequenceEntityList = new ArrayList<>();
        for (Sequence sequence : sequences) {
            sequenceEntityList.add(mapToEntity(sequence));
        }
        return sequenceEntityList;
    }

    private SequenceEntity mapToEntity(Sequence sequence) {
        SequenceEntity entity = new SequenceEntity();
        entity.setNumber(sequence.getNumber());
        entity.setType(sequence.getSequenceType());
        if (sequence.getSequence() != null) entity.setSequence(mapToEntity(sequence.getSequence()));
        return entity;
    }

    private StackSequenceResponse mapToResponse(StackSequenceResult result) {
        return mapper.map(result, StackSequenceResponse.class);
    }
}
