package day07.code_2;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        //创建一个列表
        LinkedBlockingDeque<String> list = new LinkedBlockingDeque<>();
        //创建客户端对象
        Client client = new Client(list);
        //创建线程并开启
        Thread thread = new Thread(client);
        thread.start();
        //每300毫秒从列表中取出5条数据，循环三次
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                String request = null;
                try {
                    //返回并删除列表第一个元素
                    request = list.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //打印相关信息
                System.out.printf("Main: Request: %s at %s. Size: %d\n",
                        request, new Date(), list.size());
            }
            //休眠300毫秒
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //打印程序结束提示语
        System.out.println("Main: End of the program");
    }

}
