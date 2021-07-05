package ru.aal;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class FrontEndSystem {
    private Queue<Request> requests = new ArrayDeque<>();

    public synchronized void addRequest(Request request) throws InterruptedException {
        try {
            while (requests.size() == 2) {
                wait();
            }
            requests.add(request);
            System.out.printf("%s: %s отправлена в банк\n", request.getClientThreadName(), request);
        } finally {
            notifyAll();
        }

    }

    public synchronized Request pollRequest() throws InterruptedException {
        try {
            while (requests.isEmpty()) {
                wait();
            }
            Request request = requests.remove();
            System.out.printf("%s: получена заявка на обработку по клиенту - %s \n", Thread.currentThread().getName(), request.getClientThreadName());
            return request;
        } finally {
            notifyAll();
        }
    }

}
