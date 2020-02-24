package usesynchronized.synchronizedlock;

/**
 * @author: wjy
 * @date: 2020/2/19
 * @description: 类锁示例2之Class对象
 */
public class SynchronizedClassLock2 implements Runnable {
    static SynchronizedClassLock2 instance1 = new SynchronizedClassLock2();
    static SynchronizedClassLock2 instance2 = new SynchronizedClassLock2();
    
    @Override
    public void run() {
        method();
    }
    
    private void method() {
        // 锁对象是SynchronizedClassLock2.class
        // 无论是类的哪个实例用的都是同一个锁对象(串行执行)
        synchronized (SynchronizedClassLock2.class) {
            System.out.println(Thread.currentThread().getName() + "开始运行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束运行");
        }
    }
    
    public static void main(String[] args) {
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
