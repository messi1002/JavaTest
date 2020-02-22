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
    
//    public synchronized void method() {
//        System.out.println(Thread.currentThread().getName() + "开始运行");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(Thread.currentThread().getName() + "结束运行");
//    }
    
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
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
        
        }
        System.out.println("finished");
    }
}
