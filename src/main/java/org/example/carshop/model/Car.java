package org.example.carshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a car for purchase in the Car Shop.
 */
@Data
@AllArgsConstructor
public class Car {
    /**
     * Unique identifier for the car model.
     */
    private int id;

    /**
     * The name of the model.
     */
    private String modelName;

    /**
     * The name of the car brand.
     */
    private String brandName;

    /**
     * The year the car was produced in.
     */
    private String prodYear;

    /**
     * The description of current car state.
     */
    private String stateDesc;

    /**
     * Indicates whether the car is currently available for order.
     */
    private boolean isAvailable;

}
