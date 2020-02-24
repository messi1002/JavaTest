package usesynchronized.synchronizedlock;

/**
 * @author: wjy
 * @date: 2020/2/19
 * @description: 类锁示例1之静态锁
 */
public class SynchronizedClassLock1 implements Runnable {
    static SynchronizedClassLock1 instance1 = new SynchronizedClassLock1();
    static SynchronizedClassLock1 instance2 = new SynchronizedClassLock1();
    
    @Override
    public void run() {
        method();
    }
    
    // synchronized修饰符修饰静态方法
    // 在全局情况下保护以下代码块串行执行(不是对象的层面)
    public static synchronized void method() {
        System.out.println(Thread.currentThread().getName() + "开始运行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束运行");
    }
    
    public static void main(String[] args) throws InterruptedException {
        // 建立两个线程，两个线程分别引用同一个类的不同实例
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        // 启动两个线程
        t1.start();
        t2.start();
        // 让主线程等待两个子线程都执行完成
        while (t1.isAlive() || t2.isAlive()) {
        
        }
        System.out.println("finished");
    }
}
