package ru.aal;

public class Client extends Thread{

    private RequestType type;
    private int amount;
    private FrontEndSystem frontEndSystem;

    public Client(String name, RequestType type, int amount, FrontEndSystem frontEndSystem) {
        super(name);
        this.type = type;
        this.amount = amount;
        this.frontEndSystem = frontEndSystem;
    }

    @Override
    public void run() {
        try {
            Request request = new Request(this.getName(), amount, type);
            frontEndSystem.addRequest(request);
            System.out.printf("%s: %s was sent to the bank\n", getName(), request);
        } catch (InterruptedException e) {

        }
    }
}
