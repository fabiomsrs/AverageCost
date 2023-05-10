import domain.AvarageCostCalculator;
import domain.InventoryCost;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AverageCostCalculetorTest {
    @Test
    public void testAvarage() {
        List<InventoryCost> inventoryCosts = Arrays.asList(
                new InventoryCost("item1", 10, new BigDecimal("10.00")),
                new InventoryCost("item2", 20, new BigDecimal("25.00")),
                new InventoryCost("item1", 15, new BigDecimal("12.50")),
                new InventoryCost("item3", 30, new BigDecimal("20.00"))
        );
        Map<String, BigDecimal> expected = new HashMap<>();
                expected.put("item2", new BigDecimal(25.00).setScale(2));
                expected.put("item1", new BigDecimal(11.50).setScale(2));
                expected.put("item3", new BigDecimal(20.00).setScale(2));

        Assert.assertEquals(expected, AvarageCostCalculator.calculate(inventoryCosts));
    }

    @Test
    public void testAvarageWithZeroQuantity() {
        List<InventoryCost> inventoryCostsSecondTest = Arrays.asList(
                new InventoryCost("item1", 10, new BigDecimal("10.00")),
                new InventoryCost("item2", 0, new BigDecimal("25.00")),
                new InventoryCost("item1", 0, new BigDecimal("12.50")),
                new InventoryCost("item3", 30, new BigDecimal("20.00"))
        );
        Map<String, BigDecimal> expectedSecondTest = new HashMap<>();
        expectedSecondTest.put("item3", new BigDecimal(20.00).setScale(2));
        expectedSecondTest.put("item1", new BigDecimal(10.00).setScale(2));
        Assert.assertEquals(expectedSecondTest, AvarageCostCalculator.calculate(inventoryCostsSecondTest));
    }

    @Test
    public void testAvarageWithZeroCost() {
        List<InventoryCost> inventoryCosts = Arrays.asList(
                new InventoryCost("item1", 10, new BigDecimal("10.00")),
                new InventoryCost("item2", 20, new BigDecimal("25.00")),
                new InventoryCost("item1", 10, new BigDecimal("0.00")),
                new InventoryCost("item3", 30, new BigDecimal("20.00"))
        );
        Map<String, BigDecimal> expected = new HashMap<>();
        expected.put("item2", new BigDecimal(25.00).setScale(2));
        expected.put("item1", new BigDecimal(5.00).setScale(2));
        expected.put("item3", new BigDecimal(20.00).setScale(2));

        Assert.assertEquals(expected, AvarageCostCalculator.calculate(inventoryCosts));
    }

    @Test
    public void testEmptyList() {
        List<InventoryCost> inventoryCosts = Arrays.asList();
        Map<String, BigDecimal> expected = new HashMap<>();

        Assert.assertEquals(expected, AvarageCostCalculator.calculate(inventoryCosts));
    }
}
