package E10TestDrivenDevelopment.src.main.java;

import java.util.Comparator;
import java.util.Objects;

public class TransactionImpl implements Comparable<TransactionImpl>, Transaction {

    private int id;
    private TransactionStatus status;
    private String from;
    private String to;
    private double amount;

    public TransactionImpl(int id, TransactionStatus status, String from, String to, double amount) {
        this.id = id;
        this.status = status;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public TransactionStatus getStatus() {
        return status;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public int compareTo(TransactionImpl other) {
        return Double.compare(other.getAmount(), this.getAmount());
    }

    @Override
    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof TransactionImpl)) return false;
        TransactionImpl that = (TransactionImpl) other;
        return getId() == that.getId() && Double.compare(that.getAmount(), getAmount()) == 0 && getStatus() == that.getStatus() && getFrom().equals(that.getFrom()) && getTo().equals(that.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getFrom(), getTo(), getAmount());
    }
}
