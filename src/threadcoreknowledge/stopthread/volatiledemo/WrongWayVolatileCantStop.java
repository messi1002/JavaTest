package threadcoreknowledge.stopthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: wjy
 * @date: 2020/2/27
 * @description: 演示用volatile的局限part2: 陷入阻塞时，volatile是无法停止线程的。
 * 举例说明这种方法不适用的情况，来证明它的局限性。
 * 此例中，生产者的生产速度很快，消费者的消费速度很慢。
 * 所以阻塞队列满了以后，生产者会阻塞，等待消费者进一步消费。
 */
public class WrongWayVolatileCantStop {
    
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        Producer1 producer = new Producer1(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);
        Consumer1 consumer = new Consumer1(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了");
        // 一旦消费者不需要更多数据了，我们应该让生产者也停下来，否则就浪费了。
        // 但是实际情况是线程不会停止，因为它面对线程长时间阻塞的情况是没有办法唤醒的。
        producer.canceled = true;
        System.out.println(producer.canceled);
    }
}

// 生产者生产出100的倍数并放入仓库中
class Producer1 implements Runnable {
    public volatile boolean canceled = false;
        
    // 阻塞队列
    BlockingQueue storage;
    
    public Producer1(BlockingQueue storage) {
        this.storage = storage;
    }
    
    @Override
    public void run() {
        try {
            // 只有在进入循环时才会检查
            for (int i = 0; i <= 10000 && !canceled; i++) {
                if (i % 100 == 0) {
                    // 但是阻塞到这里了(没有人唤醒它)，因为put满之后不会继续执行下面的代码
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

// 消费者需要时会消费，一旦消费者不需要了，让生产者也停下来。
class Consumer1 {
    BlockingQueue storage;
    
    public Consumer1(BlockingQueue storage) {
        this.storage = storage;
    }
    
    public boolean needMoreNums() {
        if (Math.random() > 0.95) {
            return false;
        }
        return true;
    }
}
