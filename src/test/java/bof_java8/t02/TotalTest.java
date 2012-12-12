package bof_java8.t02;

import org.fest.assertions.data.Offset;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.offset;

/**
 * Aggregate the result of computations
 */
public class TotalTest {

    private static class Product {

        private final String name;

        private final double unitPrice;

        private final int quantity;

        public Product(String name, double unitPrice, int quantity) {
            this.name = name;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public int getQuantity() {
            return quantity;
        }
    }

    @Test
    public void should_get_total_from_products_imperative_way() {
        Collection<Product> basket = Arrays.asList(
                new Product("aaa", 10.0, 1),
                new Product("bbb", 5.0, 2),
                new Product("ccc", 2.0, 5)
        );

        double total = getTotal_imperative(basket);

        assertThat(total).isEqualTo(30.0, OFFSET);
    }

    private double getTotal_imperative(Collection<Product> products) {
        double total = 0.0; // initialization

        for (Product product : products) {
            total = total + product.getUnitPrice() * product.getQuantity();
        }

        return total;
    }

    @Test
    public void should_get_total_from_products_java8_way() {
        Collection<Product> basket = Arrays.asList(
                new Product("aaa", 10.0, 1),
                new Product("bbb", 5.0, 2),
                new Product("ccc", 2.0, 5)
        );

        double total = getTotal_java8(basket);

        assertThat(total).isEqualTo(30.0, OFFSET);
    }

    private double getTotal_java8(Collection<Product> products) {
        return 0.0;
    }

    private static final Offset<Double> OFFSET = offset(1e-7);

}
