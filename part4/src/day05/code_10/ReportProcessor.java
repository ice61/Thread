package day05.code_10;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ReportProcessor implements Runnable {

    //CompletionService对象
    private CompletionService<String> service;

    //线程是否跳出死循环的标志
    private boolean end;

    public void setEnd(boolean end) {
        this.end = end;
    }

    public ReportProcessor(CompletionService<String> service) {
        this.service = service;
        //end默认为false
        end = false;
    }

    @Override
    public void run() {
        //end不为true时，始终尝试取出Future对象
        while (!end) {
            try {
                //取出Future对象
                Future<String> result =
                        service.poll(20, TimeUnit.SECONDS);
                //如果成功取出
                if (result != null) {
                    //打印任务返回的结果
                    String report = result.get();
                    System.out.printf("ReportReceiver: Report Received:%s\n",
                            report);
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        //打印当前线程结束提示语
        System.out.println("ReportSender: End");
    }
}
