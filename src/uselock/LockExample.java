package uselock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wjy
 * @date: 2020/2/23
 * @description: 展示Lock的方法
 */
public class LockExample {
    
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        // 通过这两个方法灵活得控制这把锁，且可以配置自定义的锁。
        // 锁住
        lock.lock();
        // 释放锁
        lock.unlock();
        // 在规定的超时时间内等待获得锁。
        try {
            // 不设置超时时间的tryLock()
            lock.tryLock();
            // 若是10s拿不到锁，会主动放弃。
            lock.tryLock(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
