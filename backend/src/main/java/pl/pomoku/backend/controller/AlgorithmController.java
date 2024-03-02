package pl.pomoku.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pomoku.backend.dto.request.FirstSearchRequest;
import pl.pomoku.backend.service.BreathFirstSearchService;
import pl.pomoku.backend.service.DepthFirstSearchService;

@RestController
@RequestMapping("/api/v1/algorithm")
@RequiredArgsConstructor
public class AlgorithmController {
    private final BreathFirstSearchService breathFirstSearchService;
    private final DepthFirstSearchService depthFirstSearchService;

    @PostMapping("/bfs")
    public ResponseEntity<?> bfs(@RequestBody FirstSearchRequest request) {
        return ResponseEntity.ok(breathFirstSearchService.calculateOrGet(request));
    }

    @PostMapping("/dfs")
    public ResponseEntity<?> dfs(@RequestBody FirstSearchRequest request) {
        return ResponseEntity.ok(depthFirstSearchService.calculateOrGet(request));
    }
}
