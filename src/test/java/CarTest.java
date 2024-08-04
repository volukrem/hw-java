import org.example.carshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests for Car class")
public class CarTest {

    private Car car;

    @BeforeEach
    public void setUp() {
        car = new Car(1, "Model1", "Brand1", "2020","Great", true);;
    }

    @Test
    @DisplayName("Test constructor and getters")
    public void testConstructorAndGetters() {
        assertThat(car.getId()).isEqualTo(1);
        assertThat(car.getModelName()).isEqualTo("Model1");
        assertThat(car.getBrandName()).isEqualTo("Brand1");
        assertThat(car.getProdYear()).isEqualTo("2020");
        assertThat(car.getStateDesc()).isEqualTo("Great");
        assertThat(car.isAvailable()).isTrue();
    }

    @Test
    @DisplayName("Test setters")
    public void testSetters() {
        car.setId(2);
        car.setModelName("Model2");
        car.setBrandName("Brand2");
        car.setProdYear("2022");
        car.setStateDesc("Great2");
        car.setAvailable(false);

        assertThat(car.getId()).isEqualTo(2);
        assertThat(car.getModelName()).isEqualTo("Model2");
        assertThat(car.getBrandName()).isEqualTo("Brand2");
        assertThat(car.getProdYear()).isEqualTo("2022");
        assertThat(car.getStateDesc()).isEqualTo("Great2");
        assertThat(car.isAvailable()).isFalse();
    }

    @Test
    @DisplayName("Test toString method")
    public void testToString() {
        String expectedString = "Car(id=1, modelName=Model1, brandName=Brand1, prodYear=2020, stateDesc=Great, isAvailable=true)";
        assertThat(car.toString()).isEqualTo(expectedString);
    }
}
