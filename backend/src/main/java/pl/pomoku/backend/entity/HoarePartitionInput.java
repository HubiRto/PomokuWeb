package pl.pomoku.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class HoarePartitionInput {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<Integer> inputArray;
    private int k;

    public HoarePartitionInput(List<Integer> inputArray, int k) {
        this.inputArray = inputArray;
        this.k = k;
    }
}
