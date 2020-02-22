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
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + "开始运行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "结束运行");
        }
        
//        synchronized (SynchronizedClassLock2.class) {
//            System.out.println(Thread.currentThread().getName() + "开始运行");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + "结束运行");
//        }
    }
    
    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
        
        }
    }
}
