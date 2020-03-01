package threadcoreknowledge.threadsafety;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: wjy
 * @date: 2020/3/1
 * @description: 第一种线程安全问题: 运行结果出错。
 * 如果不对一个变量进行同步保护，就可能会出现这样的问题。
 * 演示计数不准确(减少)，并找出具体出错的位置。
 * 如何计算出错的位置: 用一个marked数组标记index值，然后用synchronized保护-防止两个线程同时判断和修改marked数组的值，
 * 然后为了防止index被篡改，加了两个闸，让它们一起执行index++，然后依次判断是否出现错误。
 * 然后重写判断条件(两个线程执行index++正确(相当于index值每次加2)和错误的情况(index只加了1))
 */
public class ThreadError1 implements Runnable {
    static ThreadError1 instance = new ThreadError1();
    int index = 0;
    // 初始化值默认为false
    final boolean[] marked = new boolean[20001];
    // 原子整型记录真正运行的次数(具有原子性，线程安全的，类比于i++的三个步骤)
    static AtomicInteger realIndex = new AtomicInteger();
    // 原子整型记录出错的次数(具有原子性，线程安全的，类比于i++的三个步骤)
    static AtomicInteger wrongCount = new AtomicInteger();
    // 让线程根据我们的需要在某一个地方等待，直到等到的线程都就绪了，再一起出发。
    // 参数为2表示需要等待两个线程
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);
    
    @Override
    public void run() {
        // 防止第一次发生错误
        marked[0] = true;
        for (int i = 0; i < 10000; i++) {
            // 防止一个线程的index++在另一个线程执行if判断时将index的值篡改。
            try {
                // 等待前进行重置
                cyclicBarrier2.reset();
                // 两个线程都执行到这里，才放行执行之后的代码。
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            // 让两个线程都完成index++之后，再去做标记的工作，防止一个线程在标记index时被index值另一个线程修改。
            try {
                // 等待前进行重置
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            // 保护这块代码在同一时刻只有一个线程执行，防止在第一个线程执行marked[idnex]=true之前，第二个线程就执行了if判断语句(这样就发现不了错误了)。
            // synchronized可以保证可见性，即对一个线程的修改可以让另一个线程看到。
            // 以上CyclicBarrier类保证统计期间index值不会变化
            synchronized (instance) {
                // 检查其他线程是否已经加到这个数
                // 两个线程正常执行index，第一个线程将之加到1，第二个线程将之加到2，之后两个线程依次执行同步代码块的代码，其index都是2，但是这属于正常情况，此时marked[2]=true，marked[1]=false。
                // 不正常情况: 两个线程不正常执行index，都执行之后index是1(相当于执行了1次)，此时marked[0]和marked[1]都是true。
                if (marked[index] && marked[index - 1]) {
                    System.out.println("发生线程错误" + index);
                    wrongCount.incrementAndGet();
                }
                // 标记这个线程已经加到这个数了
                marked[index] = true;
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("表面上结果是" + instance.index);
        System.out.println("真正的运行次数是" + realIndex.get());
        System.out.println("错误的运行次数是" + wrongCount.get());
    }
}
