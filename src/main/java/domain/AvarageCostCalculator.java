package domain;

import java.math.BigDecimal;
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
                        e -> e.getValue().divide(new BigDecimal(getTotalQuantityForItem(inventoryCosts, e.getKey())))
                ));
    }

    private static int getTotalQuantityForItem(List<InventoryCost> inventoryCosts, String item) {
        return inventoryCosts.stream()
                .filter(ic -> ic.getItem().equals(item))
                .mapToInt(InventoryCost::getQuantity)
                .sum();
    }

}
