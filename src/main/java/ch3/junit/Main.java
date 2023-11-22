package ch3.junit;

import ch3.junit.Controller.EmployeeController;
import ch3.junit.View.View;
import ch3.junit.util.InputRegex;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeController controller = new EmployeeController();
        View view = new View();
        InputRegex check = new InputRegex();
        Scanner sc = new Scanner(System.in);
        boolean systemON = true;
        String id;
        String name;
        String dob;

        while (systemON){
            id = "";
            name = "";
            view.menu();
            switch (sc.nextInt()){
                case 1:
                    while (!check.idCheck(id)){
                        System.out.print("Enter ID: ");
                        id = sc.next();
                    }
                    while (!check.nameCheck(name)){
                        System.out.print("Enter Name: ");
                        name = sc.next();
                    }
                    dob = sc.next();
                    controller.addEmployee(id, name, dob);
                    break;
                case 2:
                    while (!check.idCheck(id)){
                        System.out.print("Enter ID to Delete: ");
                        id = sc.next();
                    }
                    controller.deleteEmployee(id);
                    break;
                case 3:
                    while (!check.idCheck(id)){
                        System.out.print("Enter ID to Update: ");
                        id = sc.next();
                    }
                    while (!check.nameCheck(name)){
                        System.out.print("Enter New Name: ");
                        name = sc.next();
                    }
                    controller.updateEmployee(id, name);
                    break;
                case 4:
                    controller.displayEmployees();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    systemON = false;
                    sc.close();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
