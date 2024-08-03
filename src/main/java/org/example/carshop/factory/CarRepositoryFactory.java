package org.example.carshop.factory;

import org.example.carshop.model.Car;
import org.example.carshop.repository.CarRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика для создания объектов типа CarRepository.
 * Реализует интерфейс CarshopFactory для создания экземпляров репозитория автомобилей.
 */
public class CarRepositoryFactory implements CarshopFactory<CarRepository> {

    /**
     * Создает и возвращает новый экземпляр CarRepository с пустым начальным маппингом автомобилей.
     *
     * @return новый экземпляр CarRepository
     */
    @Override
    public CarRepository create() {
        Map<Integer, Car> carsHashMap = new HashMap<>();
        return new CarRepository(carsHashMap);
    }
}
