package org.example.carshop.service;

import lombok.AllArgsConstructor;
import org.example.carshop.model.Car;
import org.example.carshop.repository.CarRepository;

import java.util.Collection;

/**
 * Service class for managing Car Shop operations.
 */
@AllArgsConstructor
public class CarShopService {
    private final CarRepository carRepository;

    /**
     * Retrieves all cars in car shop.
     *
     * @return a collection of all cars.
     */
    public Collection<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    /**
     * Adds a new conference hall.
     *
     * @param car the conference hall to add.
     */
    public void addCar(Car car) {
        carRepository.addCar(car);
    }

    /**
     * Updates an existing car info.
     *
     * @param car the updated car object.
     */
    public void updateCar(Car car) {
        carRepository.updateCar(car);
    }

    /**
     * Deletes a car by its ID.
     *
     * @param id the ID of the Car to delete.
     */
    public void deleteCar(int id) {
        carRepository.removeCarById(id);
    }

    /**
     * Finds a car by its ID.
     *
     * @param id the ID of the car to find.
     * @return the Car with the specified ID, or null if not found.
     */
    public Car findCarById(int id) {
        return carRepository.findCarById(id);
    }

    /**
     * Searches for cars based on given parameters.
     *
     * @param brand Brand to search by.
     * @param model Model to search by.
     * @param prodYear Year of manufacture to search by.
     * @param isAvailable availability of a car
     * @return Collection of matching cars.
     */
    public Collection<Car> searchCars(String brand, String model, String prodYear, Boolean isAvailable) {
        return carRepository.filterCars(brand, model, prodYear, isAvailable);
    }
}
