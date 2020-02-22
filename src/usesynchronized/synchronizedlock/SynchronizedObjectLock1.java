package usesynchronized.synchronizedlock;

/**
 * @author: wjy
 * @date: 2020/2/19
 * @description: 对象锁实例1之同步代码块
 */
public class SynchronizedObjectLock1 implements Runnable {
    static SynchronizedObjectLock1 instance = new SynchronizedObjectLock1();
    Object lock1 = new Object();
    Object lock2 = new Object();
    
    @Override
    public void run() {
//        默认this
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + "开始执行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "执行结束");
        // 自己构造对象
//        synchronized (lock1) {
//            System.out.println(Thread.currentThread().getName() + "开始执行(lock1)");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(Thread.currentThread().getName() + "执行结束(lock1)");
//
//        synchronized (lock2) {
//            System.out.println(Thread.currentThread().getName() + "开始执行(lock2)");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(Thread.currentThread().getName() + "执行结束(lock2)");
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
