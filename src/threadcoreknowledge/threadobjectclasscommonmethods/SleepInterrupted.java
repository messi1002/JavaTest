package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author: wjy
 * @date: 2020/2/28
 * @description: 每隔一秒钟输出当前时间，过段时间被中断然后观察，第二种写法更优雅。
 * 第一种写法: Thread.sleep()
 * 第二种写法: TimeUnit.SECONDS.sleep()
 */
public class SleepInterrupted implements Runnable {
    static SleepInterrupted instance = new SleepInterrupted();
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date());
            try {
                // 每隔一秒打印当前时间
                // 第二种方法更优雅和清晰明了
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被中断了");
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(instance);
        thread.start();
        Thread.sleep(3000);
        thread.interrupt();
    }
}
