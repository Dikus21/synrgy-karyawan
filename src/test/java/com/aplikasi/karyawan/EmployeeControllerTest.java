package com.aplikasi.karyawan;


import ch3.junit.Controller.EmployeeController;
import ch3.junit.Model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeControllerTest {
   private EmployeeController controller;

   @BeforeEach
    public void setUp(){
       controller = new EmployeeController();
   }

   @Test
    @DisplayName("Test Tambah Karyawan")
    public void testAddEmployee() {
       controller.addEmployee("112", "mira", "20-09-1993");
       assertEquals(1, controller.getEmployees().size());
       System.out.println("Test Passed");
   }

   @Test
   @DisplayName("Test Hapus Karyawan")
   public void testDeleteEmployee(){
       controller.addEmployee("112", "mira", "20-09-1993");
       controller.deleteEmployee("112");
       assertEquals(0, controller.getEmployees().size());
       System.out.println("Test Passed");
   }

   @Test
   @DisplayName("Test Update Karyawan")
   public void testUpdateEmployee(){
       controller.addEmployee("112", "mira", "20-09-1993");
       controller.updateEmployee("112", "john");
       Employee employee = controller.getEmployees().get("112");
       assertEquals(employee, controller.getEmployees().get("112"));
       System.out.println("Test Passed");
   }

   @AfterEach
    public void shutDown(){
       controller = null;
   }
}
