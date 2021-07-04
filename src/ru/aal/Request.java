package ru.aal;

public class Request {
    private String clientThreadName;
    private int amount;
    private RequestType type;

    public Request(String clientThreadName, int amount, RequestType type) {
        this.clientThreadName = clientThreadName;
        this.amount = amount;
        this.type = type;
    }

    public String getClientThreadName() {
        return clientThreadName;
    }

    public void setClientThreadName(String clientThreadName) {
        this.clientThreadName = clientThreadName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Request{" +
                "clientThreadName='" + clientThreadName + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }
}
