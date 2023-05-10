package domain;

import java.math.BigDecimal;

public class InventoryCost {
    private String item;
    private Integer quantity;
    private BigDecimal cost;

    public InventoryCost(String item, Integer quantity, BigDecimal cost) {
        this.item = item;
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }
}
