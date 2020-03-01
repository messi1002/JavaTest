package threadcoreknowledge.wrongways;

import java.util.Timer;

/**
 * @author: wjy
 * @date: 2020/2/26
 * @description: 定时器创建线程
 */
public class TimerTaskDemo {
    
    // 每隔一秒打印出子线程的名字
    public static void main(String[] args) {
        Timer timer = new Timer();
        // 初始delay和周期
        timer.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}
