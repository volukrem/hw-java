package org.example.carshop.model;

import lombok.Data;
import org.example.carshop.model.enums.RequestStatus;
import org.example.carshop.model.enums.RequestType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Represents a customer's request for a new car or a car maintenance.
 * Each request is uniquely identified by an id which is auto-incremented.
 */
@Data
public class Request {
    private static int nextRequestId = 1;

    /**
     * Unique identifier for the request.
     */
    private int id;

    /**
     * The user who made the request.
     */
    private User user;

    /**
     * The id of the car being booked.
     */
    private int carId;

    /**
     * The creation time of the request.
     */
    private LocalDateTime creationTime;

    /**
     * The completion time of the request.
     */
    private LocalDateTime completionTime;

    /**
     * The type of the resource being booked.
     */
    private RequestType requestType;

    /**
     * The type of current status of the request.
     */
    private RequestStatus requestStatus;

    /**
     * Constructs a new Request with the given parameters.
     * The id is auto-generated and incremented.
     *
     * @param user the user who made the request
     * @param carId the id of the User's car
     * @param creationTime the creation time of the request
     * @param requestType the type of the resource being requested
     * @param requestStatus type of current status of the request
     */
    public Request(User user, int carId, LocalDateTime creationTime,
                   LocalDateTime completionTime, RequestType requestType, RequestStatus requestStatus) {
        this.id = nextRequestId++;
        this.user = user;
        this.carId = carId;
        this.creationTime = creationTime != null ? creationTime : LocalDateTime.now();
        this.completionTime = completionTime != null ? completionTime : LocalDateTime.of(1970, 1, 1, 1, 1, 1);
        this.requestType = requestType;
        this.requestStatus = RequestStatus.NEW;
    }

    /**
     * Checks if the user who made the request is null.
     *
     * @return true if the user is null, false otherwise
     */
    public boolean isUserNull() {
        return this.user == null;
    }

    /**
     * Changes the status of the request.
     *
     * @param newStatus The new status to set for the request.
     */
    public void changeStatus(RequestStatus newStatus) {
        this.requestStatus = newStatus;
    }
}

