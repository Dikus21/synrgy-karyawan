package ch3.junit.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.StringReader;
import java.util.Optional;

@Data
@AllArgsConstructor
public class EmployeeV2 {
    private String id;
    private String name;
    private String dob;

    public Optional<String> getNameOptional() {
        return Optional.ofNullable(name);
    }

    public Optional<String> getIdOptional() {
        return Optional.ofNullable(id);
    }

    public Optional<String> getDobOptional() {return Optional.ofNullable(dob);}
}
