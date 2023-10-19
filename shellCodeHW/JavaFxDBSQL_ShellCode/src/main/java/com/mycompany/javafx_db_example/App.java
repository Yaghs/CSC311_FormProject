package com.mycompany.javafx_db_example;

import com.mycompany.javafx_db_example.db.ConnDbOps;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class App extends Application {
    private static ConnDbOps cdbop;

    @Override
    public void start(Stage stage) throws IOException {
        // Launch the GUI directly
        changeScene(stage, "db_interface_gui.fxml");
    }

    private void changeScene(Stage stage, String fxml) {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource(fxml));
            Scene newScene = new Scene(newRoot, 850, 560);
            newScene.getStylesheets().add("style.css");
            stage.setScene(newScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        cdbop = new ConnDbOps();
        Scanner scan = new Scanner(System.in);

        char input;
        do {
            System.out.println(" ");
            System.out.println("============== Menu ==============");
            System.out.println("| To start GUI,           press 'g' |");
            System.out.println("| To connect to DB,       press 'c' |");
            System.out.println("| To display all users,   press 'a' |");
            System.out.println("| To insert to the DB,    press 'i' |");
            System.out.println("| To query by name,       press 'q' |");
            System.out.println("| To delete a user,       press 'd' |");
            System.out.println("| To edit user info,      press 'k' |");
            System.out.println("| To exit,                press 'e' |");
            System.out.println("===================================");
            System.out.print("Enter your choice: ");
            input = scan.next().charAt(0);
            scan.nextLine(); // Consume the newline character

            switch (input) {
                case 'g':
                    // Launch the GUI directly
                    launch(args);
                    break;

                case 'c':
                    cdbop.connectToDatabase(); // Connect to the database
                    break;

                case 'a':
                    cdbop.listAllUsers(); // List all users in the DB
                    break;

                case 'i':
                    Person p;
                    System.out.print("Enter Name: ");
                    String name = scan.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scan.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scan.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scan.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scan.nextLine();
                    p = new Person(name, email, address, password, phone);
                    cdbop.insertUser(p);
                    break;

                case 'q':
                    System.out.print("Enter the name to query: ");
                    String queryName = scan.nextLine();
                    cdbop.queryUserByName(queryName);
                    break;

                case 'e':
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println(" ");
        } while (input != 'e');

        scan.close();
    }
}
