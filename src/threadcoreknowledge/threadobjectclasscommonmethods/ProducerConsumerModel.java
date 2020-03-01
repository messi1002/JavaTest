package threadcoreknowledge.threadobjectclasscommonmethods;

import java.util.Date;
import java.util.LinkedList;

/**
 * @author: wjy
 * @date: 2020/2/28
 * @description: 用wait和notify来实现(不用阻塞队列)
 * 消费者和生产者不能同时即生产又消费，但是它们是交替进行的。
 */
public class ProducerConsumerModel {
    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}

class EventStorage {
    private int maxSize;
    private LinkedList<Date> storage;
    
    // 初始化
    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<>();
    }
    
    public synchronized void put() {
        if (storage.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            storage.add(new Date());
            System.out.println("仓库里有" + storage.size() + "个产品");
            // 每次生产后都通知消费者来消费
            notify();
        }
    }
    
    public synchronized void take() {
        if (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("拿到了" + storage.poll() + "，仓库容量是" + storage.size());
            // 每次消费后都通知生产者来生产
            notify();
        }
    }
}

class Producer implements Runnable {
    private EventStorage storage;
    
    public Producer(EventStorage storage) {
        this.storage = storage;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.put();
        }
    }
}

class Consumer implements Runnable {
    private EventStorage storage;
    
    public Consumer(EventStorage storage) {
        this.storage = storage;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.take();
        }
    }
}