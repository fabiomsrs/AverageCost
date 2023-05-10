import domain.AvarageCostCalculator;
import domain.InventoryCost;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class MainApplication {
    public static void main(String[] args) {
        List<InventoryCost> inventoryCosts = Arrays.asList(
                new InventoryCost("item1", 10, new BigDecimal("10.00")),
                new InventoryCost("item2", 20, new BigDecimal("25.00")),
                new InventoryCost("item1", 15, new BigDecimal("12.50")),
                new InventoryCost("item3", 30, new BigDecimal("20.00"))
        );

        System.out.println(AvarageCostCalculator.calculate(inventoryCosts));
    }
}
