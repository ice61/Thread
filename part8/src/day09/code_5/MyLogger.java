package day09.code_5;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger {

    //处理器
    private static Handler handler;

    //用于获取日志生成器的静态方法
    public static Logger getLogger(String name) {
        //调用Logger类的静态方法获取日志生成器
        Logger logger = Logger.getLogger(name);
        //设置日志级别未ALL，输出一切等级的日志
        logger.setLevel(Level.ALL);
        try {
            //如果没有处理器
            if (handler == null) {
                //创建文档处理器关联相关文件
                handler = new FileHandler("src/day09/code_5/recipe8.log");
                //创建格式化工具并赋值给处理器
                MyFormatter format = new MyFormatter();
                handler.setFormatter(format);
            }
            //如果日志生成器没有处理器，则添加
            if (logger.getHandlers().length == 0) {
                logger.addHandler(handler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回日志生成器
        return logger;
    }

}
