package pl.pomoku.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pomoku.backend.algorithm.HoarePartition;
import pl.pomoku.backend.dto.request.HoarePartitionRequest;
import pl.pomoku.backend.dto.response.HoarePartitionResponse;
import pl.pomoku.backend.entity.HoarePartitionResult;
import pl.pomoku.backend.repository.HoarePartitionResultRepository;
import pl.pomoku.backend.utils.HashUtils;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HoarePartitionService {
    private final HoarePartitionResultRepository repository;

    public HoarePartitionResponse calculateOrGet(HoarePartitionRequest request) {
        String hashToken = HashUtils.generateHash(Arrays.asList(request.arr()), request.k());
        Optional<HoarePartitionResult> optionalResult = repository.findByHashToken(hashToken);
        if(optionalResult.isPresent()) {
            return optionalResult.get().getResponse();
        }else {
            HoarePartitionResult result = new HoarePartition(request.arr(), request.k(), hashToken).run();
            repository.save(result);
            return result.getResponse();
        }
    }
}
