package usesynchronized.synchronizedlock;

/**
 * @author: wjy
 * @date: 2020/2/19
 * @description: 对象锁示例2之方法锁形式
 */
public class SynchronizedObjectLock2 implements Runnable {
    static SynchronizedObjectLock2 instance = new SynchronizedObjectLock2();
    
    @Override
    public void run() {
        method();
    }
    
    // 对普通方法加上synchronized修饰符，保护以下代码块串行执行。
    public synchronized void method() {
        System.out.println(Thread.currentThread().getName() + "对象锁的方法修饰符形式");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行结束");
    }
    
    public static void main(String[] args) throws InterruptedException {
        // 建立两个线程，利用同一个实例，共用实例中的方法。
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        // 启动两个线程
        t1.start();
        t2.start();
        // 让主线程等待两个子线程都执行完成
        while (t1.isAlive() || t2.isAlive()) {
        
        }
        System.out.println("finished");
    }
}
