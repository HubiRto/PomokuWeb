package pl.pomoku.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pomoku.backend.algorithm.Partition;
import pl.pomoku.backend.dto.request.HoarePartitionRequest;
import pl.pomoku.backend.service.HoarePartitionService;

@RestController
@RequestMapping("/api/v1/algorithm")
@RequiredArgsConstructor
public class AlgorithmController {
    private final HoarePartitionService hoarePartitionService;
    @PostMapping("/partition")
    public ResponseEntity<?> partition(@RequestBody Integer[] arr) {
        return ResponseEntity.ok(Partition.partition(arr));
    }

    @PostMapping("/hoare-partition")
    public ResponseEntity<?> hoarePartition(@RequestBody HoarePartitionRequest request) {
        return ResponseEntity.ok(hoarePartitionService.calculateOrGet(request));
    }
}
