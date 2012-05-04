package bof_java8.t02;

import org.fest.assertions.Delta;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class TotalTest {

    private static final Delta DELTA = Delta.delta(1e-7);

    @Test
    public void should_get_total_from_products() {
        Iterable<Product> products = Arrays.asList(
                new Product("aaa", 10.0, 1),
                new Product("bbb", 5.0, 2),
                new Product("ccc", 2.0, 5)
        );

        double total = getTotal_1(products);

        assertThat(total).isEqualTo(30.0, DELTA);
    }

    private double getTotal_1(Iterable<Product> products) {
        double total = 0.0; // initialization

        for (Product product : products) {
            total = total + product.getUnitPrice() * product.getQuantity();
        }

        return total;
    }

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

}
