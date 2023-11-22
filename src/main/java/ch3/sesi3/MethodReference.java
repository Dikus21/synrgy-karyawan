package ch3.sesi3;

import ch3.junit.Controller.EmployeeList;
import ch3.junit.Model.DtoKaryawan;

import java.util.List;
import java.util.stream.Collectors;

public class MethodReference {
    public static void main(String[] args) {
        EmployeeList listEmployee = new EmployeeList();

        listEmployee.addEmployee("1", "Bima", "10-02-1990");
        listEmployee.addEmployee("2", "Joni", "03-11-1992");
        listEmployee.addEmployee("3", "Riki", "20-07-1995");

        List<DtoKaryawan> dtoKaryawanList = listEmployee.getEmployees().stream()
                .map(DtoKaryawan::new)
                .collect(Collectors.toList());

        dtoKaryawanList.forEach(System.out::println);
    }
}
