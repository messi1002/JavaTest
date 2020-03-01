package threadcoreknowledge.uncaughtexception;

/**
 * @author: wjy
 * @date: 2020/2/29
 * @description: 1.不加try catch抛出4个异常，都带线程名字。
 * 2.加了try catch，期望捕获到第一个线程的异常，此时线程234不应该运行，希望看到打印出Caught Exception。
 * 3.执行时发现和预期不一样，根本没有Caught Exception，且线程234依然运行并且抛出异常。
 * 说明线程的异常不能用传统方法捕获。
 */
public class CantCatchDirectly implements Runnable {

    @Override
    public void run() {
    
//        throw new RuntimeException();
        try {
            throw new RuntimeException();
        }
        catch (RuntimeException e) {
            System.out.println("Caught Exception");
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
//        new Thread(new CantCatchDirectly(), "MyThread-1").start();
//        Thread.sleep(300);
//        new Thread(new CantCatchDirectly(), "MyThread-2").start();
//        Thread.sleep(300);
//        new Thread(new CantCatchDirectly(), "MyThread-3").start();
//        Thread.sleep(300);
//        new Thread(new CantCatchDirectly(), "MyThread-4").start();
//        Thread.sleep(300);
        try {
            new Thread(new CantCatchDirectly(), "MyThread-1").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(), "MyThread-2").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(), "MyThread-3").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(), "MyThread-4").start();
            Thread.sleep(300);
        } catch (RuntimeException e) {
            System.out.println("Caught Exception");
        }
    }
}
