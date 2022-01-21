package day05;

public class TransferPerClient {

    private String clientId;
    private int sum;
    private int numberOfTransaction;

    public TransferPerClient(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public int getSum() {
        return sum;
    }

    public int getNumberOfTransactions() {
        return numberOfTransaction;
    }

    public void increase(int amount) {
        sum += amount;
        numberOfTransaction++;
    }

    public void decrease(int amount) {
        sum -= amount;
        numberOfTransaction++;
    }

    @Override
    public String toString() {
        return "TransferPerClient{" +
                "clientId='" + clientId + '\'' +
                ", sum=" + sum +
                ", numberOfTransaction=" + numberOfTransaction +
                '}';
    }
}