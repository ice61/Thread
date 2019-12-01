package day05.code_04;

import java.util.concurrent.Callable;

public class TaskValidator implements Callable<String> {

    //用户验证类
    private UserValidator validator;

    public TaskValidator(UserValidator validator) {
        this.validator = validator;
    }

    //返回字符串的call方法
    @Override
    public String call() throws Exception {
        //如果方法返回false
        if (!validator.validate()) {
            //打印验证失败信息
            System.out.printf("%s: The user has not been found\n",
                    validator.getName());
            //抛出异常
            throw new Exception("Error validating user");
        }
        //否则打印验证成功信息
        System.out.printf("%s: The user has been found\n",
                validator.getName());
        //返回结果
        return validator.getName();
    }
}
