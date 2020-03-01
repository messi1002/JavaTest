package threadcoreknowledge.uncaughtexception;

/**
 * @author: wjy
 * @date: 2020/3/1
 * @description: 使用自己的异常处理器处理异常
 */
public class UseOwnUncaughtExceptionHandler implements Runnable {
    
    @Override
    public void run() {
        throw new RuntimeException();
    }
    
    public static void main(String[] args) throws InterruptedException {
        // 给线程设置自己的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-1").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-2").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-3").start();
        Thread.sleep(300);
        new Thread(new UseOwnUncaughtExceptionHandler(), "MyThread-4").start();
        Thread.sleep(300);
    }
}
