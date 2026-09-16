package core.basesyntax.service;

import core.basesyntax.RegistrationException;
import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null) {
            throw new RegistrationException("User can't be null");
        }

        if (user.getLogin() == null) {
            throw new RegistrationException("Login can't be null");
        }

        if (storageDao.get(user.getLogin()) != null) {
            throw new RegistrationException("User can't be null");
        }

        if (user.getLogin().length() < 6) {
            throw new RegistrationException("Login can't be < 6");
        }

        if (user.getPassword() == null) {
            throw new RegistrationException("Password can't be null");
        }

        if (user.getPassword().length() < 6) {
            throw new RegistrationException("Password can't be < 6");
        }

        if (user.getAge() == null) {
            throw new RegistrationException("Age can't be null");
        }

        if (user.getAge() < 18) {
            throw new RegistrationException("Age can't be < 18>");
        }
        storageDao.add(user);
        return user;
    }
}
