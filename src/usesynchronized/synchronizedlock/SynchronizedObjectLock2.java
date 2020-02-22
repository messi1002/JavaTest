package usesynchronized.synchronizedlock;

/**
 * @author: wjy
 * @date: 2020/2/19
 * @description: 对象锁示例2之方法锁
 */
public class SynchronizedObjectLock2 implements Runnable {
    static SynchronizedObjectLock2 instance = new SynchronizedObjectLock2();
    
    @Override
    public void run() {
        method();
    }
    
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
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
        
        }
        System.out.println("finished");
    }
}
