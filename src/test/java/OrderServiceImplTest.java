import org.example.model.Order;
import org.example.service.IDiscountPolicy;
import org.example.service.IOrderService;
import org.example.service.OrderService;
import org.example.service.StepDiscountPolicy;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceImplTest {

    @Test
    void shuoldSortAndApplyDiscountCorrectly() {
        LocalDateTime t1 = LocalDateTime.of(2021, 2, 9, 10, 0, 0);
        LocalDateTime t2 = LocalDateTime.of(2021, 2, 9, 11, 0, 0 );

        Order laterOrder = new Order(t2, "CompanyA", 100);
        Order earlierOrder = new Order(t1, "CompanyA", 100);

        IDiscountPolicy policy = new StepDiscountPolicy(0.50, 0.05);
        IOrderService service = new OrderService(10.0, policy);

        Map<String, Double> totals = service.proccesOrders(List.of(laterOrder, earlierOrder));

        assertEquals(1050.0, totals.get("CompanyA"), 0.001);
    }
}
