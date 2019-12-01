package day07.code_5;

import java.util.concurrent.ConcurrentSkipListMap;

public class Task implements Runnable {

    //图
    private ConcurrentSkipListMap<String,Contact> map;

    //任务id
    private String id;

    public Task(ConcurrentSkipListMap<String, Contact> map, String id) {
        this.map = map;
        this.id = id;
    }

    @Override
    public void run() {
        //向图中装填1000个联系方式
        for (int i = 0; i < 1000; i++) {
            //联系方式中的name为id，号码为循环次数+1000
            Contact contact = new Contact(id, String.valueOf(i + 1000));
            //图的键为任务id+电话号码，值为联系方式对象
            map.put(id+contact.getPhone(),contact);
        }
    }
}
