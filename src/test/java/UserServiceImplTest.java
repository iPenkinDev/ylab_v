import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ylab.model.User;
import ru.ylab.repository.UserRepository;
import ru.ylab.service.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserRepository userRepository;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void testRegisterUserWhenEmailIsUnique() {
        String username = "John";
        String email = "john@example.com";
        String password = "password123";

        when(userRepository.saveUser(any(User.class))).thenReturn(true);

        boolean result = userService.registerUser(username, email, password);

        assertTrue(result);
        verify(userRepository, times(1)).saveUser(any(User.class));
    }

    @Test
    void testRegisterUserWhenEmailAlreadyExists() {
        String username = "Jane";
        String email = "jane@example.com";
        String password = "password123";

        when(userRepository.saveUser(any(User.class))).thenReturn(false);

        boolean result = userService.registerUser(username, email, password);

        assertFalse(result);
        verify(userRepository, times(1)).saveUser(any(User.class));
    }

    @Test
    void testAuthenticateUserSuccess() {
        String email = "john@example.com";
        String password = "password123";
        User mockUser = mock(User.class);

        when(userRepository.findUserByEmail(email)).thenReturn(mockUser);
        when(mockUser.checkPassword(password)).thenReturn(true);

        User result = userService.authenticateUser(email, password);

        assertNotNull(result);
        assertEquals(mockUser, result);
        verify(userRepository, times(1)).findUserByEmail(email);
        verify(mockUser, times(1)).checkPassword(password);
    }

    @Test
    void testAuthenticateUserFailWithWrongPassword() {
        String email = "john@example.com";
        String password = "wrongpassword";
        User mockUser = mock(User.class);

        when(userRepository.findUserByEmail(email)).thenReturn(mockUser);
        when(mockUser.checkPassword(password)).thenReturn(false);

        User result = userService.authenticateUser(email, password);

        assertNull(result);
        verify(userRepository, times(1)).findUserByEmail(email);
        verify(mockUser, times(1)).checkPassword(password);
    }

    @Test
    void testAuthenticateUserFailWhenUserNotFound() {
        String email = "notfound@example.com";
        String password = "password123";

        when(userRepository.findUserByEmail(email)).thenReturn(null);

        User result = userService.authenticateUser(email, password);

        assertNull(result);
        verify(userRepository, times(1)).findUserByEmail(email);
    }
}
