package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wjy
 * @date: 2020/2/28
 * @description: 展示线程sleep的时候不释放lock(lock独占锁本身就需要手动释放)。
 */
public class SleepDontReleaseLock implements Runnable {
    private static final Lock lock = new ReentrantLock();
    static SleepDontReleaseLock instance = new SleepDontReleaseLock();
    
    @Override
    public void run() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "获取锁");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放锁");
            lock.unlock();
        }
    }
    
    public static void main(String[] args) {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
    }
}
