package org.example.carshop.factory;

import lombok.RequiredArgsConstructor;
import org.example.carshop.in.UserConsole;
import org.example.carshop.service.RequestService;
import org.example.carshop.service.CarShopService;
import org.example.carshop.service.UserService;

/**
 * Фабрика для создания экземпляров {@link UserConsole}.
 */
@RequiredArgsConstructor
public class UserConsoleFactory implements CarshopFactory<UserConsole> {
    private final CarshopFactory<UserService> userServiceFactory;
    private final CarshopFactory<CarShopService> carShopServiceFactory;
    private final CarshopFactory<RequestService> requestServiceFactory;

    /**
     * Создает и возвращает новый экземпляр {@link UserConsole}.
     *
     * @return новый экземпляр {@link UserConsole}.
     */
    @Override
    public UserConsole create() {
        UserService userService = userServiceFactory.create();
        CarShopService carShopService = carShopServiceFactory.create();
        RequestService requestService = requestServiceFactory.create();
        return new UserConsole(userService, carShopService, requestService);
    }
}
