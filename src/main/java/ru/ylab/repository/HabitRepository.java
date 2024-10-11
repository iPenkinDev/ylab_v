package ru.ylab.repository;

import ru.ylab.model.Habit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HabitRepository {
    private List<Habit> habits;

    public HabitRepository() {
        habits = new ArrayList<>();
    }

    public void addHabit(Habit habit) {
        habits.add(habit);
    }

    public void removeHabit(String habitName) {
        habits.removeIf(habit -> habit.getName().equals(habitName));
    }

    public void updateHabit(String habitName, Habit updatedHabit) {
        for (int i = 0; i < habits.size(); i++) {
            if (habits.get(i).getName().equals(habitName)) {
                habits.set(i, updatedHabit);
                return;
            }
        }
    }

    public List<Habit> getAllHabits() {
        return habits;
    }

    public List<Habit> getHabitsByFrequency(String frequency) {
        return habits.stream()
                .filter(habit -> habit.getFrequency().equalsIgnoreCase(frequency))
                .collect(Collectors.toList());
    }
}