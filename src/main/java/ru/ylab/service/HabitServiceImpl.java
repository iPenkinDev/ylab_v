package ru.ylab.service;

import ru.ylab.model.Habit;
import ru.ylab.model.User;
import ru.ylab.repository.HabitRepository;

import java.util.List;

public class HabitServiceImpl implements HabitService {
    private HabitRepository habitRepository;

    // Конструктор с параметром
    public HabitServiceImpl(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    @Override
    public void addHabit(Habit habit) {
        habitRepository.addHabit(habit);
    }

    @Override
    public void removeHabit(String habitName) {
        habitRepository.removeHabit(habitName);
    }

    @Override
    public void updateHabit(String habitName, Habit updatedHabit) {
        habitRepository.updateHabit(habitName, updatedHabit);
    }

    @Override
    public List<Habit> getAllHabits() {
        return habitRepository.getAllHabits();
    }

    @Override
    public List<Habit> getHabitsByFrequency(String frequency) {
        return habitRepository.getHabitsByFrequency(frequency);
    }
}
