package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @author: wjy
 * @date: 2020/2/28
 * @description: 展示线程sleep的时候不释放synchronized的monitor。
 * 等sleep时间到了之后，正常结束后才释放锁。
 */
public class SleepDontReleaseMonitor implements Runnable {
    static SleepDontReleaseMonitor instance = new SleepDontReleaseMonitor();

    @Override
    public synchronized void run() {
        System.out.println(Thread.currentThread().getName() + "得到锁");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "释放锁");
    }
    
    public static void main(String[] args) {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
    }
}
