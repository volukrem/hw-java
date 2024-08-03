package org.example.carshop.factory;

import lombok.RequiredArgsConstructor;
import org.example.carshop.repository.UserRepository;
import org.example.carshop.service.UserService;

/**
 * Фабрика для создания экземпляров {@link UserService}.
 */
@RequiredArgsConstructor
public class UserServiceFactory implements CarshopFactory<UserService> {
    private final CarshopFactory<UserRepository> userRepositoryFactory;

    /**
     * Создает и возвращает новый экземпляр {@link UserService}.
     *
     * @return новый экземпляр {@link UserService}.
     */
    @Override
    public UserService create() {
        return new UserService(userRepositoryFactory.create());
    }
}
