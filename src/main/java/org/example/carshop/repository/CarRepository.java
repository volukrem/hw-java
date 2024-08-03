package org.example.carshop.repository;

import lombok.AllArgsConstructor;
import org.example.carshop.model.Car;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Repository class for managing ConferenceHall entities.
 */
@AllArgsConstructor
public class CarRepository {
    private final Map<Integer, Car> carsMap;

    /**
     * Retrieves all cars in Car Shop.
     *
     * @return a collection of all cars.
     */
    public Collection<Car> getAllCars() {
        return carsMap.values();
    }

    /**
     * Adds a new car to the repository.
     *
     * @param car the car entity to add.
     */
    public void addCar(Car car) {
        carsMap.put(car.getId(), car);
    }

    /**
     * Removes a car from the repository by its ID.
     *
     * @param carId the ID of the conference hall to remove.
     */
    public void removeCarById(int carId) {
        carsMap.remove(carId);
    }

    /**
     * Finds a car by its ID.
     *
     * @param carId the ID of the car to find.
     * @return the car object with the specified ID, or null if not found.
     */
    public Car findCarById(int carId) {
        return carsMap.get(carId);
    }

    /**
     * Updates an existing car in the repository.
     *
     * @param car the car object with updated information.
     */
    public void updateCar(Car car) {
        carsMap.put(car.getId(), car);
    }

    /**
     * Filters cars based on given parameters.
     *
     * @param brand Filter by brand.
     * @param model Filter by model.
     * @param prodYear Filter by year of manufacture.

     * @return Filtered collection of cars.
     */
    public Collection<Car> filterCars(String brand, String model, String prodYear, Boolean isAvailable) {
        return carsMap.values().stream()
                .filter(car -> (brand == null || car.getBrandName().equals(brand))
                        && (model == null || car.getModelName().equals(model))
                        && (prodYear == null || car.getProdYear().equals(prodYear))
                        && (isAvailable == null || car.isAvailable() == isAvailable))
                .collect(Collectors.toList());
    }
}
