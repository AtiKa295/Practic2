import org.example.adapter.HashOrderSourceAdapter;
import org.example.adapter.OrderParseException;
import org.example.adapter.PipeOrderSourceAdapter;
import org.example.model.Order;
import org.example.source.rawDataSource.IRawDataSource;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdapterParsingTest {
    @Test
    void pipeAdapterShouldParseValidLine() {
        IRawDataSource rawDataSource = () -> List.of("2021-02-09T16:00:22|Industrial|8800");
        PipeOrderSourceAdapter adapter = new PipeOrderSourceAdapter(rawDataSource);

        List<Order> orders = adapter.getOrders();

        assertEquals(1, orders.size());
        assertEquals("Industrial", orders.get(0).getCompanyName());
        assertEquals(8800, orders.get(0).getWeight());
    }

    @Test
    void hashAdapterShouldParseValidLine() {

        IRawDataSource rawDataSource = () -> List.of("2021-02-09T16:00:22#Industrial#8800");
        HashOrderSourceAdapter adapter = new HashOrderSourceAdapter(rawDataSource);

        List<Order> orders = adapter.getOrders();

        assertEquals(1, orders.size());
        assertEquals("Industrial", orders.get(0).getCompanyName());
        assertEquals(8800, orders.get(0).getWeight());
    }


    @Test
    void shouldThrowWhenFiledCountIsInvalid() {

        IRawDataSource rawDataSource = () -> List.of("2021-02-09T16:00:22|Almazik");
        PipeOrderSourceAdapter adapter = new PipeOrderSourceAdapter(rawDataSource);

        assertThrows(OrderParseException.class, adapter::getOrders);
    }

    @Test
    void shouldThrowWhenDataIsInvalid() {
        IRawDataSource rawDataSource = () -> List.of("notData|Industrial|8800");
        PipeOrderSourceAdapter adapter = new PipeOrderSourceAdapter(rawDataSource);

        assertThrows(OrderParseException.class, adapter::getOrders);
    }


    @Test
    void shouldThrowWhenWeightIsNotNumber() {
        IRawDataSource rawDataSource = () -> List.of("2021-02-09T16:00:22|Industrial|eight-thousand");
        PipeOrderSourceAdapter adapter = new PipeOrderSourceAdapter(rawDataSource);

        assertThrows(OrderParseException.class, adapter::getOrders);
    }

    @Test
    void shouldThrowWhenDateInvalidCalendarDate() {
        IRawDataSource rawDataSource = () -> List.of("2021-02-30T16:00:22|Industrial|8800");
        PipeOrderSourceAdapter adapter = new PipeOrderSourceAdapter(rawDataSource);

        assertThrows(OrderParseException.class, adapter::getOrders);
    }

    @Test
    void shouldThrowWhenTimeHasInvalidHours() {
        IRawDataSource rawDataSource = () -> List.of("2021-02-09T25:00:22|Industrial|8800");
        PipeOrderSourceAdapter adapter = new PipeOrderSourceAdapter(rawDataSource);

        assertThrows(OrderParseException.class, adapter::getOrders);
    }

    @Test
    void shouldThrowWhenExtraDelimitersPresent() {
        IRawDataSource rawDataSource = () -> List.of("2021-02-09T16:00:22|Industrial|8800|extra");
        PipeOrderSourceAdapter adapter = new PipeOrderSourceAdapter(rawDataSource);

        assertThrows(OrderParseException.class, adapter::getOrders);
    }

    @Test
    void shouldThrowWhenFieldIsEmpty() {
        IRawDataSource rawDataSource = () -> List.of("2021-02-30T16:00:22||8800");
        PipeOrderSourceAdapter adapter = new PipeOrderSourceAdapter(rawDataSource);

        assertThrows(OrderParseException.class, adapter::getOrders);
    }

    @Test
    void shouldThrowsWhenWeightIsNaN() {
        IRawDataSource rawDataSource = () -> List.of("2021-02-09T16:00:22|Industrial|8800|NaN");
        PipeOrderSourceAdapter adapter = new PipeOrderSourceAdapter(rawDataSource);

        assertThrows(OrderParseException.class, adapter::getOrders);
    }
}
