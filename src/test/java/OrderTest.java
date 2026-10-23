import org.example.model.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest {
    @Test
    void shouldCreateOrderWhenDataIsValid() {
        LocalDateTime time = LocalDateTime.of(2021, 2, 9, 16, 0, 22);
        Order order = new Order(time, "Industrial", 8800);

        assertEquals(time, order.getOrderTime());
        assertEquals("Industrial", order.getCompanyName());
        assertEquals(8800, order.getWeight());
    }


    @Test
    void shouldThrowWhenTimeIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Order(null, "Industrial", 8800));
    }


    @Test
    void shouldThrowWhenCompanyNameIsBlank() {
        LocalDateTime time = LocalDateTime.now();

        assertThrows(IllegalArgumentException.class, () -> new Order(time, null, 8800));
        assertThrows(IllegalArgumentException.class, () -> new Order(time, "", 8800));
        assertThrows(IllegalArgumentException.class, () -> new Order(time, "    ", 8800));
    }


    @Test
    void shouldThrowWhenWeightIsZeroOrNegative() {
        LocalDateTime time = LocalDateTime.now();

        assertThrows(IllegalArgumentException.class, () -> new Order(time, "Industrial", 0));
        assertThrows(IllegalArgumentException.class, () -> new Order(time, "Industrial", -200));
    }

    @Test
    void shouldThrowWhenWeightIsNunOrInfinite() {
        LocalDateTime now = LocalDateTime.now();

        assertThrows(IllegalArgumentException.class, () -> new Order(now, "Industrial", Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> new Order(now, "Industrial", Double.POSITIVE_INFINITY));
        assertThrows(IllegalArgumentException.class, () -> new Order(now, "Industrial", Double.NEGATIVE_INFINITY));
    }

}
