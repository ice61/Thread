package day03.code_6;

import day03.code_4.Job;

public class Main {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Job job = new Job(printQueue);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(job,"Thread" + i);
            thread.start();
        }
    }

}
