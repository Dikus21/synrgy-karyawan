package ch3.junit.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class Employee {
    private String id;
    private String name;
    private String dob;
}
