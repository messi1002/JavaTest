package threadcoreknowledge.threadsafety;

/**
 * @author: wjy
 * @date: 2020/3/1
 * @description: 第二种线程安全问题，演示死锁(必然死锁)。
 */
public class ThreadError2 implements Runnable {
    static ThreadError2 instance = new ThreadError2();
    static Object o1 = new Object();
    static Object o2 = new Object();
    
    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-0")) {
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + "获取到o1");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + "获取到o2");
                }
            }
        }
        else {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + "获取到o2");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "获取到o1");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        new Thread(instance).start();
        new Thread(instance).start();
    }
}
