package ch3.junit.Controller;


import ch3.junit.View.View;
import ch3.junit.Model.Employee;
import ch3.junit.util.EmployeeException;
import lombok.Getter;

import java.util.HashMap;
@Getter
public class EmployeeController {
    private final View view = new View();
    private final HashMap<String, Employee> employees = new HashMap<>();

    public void addEmployee(String id, String name, String dob){
        Employee employee = new Employee(id, name, dob);
        employees.put(id, employee);
    }
    public void deleteEmployee(String id){
        if (employees.get(id) == null){
            System.out.println("Employee not found!");
        } else {
            employees.remove(id);
            System.out.println("Employee deleted!");
        }
    }
    public void updateEmployee(String id, String name){
        Employee employee = employees.get(id);
        try {
            if (employee == null){
                throw new EmployeeException("Employee not found!");
            }
            employee.setName(name);
            System.out.println("Updated!");
        } catch (EmployeeException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Finished");
        }

    }
    public void displayEmployees(){
        for (Employee employee : employees.values()){
            view.printEmployeeDetails(employee);
        }
    }
}
