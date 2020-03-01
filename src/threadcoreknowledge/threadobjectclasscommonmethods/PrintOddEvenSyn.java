package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @author: wjy
 * @date: 2020/2/28
 * @description: 两个线程交替打印0~100的奇偶数，用synchronize关键字实现。
 * 新建两个线程，一个只处理偶数，一个只处理奇数(用位运算)。
 * 用synchronized来通信(两个线程共用同一把锁)
 * 缺点:效率不高，有很多重复的判断。
 */
public class PrintOddEvenSyn implements Runnable {
    static PrintOddEvenSyn instance = new PrintOddEvenSyn();
    private static int count = 1;
    private static final Object object = new Object();
    
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
        while (count < 100) {
            synchronized (object) {
//                if (count % 2 == 1) {
                // 用位运算提高效率
                if ((count & 1) == 1) {
                    System.out.println(count++);
                }
            }
        }
    }
    
    private void method2() {
        while (count <= 100) {
            synchronized (object) {
                if ((count & 1) == 0) {
                    System.out.println(count++);
                }
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        // count的初始值是1，所以两个线程同时启动时，只有一个线程可以拿到锁。
        // 当打印奇数的线程先执行时，打印1，此时进入下一轮循环，两个线程开始不断地竞争锁。
        // 若是打印偶数的线程竞争到成功打印出下一个偶数2，否则打印奇数的线程竞争到不满足if条件，进入下一轮循环，继续竞争。
        // 若是不符合if条件的线程多次竞争到锁，这属于浪费的竞争。
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
    }
}
