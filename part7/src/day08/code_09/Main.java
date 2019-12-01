package day08.code_09;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        MyPriorityTransferQueue<Event> buffer = new MyPriorityTransferQueue<>();
        Producer producer = new Producer(buffer);
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(producer);
            threads[i].start();
        }
        Consumer consumer = new Consumer(buffer);
        Thread thread = new Thread(consumer);
        thread.start();
        System.out.printf("Main: Buffer: Consumer count: %d\n",
                buffer.getWaitingConsumerCount());
        Event myEvent = new Event("Core Event", 0);
        buffer.transfer(myEvent);
        System.out.println("Main: My Event has been transfered");
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("Main: Buffer: Consumer count: %d\n",
                buffer.getWaitingConsumerCount());
        myEvent = new Event("Core Event 2", 0);
        buffer.transfer(myEvent);
        thread.join();
        System.out.println("Main: End of the program");
    }

}
