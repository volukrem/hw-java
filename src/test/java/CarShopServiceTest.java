import org.example.carshop.model.Car;
import org.example.carshop.repository.CarRepository;
import org.example.carshop.service.CarShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests for CarShopService class")
public class CarShopServiceTest {

    private CarShopService carShopService;
    private Map<Integer, Car> carMap;

    @BeforeEach
    public void setUp() {
        carMap = new HashMap<>();
        CarRepository carRepository = new CarRepository(carMap);
        carShopService = new CarShopService(carRepository);
    }

    @Test
    @DisplayName("Test adding a car")
    public void testAddCar() {
        // Arrange
        Car car = new Car(1, "Model1", "Brand1", "2020","Great", true);

        // Act
        carShopService.addCar(car);

        // Assert
        assertThat(carMap).hasSize(1);
        assertThat(carMap.get(1)).isEqualTo(car);
    }

    @Test
    @DisplayName("Test retrieving available cars")
    public void testGetAvailableCars() {
        // Arrange
        Car car1 = new Car(1, "Model1", "Brand1", "2020","Great", true);
        Car car2 = new Car(2, "Model2", "Brand2", "2020","Great", true);
        carShopService.addCar(car1);
        carShopService.addCar(car2);

        // Act
        Collection<Car> cars = carShopService.getAllCars();

        // Assert
        assertThat(cars).hasSize(2);
    }


    @Test
    @DisplayName("Test updating a car")
    public void testUpdateCar() {
        // Arrange
        Car car = new Car(1, "Model1", "Brand1", "2020","Great", true);
        carMap.put(car.getId(), car);

        // Act
        Car updatedCar = new Car(1, "Model2", "Brand2", "2022","So-So", false);
        carShopService.updateCar(updatedCar);

        // Assert
        assertThat(carMap.get(car.getId())).isEqualTo(updatedCar);
        assertThat(carMap.get(car.getId()).isAvailable()).isFalse();
    }

    @Test
    @DisplayName("Test deleting a car")
    public void testDeleteCar() {
        // Arrange
        Car car = new Car(1, "Model1", "Brand1", "2020","Great", true);
        carMap.put(car.getId(), car);

        // Act
        carShopService.deleteCar(1);

        // Assert
        assertThat(carMap).isEmpty();
    }

    @Test
    @DisplayName("Test finding a ConferenceHall by ID")
    public void testFindCarById() {
        // Arrange
        Car car = new Car(1, "Model1", "Brand1", "2020","Great", true);
        carMap.put(car.getId(), car);

        // Act
        Car foundCar = carShopService.findCarById(1);

        // Assert
        assertThat(foundCar).isEqualTo(car);
    }
}
