package threadcoreknowledge.wrongways;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: wjy
 * @date: 2020/2/26
 * @description: 线程池创建线程的方法
 */
public class ThreadPool {
    
    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            // 提交任务
            executorService.submit(new Task() {
            });
        }
    }
}

class Task implements Runnable {
    
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}