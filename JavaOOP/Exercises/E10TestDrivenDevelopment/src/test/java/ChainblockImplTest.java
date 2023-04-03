package E10TestDrivenDevelopment.src.test.java;

import E10TestDrivenDevelopment.src.main.java.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ChainblockImplTest {

    Chainblock database;

    @Before
    public void SetUp() {
        database = new ChainblockImpl();

    }

    @Test
    public void testAddCorrectTransaction() {
        assertEquals(0, this.database.getCount());
        Transaction transaction1 = addTransaction1();
        assertEquals(1, this.database.getCount());
        Assert.assertTrue(database.contains(transaction1.getId()));
        Assert.assertTrue(database.contains(transaction1));
    }

    @Test
    public void testAddExistingTransaction() {
        Transaction transaction1 = addTransaction1();
        assertEquals(1, this.database.getCount());
        database.add(transaction1);
        assertEquals(1, this.database.getCount());
    }

    @Test
    public void testChangeTransactionStatusShouldChangeTransactionStatus() {
        Transaction transaction1 = addTransaction1();
        this.database.changeTransactionStatus(transaction1.getId(), TransactionStatus.ABORTED);
        Transaction expected = new TransactionImpl(1, TransactionStatus.ABORTED, "Desi", "Stoyan", 150.90);
        assertTrue(database.contains(expected));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionStatusShouldThrowIfNonSuchTransaction() {
        addTransaction1();
        this.database.changeTransactionStatus(200, TransactionStatus.UNAUTHORIZED);
    }

    @Test
    public void testRemoveTransactionByIdShouldRemoveTransaction() {
        Transaction transaction1 = addTransaction1();
        Transaction transaction2 = addTransaction2();
        assertEquals(2, this.database.getCount());

        database.removeTransactionById(2);
        assertEquals(1, this.database.getCount());
        assertFalse(database.contains(2));
    }

    @Test
    public void testGetByIdShouldReturnTheCorrectTransaction() {
        Transaction transaction1 = addTransaction1();
        Transaction expected = new TransactionImpl(1, TransactionStatus.UNAUTHORIZED, "Desi", "Stoyan", 150.90);
        assertEquals(expected, database.getById(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdShouldThrowIfNonSuchTransaction() {
        addTransaction1();
        this.database.getById(200);
    }

    @Test
    public void testGetByTransactionStatusShouldReturnACorrectIterable() {
        Transaction transaction1 = addTransaction1();
        Transaction transaction2 = addTransaction2();
        Transaction transaction3 = addTransaction3();

        Iterable<Transaction> result = database.getByTransactionStatus(TransactionStatus.SUCCESSFUL);

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);

        List<Transaction> expected = new ArrayList<>(
                Arrays.asList(transaction3, transaction2)
        );

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusShouldThrowIfNonSuchTransactions() {
        addTransaction1();
        addTransaction2();
        addTransaction3();
        this.database.getByTransactionStatus(TransactionStatus.FAILED);
    }

    @Test
    public void testGetAllSendersWithTransactionStatusShouldReturnACorrectIterable() {
        Transaction transaction1 = addTransaction1();
        Transaction transaction2 = addTransaction2();
        Transaction transaction3 = addTransaction3();

        Iterable<String> result = database.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);

        List<String> actual = new ArrayList<>();
        result.forEach(actual::add);

        List<String> expected = new ArrayList<>(
                Arrays.asList(transaction3.getFrom(), transaction2.getFrom())
        );

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllSendersWithTransactionStatusShouldThrowIfNonSuchTransactions() {
        addTransaction1();
        addTransaction2();
        addTransaction3();
        this.database.getAllSendersWithTransactionStatus(TransactionStatus.FAILED);
    }

    @Test
    public void testGetAllReceiversWithTransactionStatusShouldReturnACorrectIterable() {
        Transaction transaction1 = addTransaction1();
        Transaction transaction2 = addTransaction2();
        Transaction transaction3 = addTransaction3();

        Iterable<String> result = database.getAllReceiversWithTransactionStatus(TransactionStatus.SUCCESSFUL);

        List<String> actual = new ArrayList<>();
        result.forEach(actual::add);

        List<String> expected = new ArrayList<>(
                Arrays.asList(transaction3.getTo(), transaction2.getTo())
        );

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllReceiversWithTransactionStatusShouldThrowIfNonSuchTransactions() {
        addTransaction1();
        addTransaction2();
        addTransaction3();
        this.database.getAllReceiversWithTransactionStatus(TransactionStatus.FAILED);
    }

    @Test
    public void testGetAllOrderedByAmountDescendingThenByIdShouldReturnACorrectData() {
        Transaction transaction1 = addTransaction1();
        Transaction transaction2 = addTransaction2();
        Transaction transaction3 = addTransaction3();
        Transaction transaction4 = addTransaction4();

        Iterable<Transaction> result = database.getAllOrderedByAmountDescendingThenById();

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);

        List<Transaction> expected = new ArrayList<>(
                Arrays.asList(transaction3, transaction1, transaction4, transaction2)
        );

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testGetBySenderOrderedByAmountDescendingShouldReturnCorrectData() {
        Transaction transaction1 = addTransaction1();
        Transaction transaction2 = addTransaction2();
        Transaction transaction3 = addTransaction3();
        Transaction transaction4 = addTransaction4();

        Iterable<Transaction> result = database.getBySenderOrderedByAmountDescending("Desi");

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);

        List<Transaction> expected = new ArrayList<>(
                Arrays.asList(transaction3, transaction1)
        );

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderOrderedByAmountDescendingShouldThrowIfNonSuchTransactions() {
        Transaction transaction1 = addTransaction1();
        Transaction transaction2 = addTransaction2();
        Transaction transaction3 = addTransaction3();
        Transaction transaction4 = addTransaction4();

        database.getBySenderOrderedByAmountDescending("Anonymous");
    }

    @Test
    public void testGetByReceiverOrderedByAmountThenByIdShouldReturnCorrectData() {
        Transaction transaction1 = addTransaction1();//Stoyan 150.90
        Transaction transaction2 = addTransaction2();//Stoyan 99.80
        Transaction transaction3 = addTransaction3();
        Transaction transaction4 = addTransaction4();//Stoyan 150.90

        Iterable<Transaction> result = database.getByReceiverOrderedByAmountThenById("Stoyan");

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);

        List<Transaction> expected = new ArrayList<>(
                Arrays.asList(transaction1, transaction4, transaction2)
        );

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverOrderedByAmountThenByIdShouldThrowIfNonSuchTransactions() {
        Transaction transaction1 = addTransaction1();
        Transaction transaction2 = addTransaction2();
        Transaction transaction3 = addTransaction3();
        Transaction transaction4 = addTransaction4();

        database.getByReceiverOrderedByAmountThenById("Anonymous");
    }

    @Test
    public void testGetByTransactionStatusAndMaximumAmountShouldReturnCorrectData() {
        Transaction transaction1 = addTransaction1();//UNAUTHORIZED 150.90
        Transaction transaction2 = addTransaction2();//SUCCESSFUL 99.80
        Transaction transaction3 = addTransaction3();//SUCCESSFUL 350.60
        Transaction transaction4 = addTransaction4();//UNAUTHORIZED 150.90
        Transaction transaction5 = addTransaction5();//SUCCESSFUL 500.30

        Iterable<Transaction> result = database.getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL, 350.60);

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);

        List<Transaction> expected = new ArrayList<>(
                Arrays.asList(transaction3, transaction2)
        );

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testGetByTransactionStatusAndMaximumAmountShouldReturnNullIfNoMatchingTransaction() {
        Transaction transaction1 = addTransaction1();//UNAUTHORIZED 150.90
        Transaction transaction2 = addTransaction2();//SUCCESSFUL 99.80

        Iterable<Transaction> result = database.getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL, 10.00);

        assertFalse(result.iterator().hasNext());
    }

    @Test
    public void testGetBySenderAndMinimumAmountDescendingShouldReturnCorrectData() {
        Transaction transaction1 = addTransaction1();//Desi UNAUTHORIZED 150.90
        Transaction transaction2 = addTransaction2();//Pesho SUCCESSFUL 99.80
        Transaction transaction3 = addTransaction3();//Desi SUCCESSFUL 350.60
        Transaction transaction4 = addTransaction4();//Alexander UNAUTHORIZED 150.90
        Transaction transaction5 = addTransaction5();//Desi SUCCESSFUL 500.30

        Iterable<Transaction> result = database.getBySenderAndMinimumAmountDescending("Desi", 350.59);

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);

        List<Transaction> expected = new ArrayList<>(
                Arrays.asList(transaction5, transaction3)
        );

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderAndMinimumAmountDescendingShouldThrowIfNoSuchTransactions() {
        Transaction transaction1 = addTransaction1();//Desi UNAUTHORIZED 150.90
        Transaction transaction2 = addTransaction2();//Pesho SUCCESSFUL 99.80
        Transaction transaction3 = addTransaction3();//Desi SUCCESSFUL 350.60
        Transaction transaction4 = addTransaction4();//Alexander UNAUTHORIZED 150.90
        Transaction transaction5 = addTransaction5();//Desi SUCCESSFUL 500.30

        Iterable<Transaction> result = database.getBySenderAndMinimumAmountDescending("Anonymous", 350.59);
    }

    @Test
    public void testGetByReceiverAndAmountRangeShouldReturnCorrectData() {
        Transaction transaction1 = addTransaction1();//Desi Stoyan UNAUTHORIZED 150.90
        Transaction transaction2 = addTransaction2();//Pesho Stoyan SUCCESSFUL 99.80
        Transaction transaction3 = addTransaction3();//Desi Ivan SUCCESSFUL 350.60
        Transaction transaction4 = addTransaction4();//Alexander Stoyan UNAUTHORIZED 150.90
        Transaction transaction5 = addTransaction5();//Desi Stoyan SUCCESSFUL 500.30

        Iterable<Transaction> result = database.getByReceiverAndAmountRange("Stoyan", 150.90, 500.30);

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);

        List<Transaction> expected = new ArrayList<>(
                Arrays.asList(transaction1, transaction4)
        );

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndAmountRangeShouldThrowIfNoSuchTransactions() {
        Transaction transaction1 = addTransaction1();//Desi Stoyan UNAUTHORIZED 150.90
        Transaction transaction2 = addTransaction2();//Pesho Stoyan SUCCESSFUL 99.80
        Transaction transaction3 = addTransaction3();//Desi Ivan SUCCESSFUL 350.60
        Transaction transaction4 = addTransaction4();//Alexander Stoyan UNAUTHORIZED 150.90
        Transaction transaction5 = addTransaction5();//Desi Georgi SUCCESSFUL 500.30

        Iterable<Transaction> result = database.getByReceiverAndAmountRange("Stoyan", 600.00, 1000.00);
    }

    @Test
    public void testGetAllInAmountRangeShouldReturnCorrectData() {
        Transaction transaction1 = addTransaction1();//Desi Stoyan UNAUTHORIZED 150.90
        Transaction transaction2 = addTransaction2();//Pesho Stoyan SUCCESSFUL 99.80
        Transaction transaction3 = addTransaction3();//Desi Ivan SUCCESSFUL 350.60
        Transaction transaction4 = addTransaction4();//Alexander Stoyan UNAUTHORIZED 150.90
        Transaction transaction5 = addTransaction5();//Desi Stoyan SUCCESSFUL 500.30

        Iterable<Transaction> result = database.getAllInAmountRange(150.90, 350.60);

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);

        List<Transaction> expected = new ArrayList<>(
                Arrays.asList(transaction1, transaction3, transaction4)
        );

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void testGetAllInAmountRangeShouldReturnEmptyIterableIfNoTransactionsWithAmountRange() {
        Transaction transaction1 = addTransaction1();//Desi Stoyan UNAUTHORIZED 150.90
        Transaction transaction2 = addTransaction2();//Pesho Stoyan SUCCESSFUL 99.80
        Transaction transaction3 = addTransaction3();//Desi Ivan SUCCESSFUL 350.60
        Transaction transaction4 = addTransaction4();//Alexander Stoyan UNAUTHORIZED 150.90
        Transaction transaction5 = addTransaction5();//Desi Stoyan SUCCESSFUL 500.30

        Iterable<Transaction> result = database.getAllInAmountRange(10, 20);
        assertFalse(result.iterator().hasNext());
    }

    @Test
    public void testIteratorShouldIterateCorrectlyOnTransactions() {
        Transaction transaction1 = addTransaction1();//Desi Stoyan UNAUTHORIZED 150.90
        Transaction transaction2 = addTransaction2();//Pesho Stoyan SUCCESSFUL 99.80
        Transaction transaction3 = addTransaction3();//Desi Ivan SUCCESSFUL 350.60
        Transaction transaction4 = addTransaction4();//Alexander Stoyan UNAUTHORIZED 150.90
        Transaction transaction5 = addTransaction5();//Desi Stoyan SUCCESSFUL 500.30

        List<Transaction> expected = new ArrayList<>(
                Arrays.asList(transaction1, transaction2, transaction3, transaction4, transaction5));

        int index = 0;
        for (Transaction transaction : database) {
            assertEquals(expected.get(index++), transaction);
        }
    }

    //======================================================//

    private Transaction addTransaction1() {
        Transaction transaction1 = new TransactionImpl(1, TransactionStatus.UNAUTHORIZED, "Desi", "Stoyan", 150.90);
        database.add(transaction1);
        return transaction1;
    }

    private Transaction addTransaction2() {
        Transaction transaction2 = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Pesho", "Stoyan", 99.80);
        database.add(transaction2);
        return transaction2;
    }

    private Transaction addTransaction3() {
        Transaction transaction3 = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Desi", "Ivan", 350.60);
        database.add(transaction3);
        return transaction3;
    }

    private Transaction addTransaction4() {
        Transaction transaction4 = new TransactionImpl(4, TransactionStatus.UNAUTHORIZED, "Alexander", "Stoyan", 150.90);
        database.add(transaction4);
        return transaction4;
    }

    private Transaction addTransaction5() {
        Transaction transaction5 = new TransactionImpl(5, TransactionStatus.SUCCESSFUL, "Desi", "Stoyan", 500.30);
        database.add(transaction5);
        return transaction5;
    }


}


