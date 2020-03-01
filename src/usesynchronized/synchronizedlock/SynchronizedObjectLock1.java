package usesynchronized.synchronizedlock;

/**
 * @author: wjy
 * @date: 2020/2/19
 * @description: 对象锁实例1之同步代码块形式
 */
public class SynchronizedObjectLock1 implements Runnable {
    static SynchronizedObjectLock1 instance = new SynchronizedObjectLock1();
    // 创建自定义锁对象，lock1和lock2保护的时机不相同。
    Object lock1 = new Object();
    Object lock2 = new Object();
    
    @Override
    public void run() {
//        // 默认用this(当前对象)作为锁对象
//        // 保护以下代码块串行执行
//        synchronized (this) {
//            System.out.println(Thread.currentThread().getName() + "开始执行");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + "执行结束");
//        }
        // 自定义锁对象
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + "开始执行(lock1)");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行结束(lock1)");
        }
        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName() + "开始执行(lock2)");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "执行结束(lock2)");
        }
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
