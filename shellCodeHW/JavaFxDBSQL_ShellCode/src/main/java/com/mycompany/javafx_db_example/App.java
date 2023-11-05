package com.mycompany.javafx_db_example;

import com.mycompany.javafx_db_example.db.ConnDbOps;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Scanner;

public class App extends Application {
    private static ConnDbOps cdbop;
    //loads the interface_gui.fxml I tried to implement the splashscreen and the log in button but for some odd reason it kept saying my fxml version was out of date
    //the issue got worse when I accidentally updated intellij to its latest version
    private Stage primaryStage;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        showScene1();

    }

    private void showScene1() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("splash_screen.fxml"));
            Scene scene = new Scene(root, 850, 560);
            scene.getStylesheets().add("style.css");
            primaryStage.setScene(scene);
            primaryStage.show();
            changeScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeScene() {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("LogInPassword.fxml"));

            Scene currentScene = primaryStage.getScene();
            Parent currentRoot = currentScene.getRoot();

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), currentRoot);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(e -> {
                // This will be executed after the fade out is finished
                Scene newScene = new Scene(newRoot, 850, 560);
                primaryStage.setScene(newScene);
                // You might want to add a fade-in transition for the new scene here as well
            });

            fadeOut.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        cdbop = new ConnDbOps();
        Scanner scan = new Scanner(System.in);
        //menu
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
                    // Launch the GUI, splash screen will be shown first
                    launch(args);
                    break;


                case 'c':
                    cdbop.connectToDatabase(); // Connect to the database
                    break;

                case 'a':
                    cdbop.listAllUsers(); // List all users in the DB
                    break;
                // insert new data for a new user(same feature can be done via gui)
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
                //safely deletes your info just by finding your email
                case 'd':
                    System.out.print("Enter the email to delete: ");
                    String deleteEmail = scan.nextLine();
                    Person personToDelete = new Person("", deleteEmail, "", "", "");
                    cdbop.deleteUser(personToDelete);
                    break;
                //updates your info
                case 'k':
                    System.out.print("Enter the new name: ");
                    String newName = scan.nextLine();
                    System.out.print("Enter the email to update: ");
                    String updateEmail = scan.nextLine();
                    System.out.print("Enter the new address: ");
                    String newAddress = scan.nextLine();
                    System.out.print("Enter the new password: ");
                    String newPassword = scan.nextLine();
                    System.out.print("Enter the new phone Number: ");
                    String newPhone = scan.nextLine();
                    Person updatedPerson = new Person(newName, updateEmail, newAddress, newPassword, newPhone);
                    cdbop.updateUser(updatedPerson);
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
