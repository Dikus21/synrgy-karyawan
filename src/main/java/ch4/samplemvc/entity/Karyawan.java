package ch4.samplemvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Karyawan {
    private Long id;
    private String name;
}
