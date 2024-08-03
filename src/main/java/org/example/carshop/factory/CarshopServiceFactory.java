package org.example.carshop.factory;

import lombok.RequiredArgsConstructor;
import org.example.carshop.repository.CarRepository;
import org.example.carshop.service.CarShopService;

/**
 * Фабрика для создания экземпляров {@link CarShopService}.
 */
@RequiredArgsConstructor
public class CarshopServiceFactory implements CarshopFactory<CarShopService> {
    private final CarshopFactory<CarRepository> carRepositoryFactory;

    /**
     * Создает и возвращает новый экземпляр {@link CarShopService}.
     *
     * @return новый экземпляр {@link CarShopService}.
     */
    @Override
    public CarShopService create() {
        return new CarShopService(carRepositoryFactory.create());
    }
}
