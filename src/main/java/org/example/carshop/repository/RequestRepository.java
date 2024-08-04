package org.example.carshop.repository;

import lombok.AllArgsConstructor;
import org.example.carshop.model.Car;
import org.example.carshop.model.Request;
import org.example.carshop.model.User;
import org.example.carshop.model.enums.RequestStatus;
import org.example.carshop.model.enums.RequestType;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Repository class for managing Request entities.
 */
@AllArgsConstructor
public class RequestRepository {
    private final Map<Integer, Request> requestsMap;

    /**
     * Retrieves all requests for a car purchase and a car maintenance.
     *
     * @return a collection of all requests.
     */
    public Collection<Request> getAllRequests() {
        return requestsMap.values();
    }

    /**
     * Adds a new request to the repository.
     *
     * @param request the request to add.
     */
    public void addRequest(Request request) {
        requestsMap.put(request.getId(), request);
    }

    /**
     * Removes a request from the repository by its ID.
     *
     * @param requestId the ID of the request to remove.
     */
    public void removeRequestById(int requestId) {
        requestsMap.remove(requestId);
    }

    /**
     * Finds a request by its ID.
     *
     * @param requestId the ID of the request to find.
     * @return the request with the specified ID, or null if not found.
     */
    public Request findRequestById(int requestId) {
        return requestsMap.get(requestId);
    }

    /**
     * Filters requests by a specific date and request type.
     *
     * @param date the date to filter requests by.
     * @param requestType the type of request to filter by.
     * @return a collection of requests that start or end on the specified date and are of the specified type.
     */
    public Collection<Request> filterRequestsByDateAndType(LocalDate date, RequestType requestType) {
        return requestsMap.entrySet().stream()
                .filter(entry -> entry.getValue().getCreationTime().toLocalDate().equals(date)
                        || entry.getValue().getCompletionTime().toLocalDate().equals(date))
                .filter(entry -> entry.getValue().getRequestType() == requestType)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).values();
    }

    /**
     * Filters requests by specific carId.
     *
     * @param carId the carId to filter requests by.
     * @return a collection of requests with the specified carId.
     */
    public Collection<Request> filterRequestsByCarAndType(int carId, RequestType requestType) {
        return requestsMap.entrySet().stream()
                .filter(entry -> entry.getValue().getCarId() == carId)
                .filter(entry -> entry.getValue().getRequestType() == requestType)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).values();
    }

    /**
     * Filters requests by a specific user.
     *
     * @param user the user to filter requests by.
     * @return a collection of requests made by the specified user.
     */
    public Collection<Request> filterRequestsByUserAndType(User user, RequestType requestType) {
        return requestsMap.entrySet().stream()
                .filter(entry -> entry.getValue().getUser() != null && entry.getValue().getUser().equals(user))
                .filter(entry -> requestType == null || entry.getValue().getRequestType() == requestType)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).values();
    }

    /**
     * Filters requests by a specific status.
     *
     * @param requestStatus the status to filter requests by.
     * @return a collection of requests having the specified status.
     */
    public Collection<Request> filterRequestsByRequestStatusAndType(RequestStatus requestStatus, RequestType requestType) {
        return requestsMap.entrySet().stream()
                .filter(entry -> entry.getValue().getRequestStatus().equals(requestStatus))
                .filter(entry -> entry.getValue().getRequestType() == requestType)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).values();
    }

    /**
     * Updates an existing request in the repository.
     *
     * @param request the request to add.
     */
    public void updateRequest(Request request) {
        requestsMap.put(request.getId(), request);
    }
}
