package ru.ylab.controller;

import ru.ylab.model.Habit;
import ru.ylab.model.User;
import ru.ylab.service.HabitService;
import ru.ylab.service.HabitServiceImpl;
import ru.ylab.service.UserService;
import ru.ylab.service.UserServiceImpl;

import java.util.List;
import java.util.Scanner;

public class UserController {
    private final UserService userService;
    private final HabitService habitService;

    public UserController(UserService userService, HabitService habitService) {
        this.userService = userService;
        this.habitService = habitService;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    if (userService.registerUser(username, email, password)) {
                        System.out.println("User registered successfully.");
                    } else {
                        System.out.println("Registration failed. User with this email already exists.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter email: ");
                    String loginEmail = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    currentUser = userService.authenticateUser(loginEmail, loginPassword);
                    if (currentUser != null) {
                        userMenu(currentUser, scanner);
                    } else {
                        System.out.println("Authentication failed.");
                    }
                }
                case 3 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void userMenu(User user, Scanner scanner) {
        while (true) {
            System.out.println("\nUser Menu for " + user.getUsername() + ":");
            System.out.println("1. Add Habit");
            System.out.println("2. Update Habit");
            System.out.println("3. Delete Habit");
            System.out.println("4. List Habits");
            System.out.println("5. List Habits by frequency");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter habit name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter habit description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter habit frequency (Daily/Weekly): ");
                    String frequency = scanner.nextLine();
                    Habit newHabit = new Habit(name, description, frequency);
                    habitService.addHabit(newHabit);
                    System.out.println("Habit added successfully.");
                }
                case 2 -> {
                    System.out.print("Enter habit name to update: ");
                    String habitToUpdate = scanner.nextLine();
                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();
                    System.out.print("Enter new frequency: ");
                    String newFrequency = scanner.nextLine();
                    Habit updatedHabit = new Habit(habitToUpdate, newDescription, newFrequency);
                    habitService.updateHabit(habitToUpdate, updatedHabit);
                    System.out.println("Habit updated successfully.");
                }
                case 3 -> {
                    System.out.print("Enter habit name to delete: ");
                    String habitToDelete = scanner.nextLine();
                    habitService.removeHabit(habitToDelete);
                    System.out.println("Habit deleted successfully.");
                }
                case 4 -> {
                    List<Habit> habits = habitService.getAllHabits();
                    System.out.println("Your habits:");
                    for (Habit habit : habits) {
                        System.out.println("- " + habit.getName() + " (Description: " + habit.getDescription() + " and frequency: " + habit.getFrequency() + ")");
                    }
                }
                case 5 -> {
                    System.out.print("Enter frequency to filter by (Daily/Weekly/Monthly): ");
                    String filterFrequency = scanner.nextLine();
                    List<Habit> filteredHabits = habitService.getHabitsByFrequency(filterFrequency);
                    if (filteredHabits.isEmpty()) {
                        System.out.println("No habits found with frequency: " + filterFrequency);
                    } else {
                        System.out.println("Filtered habits (" + filterFrequency + "):");
                        for (Habit habit : filteredHabits) {
                            System.out.println("- " + habit.getName() + " (Description: " + habit.getDescription() + ")");
                        }
                    }
                }
                case 6 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
