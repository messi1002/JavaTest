package usesynchronized.sevensituation;

/**
 * @author: wjy
 * @date: 2020/2/20
 * @description: 同时访问同步和非同步方法
 */
public class Situation4 implements Runnable {
    static Situation4 instance = new Situation4();
    
    @Override
    public void run() {
        // 线程的默认名字是从Thread-0(Thread-1、Thread-2、...)开始
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        }
        else {
            method2();
        }
    }
    
    public synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + "开始运行(加锁的方法)");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束运行(加锁的方法)");
    }
    
    public void method2() {
        System.out.println(Thread.currentThread().getName() + "开始运行(没加锁的方法)");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束运行(没加锁的方法)");
    }
    
    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
        
        }
        System.out.println("Situation4.class finished");
    }
}
