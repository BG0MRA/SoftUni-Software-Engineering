package L10TestDrivenDevelopment.src.test.java;

import L10TestDrivenDevelopment.src.main.java.Instock;
import L10TestDrivenDevelopment.src.main.java.Product;
import L10TestDrivenDevelopment.src.main.java.ProductStock;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InstockTest {

    private ProductStock instock;
    private Product product;

    @Before
    public void setUp() {
        this.instock = new Instock();
        this.product = new Product("test_product", 13.00, 10);
    }

    @Test
    public void testAddInStockShouldContainThatProduct() {
        instock.add(product);
        assertTrue(instock.contains(product));
    }

    @Test
    public void testContainsShouldReturnFalseWhenProductMissing() {
        assertFalse(instock.contains(product));
    }

    @Test
    public void testCountShouldReturnTheCorrectNumberOfProducts() {
        assertEquals(0, instock.getCount());
        instock.add(product);
        assertEquals(1, instock.getCount());
        instock.add(new Product("test_two", 10.00, 13));
        assertEquals(2, instock.getCount());
    }

    @Test
    public void testFindShouldReturnTheCorrectNthProduct() {
        List<Product> products = addMultipleProducts();

        int productIndex = 3;

        Product expectedProduct = products.get(productIndex);

        Product actualProduct = instock.find(productIndex);

        assertNotNull(actualProduct);
        assertEquals(expectedProduct.getLabel(), actualProduct.getLabel());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindWithIndexOutOfRangeShouldThrow() {
        List<Product> products = addMultipleProducts();
        instock.find(products.size());
    }

    @Test
    public void testChangeQuantityShouldUpdateTheProductQuantity() {
        instock.add(product);
        int expectedQuantity = product.getQuantity() + 10;

        instock.changeQuantity(product.getLabel(), expectedQuantity);

        assertEquals(expectedQuantity, product.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityShouldFailIfProductWithLabelIsMissing() {
        instock.changeQuantity("missing_label", 13);
    }

    @Test
    public void testFindByLabelShouldReturnTheProductWithTheSameLabel() {
        List<Product> products = addMultipleProducts();
        instock.add(product);

        Product actualProduct = instock.findByLabel(product.getLabel());

        assertNotNull(actualProduct);
        assertEquals(product.getLabel(), actualProduct.getLabel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelShouldFailWhenProductWithLabelIsMissing() {
        instock.findByLabel("missing_label" );
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnCorrectNumberOfProducts() {
        List<Product> products = addMultipleProducts();
        int expectedCount = 3;
        Iterable<Product> iterable = instock.findFirstByAlphabeticalOrder(expectedCount);
        List<Product> actual = iterableToList(iterable);
        assertEquals(expectedCount, actual.size());
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnTheProductsOrderedByLabel() {
        List<Product> products = addMultipleProducts()
                .stream()
                .sorted(Comparator.comparing(Product::getLabel))
                .collect(Collectors.toList());

        int expectedCount = products.size();

        List<Product> actual = iterableToList(instock.findFirstByAlphabeticalOrder(expectedCount));
        assertEquals(expectedCount, actual.size());

        for (int i = 0; i < products.size(); i++) {
            String expectedLabel = products.get(i).getLabel();
            String actualLabel = actual.get(i).getLabel();
            assertEquals(expectedLabel, actualLabel);
        }
    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnEmptyCollectionWhenNotEnoughProducts() {
        int size = addMultipleProducts().size();

        List<Product> products = iterableToList(instock.findFirstByAlphabeticalOrder(size + 1));

        assertEquals(0, products.size());

    }

    @Test
    public void testFindFirstByAlphabeticalOrderShouldReturnEmptyCollectionWhenCountIsZero() {
        addMultipleProducts();

        List<Product> products = iterableToList(instock.findFirstByAlphabeticalOrder(0));

        assertEquals(0, products.size());

    }

    //
    @Test
    public void testFindAllInRangeShouldReturnTheCorrectRange() {
        final double beginRange = 2.00;
        final double endRange = 13.00;

        List<Product> products = addMultipleProducts().stream()
                .filter(p -> p.getPrice() > beginRange && p.getPrice() <= endRange)
                .collect(Collectors.toList());

        Iterable<Product> iterable = instock.findAllInRange(beginRange, endRange);
        List<Product> actual = iterableToList(iterable);

        assertEquals(products.size(), actual.size());

        boolean hasNoOutOfRangePrices = actual.stream()
                .map(Product::getPrice)
                .noneMatch(p -> p <= beginRange || p > endRange);

        assertTrue(hasNoOutOfRangePrices);
    }

    @Test
    public void testFindAllInRangeShouldReturnOrderedProductsOrderedByPriceDescending() {
        final double beginRange = 2.00;
        final double endRange = 13.00;

        List<Product> expected = addMultipleProducts().stream()
                .filter(p -> p.getPrice() > beginRange && p.getPrice() <= endRange)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());

        List<Product> actual = iterableToList(instock.findAllInRange(beginRange, endRange));
        assertEquals(expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            double expectedPrice = expected.get(i).getPrice();
            double actualPrice = actual.get(i).getPrice();

            assertEquals(expectedPrice, actualPrice, 0.00);
        }
    }

    @Test
    public void testFindAllInRangeShouldReturnEmptyCollectionWhenThereIsNoPriceInRange() {
        final double beginRange = 0.00;
        final double endRange = 1.00;
        List<Product> products = iterableToList(instock.findAllInRange(beginRange, endRange));
        assertEquals(0, products.size());

    }

    @Test
    public void testFindAllByPriceShouldReturnMatchingPriceProducts() {
        addMultipleProducts();

        double expectedPrice = 5.00;
        List<Product> products = iterableToList(instock.findAllByPrice(expectedPrice));

        for (Product actual : products) {
            assertEquals(expectedPrice, actual.getPrice(), 0.00);
        }
    }

    @Test
    public void testFindAllByPriceShouldReturnEmptyCollectionWhenNoMatches() {
        addMultipleProducts();

        double expectedPrice = -1.00;
        List<Product> products = iterableToList(instock.findAllByPrice(expectedPrice));

        assertEquals(0, products.size());
    }

    @Test
    public void testFindFirstMostExpensiveProductsShouldReturnTheCorrectMostExpensiveProducts() {
        int productsToTake = 5;

        List<Product> expectedProducts = addMultipleProducts().stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(productsToTake)
                .collect(Collectors.toList());

        List<Product> actualProducts = iterableToList(instock.findFirstMostExpensiveProducts(productsToTake));

        assertEquals(actualProducts.size(), expectedProducts.size());

        for (int i = 0; i < expectedProducts.size(); i++) {
            double actualPrice = actualProducts.get(i).getPrice();
            double expectedPrice = expectedProducts.get(i).getPrice();

            assertEquals(actualPrice, expectedPrice, 0.00);

        }


    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindFirstMostExpensiveProductsShouldThrowsWhenThereAreLessProductsInStock() {
        int size = addMultipleProducts().size();

        instock.findFirstMostExpensiveProducts(size + 1);

    }

    @Test
    public void testFindAllByQuantityShouldReturnMatchingProducts() {
        addMultipleProducts();

        int expectedQuantity = 7;
        List<Product> products = iterableToList(instock.findAllByQuantity(expectedQuantity));

        for (Product actual : products) {
            assertEquals(expectedQuantity, actual.getQuantity());
        }
    }

    @Test
    public void testFindAllByQuantityShouldReturnEmptyCollectionWhenNoneMatches() {
        addMultipleProducts();
        List<Product> products = iterableToList(instock.findAllByQuantity(-10));
        assertEquals(0, products.size());
    }

    @Test
    public void testIteratorShouldReturnAllTheProductsInStock () {
        List<Product> expected = addMultipleProducts();

        Iterator<Product> iterator = instock.iterator();

        List<Product> actual = new ArrayList<>();
        iterator.forEachRemaining(actual::add);

        assertEquals(expected,actual);
    }

    private List<Product> addMultipleProducts() {
        List<Product> products = List.of(
                new Product("Label_2", 3.00, 1),
                new Product("Label_1", 5.00, 7),
                new Product("Label_4", 2.00, 7),
                new Product("Label_3", 5.00, 7),
                new Product("Label_7", 21.00, 5),
                new Product("Label_5", 3.00, 7),
                new Product("Label_6", 13.00, 8),
                new Product("Label_8", 7.00, 13)
        );

        products.forEach(instock::add);

        return products;
    }

    private List<Product> iterableToList(Iterable<Product> iterable) {
        assertNotNull(iterable);
        List<Product> products = new ArrayList<>();
        for (Product product : iterable) {
            products.add(product);
        }
        return products;
    }

}