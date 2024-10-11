import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ylab.model.Habit;
import ru.ylab.repository.HabitRepository;
import ru.ylab.service.HabitService;
import ru.ylab.service.HabitServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HabitServiceImplTest {

    private HabitService habitService;
    private HabitRepository habitRepository;

    @BeforeEach
    void setUp() {
        habitRepository = mock(HabitRepository.class);
        habitService = new HabitServiceImpl(habitRepository);
    }

    @Test
    void testAddHabit() {
        Habit habit = new Habit("Exercise", "Morning workout", "Daily");
        
        habitService.addHabit(habit);
        
        verify(habitRepository, times(1)).addHabit(habit); // Проверяем, что вызвался метод addHabit
    }

    @Test
    void testRemoveHabit() {
        String habitName = "Exercise";

        habitService.removeHabit(habitName);
        
        verify(habitRepository, times(1)).removeHabit(habitName); // Проверяем, что вызвался метод removeHabit
    }

    @Test
    void testUpdateHabit() {
        String habitName = "Exercise";
        Habit updatedHabit = new Habit("Exercise", "Evening workout", "Weekly");

        habitService.updateHabit(habitName, updatedHabit);
        
        verify(habitRepository, times(1)).updateHabit(habitName, updatedHabit); // Проверяем, что вызвался метод updateHabit
    }

    @Test
    void testGetAllHabits() {
        List<Habit> habits = Arrays.asList(
            new Habit("Exercise", "Morning workout", "Daily"),
            new Habit("Meditation", "Daily meditation", "Daily")
        );
        
        when(habitRepository.getAllHabits()).thenReturn(habits);
        
        List<Habit> result = habitService.getAllHabits();
        
        assertEquals(2, result.size());
        assertEquals("Exercise", result.get(0).getName());
        assertEquals("Meditation", result.get(1).getName());
    }

    @Test
    void testGetHabitsByFrequency() {
        String frequency = "Daily";
        List<Habit> habits = Arrays.asList(
            new Habit("Exercise", "Morning workout", "Daily"),
            new Habit("Meditation", "Daily meditation", "Daily")
        );
        
        when(habitRepository.getHabitsByFrequency(frequency)).thenReturn(habits);

        List<Habit> result = habitService.getHabitsByFrequency(frequency);
        
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(habit -> habit.getFrequency().equals("Daily")));
    }
}
