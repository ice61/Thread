package day07.code_5;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Main {

    public static void main(String[] args) {
        //创建一个图
        ConcurrentSkipListMap<String, Contact> map =
                new ConcurrentSkipListMap<>();
        //创建数组
        Thread[] threads = new Thread[26];
        int counter = 0;
        //循环26次
        for (char i = 'A'; i <= 'Z'; i++) {
            //创建任务，id为此次循环中的英文字母
            Task task = new Task(map, String.valueOf(i));
            //填充数组
            threads[counter] = new Thread(task);
            //开启线程
            threads[counter++].start();
        }
        //等待线程运行完毕
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //打印图中键值对的数量
        System.out.printf("Main: Size of the map: %d\n",
                map.size());
        //声明变量
        Map.Entry<String, Contact> element;
        Contact contact;

        //获取第一个键值对并打印联系方式中的姓名和号码
        element = map.firstEntry();
        contact = element.getValue();
        System.out.printf("Main: First Entry: %s: %s\n",
                contact.getName(), contact.getPhone());

        //获取最后一个键值对并打印联系方式中的姓名和号码
        element = map.lastEntry();
        contact = element.getValue();
        System.out.printf("Main: Last Entry: %s: %s\n",
                contact.getName(), contact.getPhone());

        //获取键在A1996和B1002之间的所有键值对
        System.out.printf("Main: Submap from A1996 to B1002: \n");
        ConcurrentNavigableMap<String, Contact> subMap = map.subMap("A1996", "B1002");
        //遍历获取到的键值对并打印相关信息
        do {
            element = subMap.pollFirstEntry();
            if (element != null) {
                contact = element.getValue();
                System.out.printf("%s: %s\n", contact.getName(), contact.getPhone());
            }
        } while (element != null);
    }

}
