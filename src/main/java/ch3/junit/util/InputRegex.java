package ch3.junit.util;

public class InputRegex {
    public boolean nameCheck(String name){
        if (name.matches("^[A-Za-z][a-z]+( [A-Z][a-z]+)?$")){
            return true;
        } else if (name.matches("")) {
            return false;
        }
        System.out.println("Wrong name format!");
        return false;
    }
    public boolean idCheck(String id){
        if (id.matches("\\d+")){
            return true;
        } else if (id.matches("")) {
            return false;
        }
        System.out.println("Wrong ID format!");
        return false;
    }
}
