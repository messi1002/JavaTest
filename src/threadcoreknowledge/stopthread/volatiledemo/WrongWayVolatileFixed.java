package threadcoreknowledge.stopthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: wjy
 * @date: 2020/2/27
 * @description: 用中断来修复刚才的无尽等待问题
 */
public class WrongWayVolatileFixed {
    
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        Producer2 producer = new Producer2(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);
        Consumer2 consumer = new Consumer2(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了");
        producerThread.interrupt();
        Thread.interrupted();
    }
}

class Producer2 implements Runnable {
    // 阻塞队列
    BlockingQueue storage;
    
    public Producer2(BlockingQueue storage) {
        this.storage = storage;
    }
    
    @Override
    public void run() {
        try {
            for (int i = 0; i <= 10000 && !Thread.currentThread().isInterrupted(); i++) {
                if (i % 100 == 0) {
                    storage.put(i);
                    System.out.println(i + "是100的倍数，被放到仓库中了。");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者结束运行");
        }
    }
}

class Consumer2 {
    BlockingQueue storage;
    
    public Consumer2(BlockingQueue storage) {
        this.storage = storage;
    }
    
    public boolean needMoreNums() {
        if (Math.random() > 0.95) {
            return false;
        }
        return true;
    }
}
