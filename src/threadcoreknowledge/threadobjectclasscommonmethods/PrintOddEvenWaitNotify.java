package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @author: wjy
 * @date: 2020/2/28
 * @description: 两个线程交替打印0~100的奇偶数，用wait和notify。
 * 新建两个线程，一个只处理偶数，一个只处理奇数。
 * 拿到锁我们就打印
 * 打印完，就唤醒其他线程，自己休眠。
 */
public class PrintOddEvenWaitNotify implements Runnable {
    
    static PrintOddEvenWaitNotify instance = new PrintOddEvenWaitNotify();
    public static int count = 1;
    
    @Override
    public synchronized void run() {
        while (count <= 100) {
            System.out.println(count++);
            this.notify();
            try {
                // 如果任务还没结束，就让出当前的锁。
                if (count <= 100) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
    }
}
