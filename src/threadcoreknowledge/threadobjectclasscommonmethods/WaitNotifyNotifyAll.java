package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @author: wjy
 * @date: 2020/2/28
 * @description: 3个线程，线程0和线程1首先被阻塞，线程2去唤醒这两个线程。
 * 使用notify和notifyAll两种不同的方法。
 * 并且start先执行不代表线程先启动。
 */
public class WaitNotifyNotifyAll implements Runnable {
    public static final Object resourceA = new Object();
    
    static WaitNotifyNotifyAll instance = new WaitNotifyNotifyAll();
    
    @Override
    public void run() {
        if (!Thread.currentThread().getName().equals("Thread-2")) {
            method1();
        }
        else {
            method2();
        }
    }
    
    private void method1() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + "获取到锁(resourceA)");
            try {
                System.out.println(Thread.currentThread().getName() + " wait");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + "重新获取到锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void method2() {
        synchronized (resourceA) {
//            // 只唤醒一个线程，而另一个线程永久等待着被唤醒，程序不会停止。
//            resourceA.notify();
            resourceA.notifyAll();
            System.out.println(Thread.currentThread().getName() + "成功notify");
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        Thread thread3 = new Thread(instance);
        thread1.start();
        thread2.start();
        // 如果主线程不休眠，意味着线程3可能早于线程1或2执行，此时就没有线程唤醒线程1或2了。
//        Thread.sleep(500);
        thread3.start();
    }
}
