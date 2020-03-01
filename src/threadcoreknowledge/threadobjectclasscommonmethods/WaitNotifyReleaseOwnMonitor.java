package threadcoreknowledge.threadobjectclasscommonmethods;

import java.awt.print.Pageable;

/**
 * @author: wjy
 * @date: 2020/2/28
 * @description: 证明wait只释放当前的那把锁
 */
public class WaitNotifyReleaseOwnMonitor implements Runnable {
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();
    
    static WaitNotifyReleaseOwnMonitor instance = new WaitNotifyReleaseOwnMonitor();
    
    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        }
        else {
            method2();
        }
    }
    
    private void method1() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + "拿到A");
            synchronized (resourceB) {
                System.out.println(Thread.currentThread().getName() + "拿到B");
                try {
                    System.out.println(Thread.currentThread().getName() + "释放A");
                    resourceA.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void method2() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + "拿到A");
            System.out.println(Thread.currentThread().getName() + "尝试获取B");
            // B被第一个线程持有，并且没有释放。
            synchronized (resourceB) {
                System.out.println(Thread.currentThread().getName() + "拿到B");
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        Thread.sleep(500);
        thread2.start();
    }
}
