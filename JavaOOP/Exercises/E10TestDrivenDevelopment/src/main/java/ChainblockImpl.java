package E10TestDrivenDevelopment.src.main.java;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {

    public ChainblockImpl() {
        this.transactionMap = new LinkedHashMap<>();
    }

    private Map<Integer, Transaction> transactionMap;

    @Override
    public int getCount() {
        return this.transactionMap.size();
    }

    @Override
    public void add(Transaction transaction) {
        transactionMap.putIfAbsent(transaction.getId(), transaction);
    }

    @Override
    public boolean contains(Transaction transaction) {
        return transactionMap.containsValue(transaction);
    }

    @Override
    public boolean contains(int id) {
        return transactionMap.containsKey(id);
    }

    @Override
    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        ensureTransaction(id);

        transactionMap.get(id).setStatus(newStatus);
    }

    @Override
    public void removeTransactionById(int id) {
        ensureTransaction(id);

        transactionMap.remove(id);
    }

    @Override
    public Transaction getById(int id) {
        ensureTransaction(id);

        return transactionMap.get(id);
    }

    @Override
    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> result = getSortedListOfTransactionsFilteredBy(t -> t.getStatus().equals(status));

        ensureListOfTransactionsIsNotEmpty(status, result);

        return result;
    }

    @Override
    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<String> result = getSortedListOf(status, Transaction::getFrom);

        ensureListOfTransactionsIsNotEmpty(status, result);

        return result;
    }

    @Override
    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<String> result = getSortedListOf(status, Transaction::getTo);

        ensureListOfTransactionsIsNotEmpty(status, result);

        return result;
    }

    @Override
    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        List<Transaction> result = getTransactionListSortedBy(
                getComparatorFirsAmountDescendingThanId());

        return result;
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> result = getSortedListOfTransactionsFilteredBy(t -> t.getFrom().equals(sender));

        ensureListOfTransactionsIsNotEmpty(sender, result);

        return result;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> result = getTransactionListSortedBy(getComparatorFirsAmountDescendingThanId())
                .stream()
                .filter(t -> t.getTo().equals(receiver))
                .collect(Collectors.toList());

        ensureListOfTransactionsIsNotEmpty(receiver, result);

        return result;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount
            (TransactionStatus status, double amount) {

        return getSortedListOfTransactionsFilteredBy
                (t -> t.getStatus().equals(status) && t.getAmount() <= amount);
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> result = getSortedListOfTransactionsFilteredBy
                (t -> t.getFrom().equals(sender) && t.getAmount() > amount);

        if (result.isEmpty()) {
            throw new IllegalArgumentException(String.format("There are no transactions with sender %s and amount over %.2f", sender, amount));
        }

        return result;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<Transaction> result = getTransactionListSortedBy(getComparatorFirsAmountDescendingThanId())
                .stream()
                .filter(t -> t.getTo().equals(receiver)
                        && t.getAmount() >= lo && t.getAmount() < hi)
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("There are no transactions with receiver %s " +
                            "and amount between %.2f and %.2f", receiver, lo, hi));
        }

        return result;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return transactionMap.values()
                .stream()
                .filter(t -> t.getAmount() >= lo && t.getAmount() <= hi)
                .collect(Collectors.toList());
    }

    public Iterator<Transaction> iterator() {
        return new Iterator<Transaction>() {
            List<Transaction> transactionList = new ArrayList<>(transactionMap.values());

            int index = 0;

            @Override
            public boolean hasNext() {
                return index > transactionList.size()-1;
            }

            @Override
            public Transaction next() {
                return transactionList.get(index);
            }
        };
    }

    //==========additional methods:=============

    private List<String> getSortedListOf(TransactionStatus status, Function<Transaction, String> mapper) {
        return transactionMap.values()
                .stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted()
                .map(mapper)
                .collect(Collectors.toList());
    }

    private List<Transaction> getSortedListOfTransactionsFilteredBy(Predicate<Transaction> predicate) {
        return transactionMap.values()
                .stream()
                .filter(predicate)
                .sorted()
                .collect(Collectors.toList());
    }

    private List<Transaction> getTransactionListSortedBy(Comparator<Transaction> transactionComparator) {
        List<Transaction> result = transactionMap.values()
                .stream()
                .sorted(transactionComparator)
                .collect(Collectors.toList());

        return result;
    }

    private Comparator<Transaction> getComparatorFirsAmountDescendingThanId() {
        return Comparator.comparing(Transaction::getAmount)
                .reversed()
                .thenComparing(Transaction::getId);
    }

    private void ensureTransaction(int id) {
        if (!this.contains(id)) {
            throw new IllegalArgumentException("Transaction with id: " + id + " transaction doesn't exist!");
        }
    }

    private <T, E> void ensureListOfTransactionsIsNotEmpty(T argument, List<E> result) {
        if (result.isEmpty()) {
            throw new IllegalArgumentException("There are no matching transactions for argument: " + argument.toString());
        }
    }
}
