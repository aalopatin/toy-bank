package ru.aal;

public class BackEndSystem {

    private int balance = 1000000;
    private final String SUCCESS = "Бэк система: %s УСПЕШНО ВЫПОЛНЕНА. Получена от %s. Баланс банка: %d\n";
    private final String FAILURE = "Бэк система: %s НЕ ВЫПОЛНЕНА. Получена от %s. " +
                                    "Сумма больше баланса банка. Баланс банка: %d\n";


    public synchronized void executeRequest(Request request) throws InterruptedException {

        switch (request.getType()) {
            case CREDIT:
                if(balance >= request.getAmount()) {
                    balance -= request.getAmount();
                    System.out.printf(SUCCESS,
                            request,
                            Thread.currentThread().getName(),
                            balance);
                } else {
                    System.out.printf(FAILURE,
                                    request,
                                    Thread.currentThread().getName(),
                                    balance);
                }

                break;
            case REPAYMENT:
                balance += request.getAmount();
                System.out.printf(SUCCESS,
                        request,
                        Thread.currentThread().getName(),
                        balance);
                notifyAll();
                break;

        }
    }
}
