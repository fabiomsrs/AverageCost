package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AvarageCostCalculator {
    public static Map<String, BigDecimal> calculate(List<InventoryCost> inventoryCosts) {
        return inventoryCosts.stream()
                .filter(inventoryCost -> !inventoryCost.getQuantity().equals(0))
                .collect(Collectors.groupingBy(
                        InventoryCost::getItem,
                        Collectors.reducing(BigDecimal.ZERO, inventoryCost ->
                                inventoryCost.getCost().multiply(new BigDecimal(inventoryCost.getQuantity())), BigDecimal::add)
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().divide(new BigDecimal(getTotalQuantityForItem(inventoryCosts, e.getKey())), 2, RoundingMode.HALF_UP)
                ));
    }

    private static int getTotalQuantityForItem(List<InventoryCost> inventoryCosts, String item) {
        return inventoryCosts.stream()
                .filter(inventoryCost -> inventoryCost.getItem().equals(item))
                .mapToInt(InventoryCost::getQuantity)
                .sum();
    }

}
