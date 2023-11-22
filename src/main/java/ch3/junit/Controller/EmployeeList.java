package ch3.junit.Controller;

import ch3.junit.Model.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeList {
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(String id, String name, String dob) {
        Employee employee = new Employee(id, name, dob);
        employees.add(employee);
    }

}
