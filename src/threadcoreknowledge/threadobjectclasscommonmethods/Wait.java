package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @author: wjy
 * @date: 2020/2/27
 * @description: 展示wait和notify的基本用法
 * 1.研究代码的执行顺序
 * 2.证明wait方法释放锁(在synchronized代码块内说明拿到了这把锁，执行wait方法会释放这把锁)
 */
public class Wait implements Runnable {
    static Wait instance = new Wait();
    // 为了执行wait方法和notify方法
    public static Object object = new Object();
    
    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            method1();
        }
        else {
            method2();
        }
    }
    
    private void method1() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName() + "开始执行");
            // 若在执行等待期间如果遇到中断，则抛出异常。
            try {
                // 释放monitor锁
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "重新拿到了锁");
        }
    }
    
    private void method2() {
        synchronized (object) {
            // 唤醒线程1，但线程2依然持有这把锁。
            object.notify();
            System.out.println(Thread.currentThread().getName() + "调用了notify方法");
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
    }
}
