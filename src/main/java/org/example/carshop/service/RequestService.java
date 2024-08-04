package org.example.carshop.service;

import lombok.AllArgsConstructor;
import org.example.carshop.model.Car;
import org.example.carshop.model.enums.RequestStatus;
import org.example.carshop.model.enums.RequestType;
import org.example.carshop.model.Request;
import org.example.carshop.model.User;
import org.example.carshop.repository.RequestRepository;
import java.time.LocalDate;
import java.util.Collection;

/**
 * Service class for managing requests.
 */
@AllArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    /**
     * Adds a new request to the repository.
     *
     * @param request The requests to add.
     */
    public void addRequest(Request request) {
        requestRepository.addRequest(request);
    }

    /**
     * Deletes a request from the repository based on its ID.
     *
     * @param requestId The ID of the request to delete.
     */
    public void deleteRequest(int requestId) {
        requestRepository.removeRequestById(requestId);
    }

    /**
     * Retrieves all requests from the repository.
     *
     * @return A collection of all requests.
     */
    public Collection<Request> getAllRequests() {
        return requestRepository.getAllRequests();
    }

    /**
     * Filters requests based on a specified date.
     *
     * @param date The date to filter requests by.
     * @return A collection of requests that match the specified date.
     */
    public Collection<Request> filterRequestsByDateAndType(LocalDate date, RequestType requestType) {
        return requestRepository.filterRequestsByDateAndType(date, requestType);
    }

    /**
     * Filters requests by specific carId.
     *
     * @param carId the carId to filter requests by.
     * @return a collection of requests made for specific car.
     */
    public Collection<Request> filterRequestsByCarAndType(int carId, RequestType requestType) {
        return requestRepository.filterRequestsByCarAndType(carId, requestType);
    }

    /**
     * Filters requests based on a specified user.
     *
     * @param user The user to filter requests by.
     * @return A collection of requests that belong to the specified user.
     */
    public Collection<Request> filterRequestsByUserAndType(User user, RequestType requestType) {
        return requestRepository.filterRequestsByUserAndType(user, requestType);
    }

    /**
     * Filters requests by a specific status.
     *
     * @param requestStatus the status to filter requests by.
     * @return a collection of requests having the specified status.
     */
    public Collection<Request> filterRequestsByRequestStatusAndType(RequestStatus requestStatus, RequestType requestType) {
        return requestRepository.filterRequestsByRequestStatusAndType(requestStatus, requestType);
    }
    /**
     * Finds a request by its ID.
     *
     * @param id The ID of the request to find.
     * @return The request with the specified ID, or null if not found.
     */
    public Request findRequestById(int id) {
        return requestRepository.findRequestById(id);
    }

    /**
     * Updates an existing car in the repository.
     *
     * @param request the car object with updated information.
     */
    public void updateRequest(Request request) {
        requestRepository.updateRequest(request);
    }
}
