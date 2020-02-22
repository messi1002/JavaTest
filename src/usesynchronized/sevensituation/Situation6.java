package usesynchronized.sevensituation;

/**
 * @author: wjy
 * @date: 2020/2/20
 * @description: 同时访问静态synchronized和非静态synchronized方法
 */
public class Situation6 implements Runnable {
    static Situation6 instance = new Situation6();
    
    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        }
        else {
            method2();
        }
    }
    
    public static synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + "开始运行(静态加锁的方法)");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束运行(静态加锁的方法)");
    }
    
    public synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + "开始运行(非静态加锁的方法)");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束运行(非静态加锁的方法)");
    }
    
    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
        
        }
        System.out.println("Situation6.class finished");
    }
}
