package ru.ylab.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String email;
    private String password;
    private List<Habit> habits;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.habits = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void updateUsername(String newUsername) {
        this.username = newUsername;
        System.out.println("Username updated to: " + newUsername);
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password updated successfully.");
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void addHabit(Habit habit) {
        habits.add(habit);
        System.out.println("Habit added to user " + username + ": " + habit);
    }

    public void listHabits() {
        if (habits.isEmpty()) {
            System.out.println("No habits found for user " + username + ".");
        } else {
            System.out.println("Habits for user " + username + ":");
            for (Habit habit : habits) {
                System.out.println(habit);
            }
        }
    }

    public void updateHabit(String name, String newDescription, String frequency) {
        for (int i = 0; i < habits.size(); i++) {
            if (habits.get(i).getName().equals(name)) {
                habits.set(i, new Habit(name, newDescription, frequency));
                System.out.println("Habit updated for user " + username + ": " + name);
                return;
            }
        }
        System.out.println("Habit not found for user " + username + ": " + name);
    }

    public void deleteHabit(String name) {
        habits.removeIf(habit -> habit.getName().equals(name));
        System.out.println("Habit deleted for user " + username + ": " + name);
    }
}
