package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.RegistrationException;
import core.basesyntax.db.Storage;
import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class RegistrationServiceImplTest {
    private static final String MIN_LOGIN_LENGTH = "1234";
    private static final String MIN_PASSWORD_LENGTH = "4321";
    private static final Integer MIN_AGE = 17;

    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        Storage.people.clear();
        registrationService = new RegistrationServiceImpl();
    }

    @Test
    void register_nullUser_notOk() {
        assertThrows(RegistrationException.class, () ->
                registrationService.register(null)
        );
    }

    @Test
    void register_nullLogin_notOk() {
        User user = new User(null, "123456", 20);
        assertThrows(RegistrationException.class, () ->
                registrationService.register(user));
    }

    @Test
    void register_shortLogin_notOk() {
        User user = new User(MIN_LOGIN_LENGTH, "123456", 20);
        assertThrows(RegistrationException.class, () ->
                registrationService.register(user));
    }

    @Test
    void register_nullPassword_notOk() {
        User user = new User("123456", null, 20);
        assertThrows(RegistrationException.class, () ->
                registrationService.register(user)
        );
    }

    @Test
    void register_shortPassword_notOk() {
        User user = new User("123456", MIN_PASSWORD_LENGTH, 20);
        assertThrows(RegistrationException.class, () ->
                registrationService.register(user)
        );
    }

    @Test
    void register_nullAge_notOk() {
        User user = new User("123456", "123456", null);
        assertThrows(RegistrationException.class, () ->
                registrationService.register(user)
        );
    }

    @Test
    void register_underageUser_notOk() {
        User user = new User("123456", "123456", MIN_AGE);
        assertThrows(RegistrationException.class, () ->
                registrationService.register(user));
    }

    @Test
    void register_duplicateLogin_notOk() {
        User user1 = new User("Maximus", "123456", 20);
        registrationService.register(user1);
        User user2 = new User("Maximus", "111222", 30);
        assertThrows(RegistrationException.class, () ->
                registrationService.register(user2));
    }

    @Test
    void register_validUser_ok() {
        User user = new User("Maximus", "123456", 20);
        User actual = registrationService.register(user);
        assertEquals(actual, user);
    }

    @Test
    void register_loginLengthSix_ok() {
        User user = new User("123456", "1234567", 20);
        User actual = registrationService.register(user);
        assertEquals(user, actual);

    }

    @Test
    void register_passwordLengthSix_ok() {
        User user = new User("1234567", "123456", 20);
        User actual = registrationService.register(user);
        assertEquals(user, actual);

    }

    @Test
    void register_ageEighteen_ok() {
        User user = new User("123455", "123466", 18);
        User actual = registrationService.register(user);
        assertEquals(user, actual);
    }
}
