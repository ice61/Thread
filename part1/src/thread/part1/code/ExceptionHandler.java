package thread.part1.code;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //打印提示语
        System.out.printf("An exception has been captured\n");
        //打印出现异常的线程id
        System.out.printf("Thread: %s\n",t.getId());
        //打印异常类和异常信息
        System.out.printf("Exception: %s: %s\n",e.getClass().getName(),e.getMessage());
        //打印堆栈记录信息
        System.out.printf("Stack Trace: \n");
        e.printStackTrace(System.out);
        //打印线程状态
        System.out.printf("Thread status: %s\n",t.getState());
    }
}
