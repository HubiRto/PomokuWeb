package pl.pomoku.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StackSequenceResponse {
    private int top;
    private int maxSize;
    private int endSize;
}
