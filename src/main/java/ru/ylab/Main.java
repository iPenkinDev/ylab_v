package ru.ylab;

import ru.ylab.controller.UserController;
import ru.ylab.repository.HabitRepository;
import ru.ylab.repository.UserRepository;
import ru.ylab.service.HabitService;
import ru.ylab.service.HabitServiceImpl;
import ru.ylab.service.UserService;
import ru.ylab.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // Инициализируем необходимые репозитории
        UserRepository userRepository = new UserRepository(); // Можно подключить реальную БД или использовать заглушку
        HabitRepository habitRepository = new HabitRepository(); // Тоже самое для привычек

        // Создаем объекты сервисов, передавая нужные репозитории
        UserService userService = new UserServiceImpl(userRepository);
        HabitService habitService = new HabitServiceImpl(habitRepository);

        // Создаем контроллер и передаем сервисы в конструктор
        UserController userController = new UserController(userService, habitService);

        // Запуск главного меню
        userController.showMenu();
    }
}