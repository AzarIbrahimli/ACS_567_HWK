package app;

import Manager.DataManager;
import Model.Player;

import java.sql.Connection;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataManager dataManager = DataManager.getInstance();

        System.out.println("Enter player name:  ");
        String name = scanner.next();

        System.out.println("Enter player surname:  ");
        String surname = scanner.next();
        System.out.println("Enter player age:  ");
        int age = scanner.nextInt();
        System.out.println("Enter player team:  ");
        String team = scanner.next();
        System.out.println("Enter player market value:  ");
        double value = scanner.nextDouble();

        Player player = new Player(name,surname,age,team,value);
        dataManager.insert(player);
        System.out.println("player added successfully");
    }
}
