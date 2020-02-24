package usesynchronized.synchronizedlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wjy
 * @date: 2020/2/23
 * @description: 用lock模拟synchronized加锁和释放锁的时机
 */
public class LockAndUnlock {
    Lock lock = new ReentrantLock();
    
    public synchronized void method1() {
        // 进入方法时隐形地获取一把锁
        System.out.println("我是synchronized形式的锁");
        // 退出方法时隐形地释放一把锁
    }
    
    public void method2() {
        // 加锁
        lock.lock();
        try {
            System.out.println("我是lock形式的锁");
        }
        finally {
            // 释放锁
            lock.unlock();
        }
    }
    
    public static void main(String[] args) {
        // synchronized和lock在底层可以相互类比和配合
        // method1方法和method2方法等价
        // 将method1中synchronized锁住和解锁的时机用method2中拆分的形式表达
        LockAndUnlock l = new LockAndUnlock();
        l.method1();
        l.method2();
    }
}
