package ru.aal;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Random random = new Random();
    private final static FrontEndSystem FRONT_END_SYSTEM = new FrontEndSystem();
    private final static BackEndSystem BACK_END_SYSTEM = new BackEndSystem();
    private static volatile int addedCount = 0;
    private static volatile int completedCount = 0;

    public static void main(String[] args) {

        System.out.println("Введите количество клиентов (не меньше 4): ");
        Scanner scanner = new Scanner(System.in);
        int countClients = scanner.nextInt();
        countClients = countClients < 4 ? 4 : countClients;

        System.out.println("Введите количество обработчиков (не меньше 2): ");
        int countHandlers = scanner.nextInt();
        countHandlers = countHandlers < 2 ? 2 : countHandlers;

        for (int i = 1; i < countClients + 1; i++) {

            String name = "Client №" + i;
            int amount =  10_000 * (random.nextInt(100) + 1);
            RequestType type = RequestType.values()[random.nextInt(2)];
            Runnable  r = () -> {
                try {
                    Request request = new Request(name, amount, type);
                    FRONT_END_SYSTEM.addRequest(request);
                    addedCount++;
                } catch (InterruptedException e) {

                }
            };

            new Thread(r, name).start();

        }

        for (int i = 1; i < countHandlers + 1; i++) {

            String name = "Обработчик заявок №" + i;
            int finalCountClients = countClients;
            Runnable r = () -> {
                try {
                    while(completedCount < finalCountClients) {
                        Request request = FRONT_END_SYSTEM.pollRequest(addedCount < finalCountClients);
                        BACK_END_SYSTEM.executeRequest(request);
                        completedCount++;
                    }
                    throw new NoSuchElementException();
                } catch (NoSuchElementException e) {
                    System.out.printf("%s: Все заявки обработаны!\n", name);
                }
                catch (InterruptedException e) {

                }

            };

            new Thread(r, name).start();

        }

    }
}
