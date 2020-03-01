package threadcoreknowledge.stopthread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wjy
 * @date: 2020/2/26
 * @description: run方法内没有sleep或wait方法时，停止线程。
 */
public class RightWayStopThreadWithoutSleep2 implements Runnable {
    
    @Override
    public void run() {
        for (int i = 0; i <= Integer.MAX_VALUE / 2 && !Thread.currentThread().isInterrupted(); i++) {
            if (i % 10000 == 0) {
                System.out.println(i + "是10000的倍数");
            }
        }
        System.out.println("任务运行结束了");
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep2());
        thread.start();
        // 主线程沉睡1秒钟
        Thread.sleep(1000);
        // 中断子线程，已经做了响应工作的编写，成功中断。
        thread.interrupt();
    }
}
