package usesynchronized.sevensituation;

/**
 * @author: wjy
 * @date: 2020/2/20
 * @description: 同时访问一个类的不同的普通同步方法
 */
public class Situation5 implements Runnable {
    static Situation5 instance = new Situation5();
    
    @Override
    public void run() {
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
    
    public synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + "开始运行(加锁的方法)");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束运行(加锁的方法)");
    }
    
    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
        
        }
        System.out.println("Situation5.class finished");
    }
}
