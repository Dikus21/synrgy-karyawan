package ch3.sesi3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Lambda {
    public static void main(String[] args) {
        umur(33);
        ConsumerExample();
        biConsumer();

        List<String> names = new ArrayList<>();
        names.add("A");
        names.add("B");
        names.add("C");
        System.out.println("lambda expression untuk mencetak list dengan format");
        names.forEach(name -> System.out.println("Hello, " + name));
        //atau
        names.forEach(name1 -> {
            System.out.println("Selamat datang " + name1);
            System.out.println("Ayo siap2 " + name1);
        });
    }

    public static void ConsumerExample() {
        System.out.println("Contoh Dengan Lambda :");
        //cara 1
        Consumer<Integer> umur = u -> System.out.println("Umur " + u); // - hanya 1 action
        umur.accept(23);

        //cara 2
        Consumer<Integer> umur1 = u -> {
            System.out.println("Umur = " + u);
            System.out.println("Umur2 = " + u + 10);
        };
        umur1.accept(20);
    }

    public static void umur (int umur) {
        System.out.println("Contoh Normal :");
        System.out.println("Umur " + umur);
    }

    public static void biConsumer() {
        System.out.println("Contoh BiConsumer :");
        BiConsumer<Integer, String> umur = (u, n) -> {
            System.out.println("nama = " + n + "dan umur" + u);
        };
        umur.accept(20, "dika");
    }
}
