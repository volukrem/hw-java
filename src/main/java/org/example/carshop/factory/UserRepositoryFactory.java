package org.example.carshop.factory;

import org.example.carshop.model.User;
import org.example.carshop.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика для создания экземпляров {@link UserRepository}.
 */
public class UserRepositoryFactory implements CarshopFactory<UserRepository> {

    /**
     * Создает и возвращает новый экземпляр {@link UserRepository}.
     *
     * @return новый экземпляр {@link UserRepository}.
     */
    @Override
    public UserRepository create() {
        Map<String, User> userMap = new HashMap<>();
        return new UserRepository(userMap);
    }
}
