package pl.pomoku.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pomoku.backend.dto.request.BFSRequest;
import pl.pomoku.backend.service.BFSService;

@RestController
@RequestMapping("/api/v1/algorithm")
@RequiredArgsConstructor
public class AlgorithmController {
    private final BFSService bfsService;

    @PostMapping("/bfs")
    public ResponseEntity<?> bfs(@RequestBody BFSRequest request) {
        return ResponseEntity.ok(bfsService.calculateOrGet(request));
    }
}
