package ch3.sesi4;

import ch3.junit.Model.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class StreamKaryawan {
    public static void main(String[] args) {
        Employee employee1 = new Employee("112", "Akbar", "10-02-1990");
        Employee employee2 = new Employee("113", "Binar", "30-10-1890");
        Employee employee3 = new Employee("114", "Coki", "11-12-2000");
        Employee employee4 = new Employee("115", "Dimar", "22-08-2001");
        Employee employee5 = new Employee("116", "Tika", "05-03-1985");
        Employee employee6 = new Employee("117", "Coki", "01-12-1983");

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
        employees.add(employee4);
        employees.add(employee5);
        employees.add(employee6);

        double averageAge = employees.stream()
                .map(employee -> karyawanAge(employee.getDob()))
                .collect(Collectors.averagingInt(Integer::intValue));
        System.out.println("Rata2 umur karyawan : " + averageAge);

        String karyawanTerdaftar = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", "));
        System.out.println("Semua karyawan terdaftar : " + karyawanTerdaftar);

        Map<Integer, List<String>> groupNameLength = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Karyawan berdasarkan panjang nama : " + groupNameLength);

        int uniqueNameCount = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.collectingAndThen(
                        Collectors.toSet(),
                        Set::size
                ));
        System.out.println("Nama yang unik :" + uniqueNameCount);
    }

    public static int karyawanAge(String dob) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        int birthYear = LocalDate.parse(dob, dateTimeFormatter).getYear();
        int currentYear = java.time.Year.now().getValue();
        return currentYear - birthYear;
    }
}
