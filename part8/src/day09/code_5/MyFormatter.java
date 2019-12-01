package day09.code_5;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        //创建字符串构造器
        StringBuilder sb = new StringBuilder();
        //拼接日志级别
        sb.append("[" + record.getLevel() + "] - ");
        //拼接日志生成时间
        sb.append(new Date(record.getMillis()) + " ");
        //拼接产生日志的类名和方法名
        sb.append(record.getSourceClassName() + " . " + record.getSourceMethodName());
        //拼接日志信息和换行符
        sb.append(" " + record.getMessage() + "\n");
        //返回相应的字符串
        return sb.toString();
    }
}
