package ru.ylab.service;

import ru.ylab.model.Habit;

import java.util.List;

public interface HabitService {

    void addHabit(Habit habit);
    void removeHabit(String habitName);
    void updateHabit(String habitName, Habit updatedHabit);
    List<Habit> getAllHabits();
    List<Habit> getHabitsByFrequency(String frequency);
}
