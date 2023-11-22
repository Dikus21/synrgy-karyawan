package ch3.junit;

import ch3.junit.Model.EmployeeV2;

import java.util.Optional;

public class MainV2 {

    public static void main(String[] args) {
        EmployeeV2 employee1 = new EmployeeV2("10", null, "23-10-2000");
        Optional<String> name1 = employee1.getNameOptional();
        Optional<String> id1 = employee1.getIdOptional();
        Optional<String> dob1 = employee1.getDobOptional();

        System.out.println("Test 1 (orElse)");
        System.out.println("ID = " + id1.orElse("Unknown"));
        System.out.println("Nama = " + name1.orElse("Unknown"));
        System.out.println("Dob = " + dob1.orElse("Unknown"));

        EmployeeV2 employee2 = new EmployeeV2(null, "niki", null);
        Optional<String> name2 = employee2.getNameOptional();
        Optional<String> id2 = employee2.getIdOptional();
        Optional<String> dob2 = employee2.getDobOptional();

        System.out.println("Test 2 (ifPresent)");
        id2.ifPresent(i -> System.out.println("id = " + i));
        name2.ifPresent(n -> System.out.println("name = " + n));
        dob2.ifPresent(d -> System.out.println("dob = " + d));

        EmployeeV2 employee3 = new EmployeeV2("11", "pew", null);
        Optional<String> name3 = employee3.getNameOptional();
        Optional<String> id3 = employee3.getIdOptional();
        Optional<String> dob3 = employee3.getDobOptional();

        System.out.println("Test 3 (get)");
        System.out.println("Nama = " + name3.get());
    }

}
