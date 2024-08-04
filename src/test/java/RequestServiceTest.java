import org.example.carshop.model.enums.RequestStatus;
import org.example.carshop.model.enums.RequestType;
import org.example.carshop.model.Request;
import org.example.carshop.model.User;
import org.example.carshop.repository.RequestRepository;
import org.example.carshop.service.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("RequestService Tests")
public class RequestServiceTest {

    private RequestService requestService;
    private RequestRepository requestRepository;
    private Map<Integer, Request> requestMap;

    @BeforeEach
    public void setUp() {
        requestMap = new HashMap<>();
        requestRepository = new RequestRepository(requestMap);
        requestService = new RequestService(requestRepository);
    }

    @Test
    @DisplayName("Test creating a request")
    public void testCreateRequest() {
        // Arrange
        LocalDateTime startTime = LocalDateTime.of(2024, 1, 1, 1, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 1, 1, 2, 0);
        int carId = 1;
        RequestType requestType = RequestType.ORDER;
        RequestStatus requestStatus = RequestStatus.NEW;

        // Act
        Request request = new Request(null, carId, startTime, endTime, requestType, requestStatus);
        requestService.addRequest(request);

        // Assert
        assertThat(requestMap).hasSize(1);
        Request createdRequest = requestMap.get(request.getId());
        assertThat(createdRequest.getId()).isEqualTo(request.getId());
        assertThat(createdRequest.getCarId()).isEqualTo(carId);
        assertThat(createdRequest.getCreationTime()).isEqualTo(startTime);
        assertThat(createdRequest.getCompletionTime()).isEqualTo(endTime);
        assertThat(createdRequest.getRequestType()).isEqualTo(requestType);
        assertThat(createdRequest.getRequestStatus()).isEqualTo(requestStatus);
    }

    @Test
    @DisplayName("Test deleting a request")
    public void testDeleteRequest() {
        // Arrange
        Request request1 = new Request(null, 1,  LocalDateTime.now(), LocalDateTime.now().plusHours(1), RequestType.ORDER, RequestStatus.NEW);
        Request request2 = new Request(null, 2,  LocalDateTime.now(), LocalDateTime.now().plusHours(1), RequestType.ORDER, RequestStatus.NEW);
        requestMap.put(request1.getId(), request1);
        requestMap.put(request2.getId(), request2);

        // Act
        requestService.deleteRequest(request1.getId());

        // Assert
        assertThat(requestMap).hasSize(1);
        assertThat(requestMap.get(request2.getId()).getId()).isEqualTo(request2.getId());
    }

    @Test
    @DisplayName("Test viewing all Requests")
    public void testViewAllRequests() {
        // Arrange
        Request request1 = new Request(null, 1,  LocalDateTime.now(), LocalDateTime.now().plusHours(1), RequestType.ORDER, RequestStatus.NEW);
        Request request2 = new Request(null, 2,  LocalDateTime.now(), LocalDateTime.now().plusHours(1), RequestType.ORDER, RequestStatus.NEW);
        requestMap.put(request1.getId(), request1);
        requestMap.put(request2.getId(), request2);

        // Act
        Collection<Request> allReqs = requestService.getAllRequests();

        // Assert
        assertThat(allReqs).hasSize(2);
    }

    @Test
    @DisplayName("Test filtering Requests by date")
    public void testFilterRequestsByDate() {
        // Arrange
        LocalDateTime newStartTime1 = LocalDateTime.of(2024, 6, 24, 9, 0);
        LocalDateTime newEndTime2 = LocalDateTime.of(2024, 6, 25, 11, 0);
        Request request1 = new Request(null, 1,  newStartTime1, newEndTime2, RequestType.ORDER, RequestStatus.NEW);
        Request request2 = new Request(null, 2,  LocalDateTime.now(), LocalDateTime.now().plusHours(1), RequestType.ORDER, RequestStatus.NEW);
        requestMap.put(request1.getId(), request1);
        requestMap.put(request2.getId(), request2);

        // Act
        Collection<Request> filteredMap = requestService.filterRequestsByDateAndType(LocalDate.from(newStartTime1), request1.getRequestType());
        Request firstRequest = filteredMap.stream().findFirst().orElse(null);

        // Assert
        assertThat(filteredMap).hasSize(1);
        assertThat(firstRequest.getId()).isEqualTo(request1.getId());
    }

    @Test
    @DisplayName("Test filtering Requests by user")
    public void testFilterRequestsByUser() {
        // Arrange
        User user1 = new User("us1", "password");
        User user2 = new User("us2", "password");
        Request request1 = new Request(null, 1,  LocalDateTime.now(), LocalDateTime.now().plusHours(1), RequestType.ORDER, RequestStatus.NEW);
        Request request2 = new Request(null, 2,  LocalDateTime.now(), LocalDateTime.now().plusHours(1), RequestType.ORDER, RequestStatus.NEW);
        request1.setUser(user1);
        request2.setUser(user2);
        requestMap.put(request1.getId(), request1);
        requestMap.put(request2.getId(), request2);

        // Act
        Collection<Request> filteredMap = requestService.filterRequestsByUserAndType(user1, request1.getRequestType());
        Request firstRequest = filteredMap.stream().findFirst().orElse(null);

        // Assert
        assertThat(filteredMap).hasSize(1);
        assertThat(firstRequest.getId()).isEqualTo(request1.getId());
    }

    @Test
    @DisplayName("Test filtering Requests by car")
    public void testFilterRequestsByCar() {
        // Arrange
        RequestType requestType = RequestType.ORDER;
        Request request1 = new Request(null, 1,  LocalDateTime.now(), LocalDateTime.now().plusHours(1), RequestType.ORDER, RequestStatus.NEW);
        Request request2 = new Request(null, 2,  LocalDateTime.now(), LocalDateTime.now().plusHours(1), RequestType.ORDER, RequestStatus.NEW);
        requestMap.put(request1.getId(), request1);
        requestMap.put(request2.getId(), request2);

        // Act
        Collection<Request> filteredMap = requestService.filterRequestsByCarAndType(1,requestType);
        Request firstRequest = filteredMap.stream().findFirst().orElse(null);

        // Assert
        assertThat(filteredMap).hasSize(1);
        assertThat(firstRequest.getId()).isEqualTo(request1.getId());
    }

    @Test
    @DisplayName("Test finding a Requests by ID")
    public void testFindRequestById() {
        // Arrange
        Request request1 = new Request(null, 1,  LocalDateTime.now(), LocalDateTime.now().plusHours(1), RequestType.ORDER, RequestStatus.NEW);
        Request request2 = new Request(null, 2,  LocalDateTime.now(), LocalDateTime.now().plusHours(1), RequestType.ORDER, RequestStatus.NEW);
        requestMap.put(request1.getId(), request1);
        requestMap.put(request2.getId(), request2);

        // Act
        Request foundRequest = requestService.findRequestById(request1.getId());

        // Assert
        assertThat(foundRequest).isNotNull();
        assertThat(foundRequest.getId()).isEqualTo(request1.getId());
    }
}
