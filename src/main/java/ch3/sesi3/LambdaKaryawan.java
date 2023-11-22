package ch3.sesi3;

import ch3.junit.Model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaKaryawan {

    public static void main(String[] args) {
        Employee employee1 = new Employee("112", "Akbar", "10-02-1990");
        Employee employee2 = new Employee("113", "Binar", "30-10-1890");
        Employee employee3 = new Employee("114", "Coki", "11-12-2000");
        Employee employee4 = new Employee("115", "Dimar", "22-08-2001");

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);

        employees.forEach(employee -> {
            System.out.println("Id : " + employee.getId());
            System.out.println("Name : " + employee.getName());
            System.out.println("Dob : " + employee.getDob());
        });

        //filtering list
        List<Employee> filteredEmployees = employees.stream().filter(employee -> employee.getName().contains("ar"))
                .collect(Collectors.toList());
        System.out.println("Contoh penggunaan stream untuk modifikasi sebuah list");
        filteredEmployees.forEach(employee -> {
            System.out.println("Id : " + employee.getId());
            System.out.println("Name : " + employee.getName());
            System.out.println("Dob : " + employee.getDob());
        });

        filteredEmployees.sort((e1, e2) -> e2.getName().compareTo(e1.getName()));
        System.out.println("Contoh Descnding using sort lambda");
        //filteredEmployees.forEach(k -> System.out.println("nama : " + k.getName()));
        filteredEmployees.forEach(System.out::println);

    }
}
