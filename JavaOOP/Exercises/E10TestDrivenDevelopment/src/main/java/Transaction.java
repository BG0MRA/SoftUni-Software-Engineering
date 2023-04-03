package E10TestDrivenDevelopment.src.main.java;

public interface Transaction {
    int getId();

    TransactionStatus getStatus();

    String getFrom();

    String getTo();

    double getAmount();

    int compareTo(TransactionImpl o);

    void setStatus(TransactionStatus status);



}
