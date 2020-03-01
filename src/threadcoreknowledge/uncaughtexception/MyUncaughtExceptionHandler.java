package threadcoreknowledge.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author: wjy
 * @date: 2020/3/1
 * @description: 自己的异常处理器
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    
    private String name;
    
    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }
    
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, "线程异常终止了" + t.getName(), e);
        System.out.println(name + "捕获了异常" + t.getName());
    }
}
