package pl.pomoku.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pomoku.backend.dto.response.HoareOperation;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoareOperationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int arg;

    @ElementCollection
    private List<Integer> arr = new ArrayList<>();

    private int index;

    public HoareOperationEntity(int arg, List<Integer> arr, int index) {
        this.arg = arg;
        this.arr = arr;
        this.index = index;
    }

    public HoareOperation to() {
        return new HoareOperation(arg, arr.toArray(new Integer[0]), index);
    }
}
