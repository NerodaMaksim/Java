import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumerTask {
    private ArrayBlockingQueue<String> firstQueue;
    private ArrayBlockingQueue<String> secondQueue;
    private Thread[] arrT;

    ProducerConsumerTask(int capacity) {
        firstQueue = new ArrayBlockingQueue<>(capacity);
        secondQueue = new ArrayBlockingQueue<>(capacity);
    }


    public void doAwfulThings(int amountOfThreads, int firstGroupThreads, int messagesToOut) {
        arrT = new Thread[amountOfThreads];
        for (int i = 0; i < firstGroupThreads; i++) {
            arrT[i] = new Thread(
                    () -> {
                        while (true) {
                            try {
                                firstQueue.put("Поток № " + Thread.currentThread().getName() +
                                        " сгенерировал сообщение ...");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            arrT[i].setDaemon(true);
            arrT[i].start();
        }
        for (int i = firstGroupThreads; i < amountOfThreads; i++) {
            arrT[i] = new Thread(
                    () -> {
                        while (true)
                            if (firstQueue.size() > 0) {
                                try {
                                    secondQueue.put("Поток № " + Thread.currentThread().getName() +
                                            " переложил сообщение " + "\"" + firstQueue.poll() + "\'");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }

                    }
            );
            arrT[i].setDaemon(true);
            arrT[i].start();
        }
        while (true) {
            if (secondQueue.size() >= messagesToOut) {
                for (int i = 0; i < messagesToOut; i++)
                    System.out.println(secondQueue.poll());
                break;
            }
        }
    }

    boolean isAllTreadsDaemons() {
        int length = arrT.length,
            counterOfDaemons = 0;
        for (Thread t : arrT) {
            if(t.isDaemon())
                counterOfDaemons++;
        }
        if(counterOfDaemons == length)
            return true;
        else
            return false;
    }

    public static void main(String[] args) throws InterruptedException {
        int amountOfThreads = 7;
        int firstGroupThreads = 5;
        int messagesToOut = 100;
        ProducerConsumerTask task = new ProducerConsumerTask(200);
        task.arrT = new Thread[amountOfThreads];
        for (int i = 0; i < firstGroupThreads; i++) {
            task.arrT[i] = new Thread(
                    () -> {
                        while (true) {
                            try {
                                task.firstQueue.put("Поток № " + Thread.currentThread().getName() + " сгенерировал сообщение ...");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            task.arrT[i].setDaemon(true);
            task.arrT[i].start();
        }
        for (int i = firstGroupThreads; i < amountOfThreads; i++) {
            task.arrT[i] = new Thread(
                    () -> {
                        while (true)
                            if (task.firstQueue.size() > 0) {
                                try {
                                    task.secondQueue.put("Поток № " + Thread.currentThread().getName() + " переложил сообщение " + "\"" + task.firstQueue.poll() + "\'");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }

                    }
            );
            task.arrT[i].setDaemon(true);
            task.arrT[i].start();
        }
        while (true) {
            if (task.secondQueue.size() >= messagesToOut) {
                for (int i = 0; i < messagesToOut; i++)
                    System.out.println(task.secondQueue.poll());
                break;
            }
        }
    }
}

