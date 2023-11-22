package ch3.junit.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoKaryawan {
    private String id;
    private String name;

    public DtoKaryawan(Employee employee) {

    }
}
