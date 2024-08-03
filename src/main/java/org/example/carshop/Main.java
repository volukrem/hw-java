package org.example.carshop;

import org.example.carshop.factory.RequestRepositoryFactory;
import org.example.carshop.factory.CarRepositoryFactory;
import org.example.carshop.factory.UserRepositoryFactory;
import org.example.carshop.in.UserConsole;
import org.example.carshop.factory.RequestServiceFactory;
import org.example.carshop.factory.CarshopServiceFactory;
import org.example.carshop.factory.CarshopFactory;
import org.example.carshop.factory.UserConsoleFactory;
import org.example.carshop.factory.UserServiceFactory;
import org.example.carshop.repository.RequestRepository;
import org.example.carshop.repository.CarRepository;
import org.example.carshop.repository.UserRepository;
import org.example.carshop.service.RequestService;
import org.example.carshop.service.CarShopService;
import org.example.carshop.service.UserService;

public class Main {
    public static void main(String[] args) {
        CarshopFactory<UserRepository> userRepositoryFactory = new UserRepositoryFactory();
        CarshopFactory<RequestRepository> requestRepositoryFactory = new RequestRepositoryFactory();
        CarshopFactory<UserService> userServiceFactory = new UserServiceFactory(userRepositoryFactory);
        CarshopFactory<UserConsole> userConsoleFactory = getUserConsoleCarshopFactory(requestRepositoryFactory, userServiceFactory);

        UserConsole userConsole = userConsoleFactory.create();
        userConsole.runStartCommands();
    }

    private static CarshopFactory<UserConsole> getUserConsoleCarshopFactory(CarshopFactory<RequestRepository> requestRepositoryFactory, CarshopFactory<UserService> userServiceFactory) {
        CarshopFactory<CarRepository> carRepositoryFactory = new CarRepositoryFactory();
        CarshopFactory<CarShopService> carShopServiceFactory = new CarshopServiceFactory(carRepositoryFactory);
        CarshopFactory<RequestService> requestServiceFactory = new RequestServiceFactory(requestRepositoryFactory);
        return new UserConsoleFactory(userServiceFactory, carShopServiceFactory, requestServiceFactory);
    }
}