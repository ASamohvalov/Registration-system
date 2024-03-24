package org.example;

import org.example.connect.Queries;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\n1.Authorization \n2.Registration \n3.Logout \nyour choose - ");

            int choose = 0;
            try {
                choose = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error, try again");
                continue;
            }

            if (choose == 1) {
                authorizeUser();
            } else if (choose == 2) {
                registerUser();
            } else if (choose == 3) {
                break;
            } else {
                System.out.println("Error, try again");
            }
        }
    }

    private static void registerUser() {
        System.out.println("Registration");
        Registration registration = new Registration();
        Scanner scanner = new Scanner(System.in);
        Person person;

        while (true) {
            System.out.print("Username - ");
            String username = scanner.nextLine();

            if (username.isEmpty()) {
                System.out.println("This username is empty");
                continue;
            } else if (registration.isUsernameExist(username)) {
                System.out.println("This username already exists, we suggest you log in");
                continue;
            }

            System.out.print("Password - ");
            String password = scanner.nextLine();
            if (!registration.isPasswordValid(password)) {
                System.out.println("Password is too short, less than 8 characters");
                continue;
            }

            System.out.print("first name - ");
            String firstName = scanner.nextLine();
            System.out.print("last name - ");
            String lastName = scanner.nextLine();

            person = new Person(username, password, firstName, lastName);
            Data data = new Data();
            data.addPerson(person);

            break;
        }

        System.out.println("!user is registered!");
        authorizeUser();
    }

    private static void authorizeUser() {
        System.out.println("Authorization");
        Authorization authorization = new Authorization();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.print("Username - ");
            String username = scanner.nextLine();
            System.out.print("Password - ");
            String password = scanner.nextLine();

            authorization.setUsername(username);
            authorization.setPassword(password);

            if (!authorization.IsDataTrue()) {
                System.out.println("Wrong username or password, try again");
                continue;
            }
            break;
        }

        Person person = authorization.getPerson();
        System.out.println("Username - " + person.getUsername());
        System.out.println("Password - " + person.getPassword());
        System.out.println("First name - " + person.getFirstName());
        System.out.println("Last name - " + person.getLastName());
    }
}

class Data {
    public void addPerson(Person person) {
        Queries queries = new Queries();
        queries.addNewUser(person.getUsername(), person.getPassword(), person.getFirstName(), person.getLastName());
    }
}
