package ch3.junit.View;

import ch3.junit.Model.Employee;

public class View {
    public void printEmployeeDetails(Employee employee){
        System.out.println("Employee name: " + employee.getName());
        System.out.println("Employee ID: " + employee.getId());
    }

    public void menu(){
        System.out.println("Menu :");
        System.out.println("1. Add Employee");
        System.out.println("2. Delete Employee");
        System.out.println("3. Update Employee");
        System.out.println("4. Display All Employee");
        System.out.println("5. Exit");
    }
}
