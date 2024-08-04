import org.example.carshop.model.enums.RequestStatus;
import org.example.carshop.model.enums.RequestType;
import org.example.carshop.model.Request;
import org.example.carshop.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Tests for Request clas")
public class RequestTest {

    private Request request;
    private User mockUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUser = mock(User.class);
        request = new Request(mockUser, 101,
                LocalDateTime.of(2024, 6, 24, 10, 0),
                LocalDateTime.of(2024, 6, 24, 12, 0),
                RequestType.ORDER,
                RequestStatus.NEW
        );
    }

    @Test
    @DisplayName("Test constructor and getters")
    public void testConstructorAndGetters() {
        assertThat(request.getId()).isEqualTo(14);
        assertThat(request.getCarId()).isEqualTo(101);
        assertThat(request.getCreationTime()).isEqualTo(LocalDateTime.of(2024, 6, 24, 10, 0));
        assertThat(request.getCompletionTime()).isEqualTo(LocalDateTime.of(2024, 6, 24, 12, 0));
        assertThat(request.getRequestType()).isEqualTo(RequestType.ORDER);
    }

    @Test
    @DisplayName("Test setters")
    public void testSetters() {
        request.setId(2);
        request.setCarId(102);
        LocalDateTime newStartTime = LocalDateTime.of(2024, 6, 25, 9, 0);
        LocalDateTime newEndTime = LocalDateTime.of(2024, 6, 25, 11, 0);
        request.setCreationTime(newStartTime);
        request.setCompletionTime(newEndTime);
        request.setRequestType(RequestType.MAINTENANCE);

        assertThat(request.getId()).isEqualTo(2);
        assertThat(request.getCarId()).isEqualTo(102);
        assertThat(request.getCreationTime()).isEqualTo(newStartTime);
        assertThat(request.getCompletionTime()).isEqualTo(newEndTime);
        assertThat(request.getRequestType()).isEqualTo(RequestType.MAINTENANCE);
    }

    @Test
    @DisplayName("Test setUser method")
    public void testSetUser() {
        request.setUser(mockUser);
        assertThat(request.getUser()).isEqualTo(mockUser);
    }

}
