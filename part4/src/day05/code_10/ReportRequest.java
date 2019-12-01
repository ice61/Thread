package day05.code_10;

import java.util.concurrent.CompletionService;

public class ReportRequest implements Runnable {

    private String name;

    private CompletionService<String> service;

    public ReportRequest(String name, CompletionService<String> service) {
        this.name = name;
        this.service = service;
    }

    @Override
    public void run() {
        //创建一个任务
        ReportGenerator reportGenerator = new ReportGenerator(name, "Report");
        //将其发送给CompletionService对象
        service.submit(reportGenerator);
    }
}
