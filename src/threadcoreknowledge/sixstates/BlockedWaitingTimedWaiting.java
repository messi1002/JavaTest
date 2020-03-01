package threadcoreknowledge.sixstates;

/**
 * @author: wjy
 * @date: 2020/2/27
 * @description: 展示线程的Blocked、Waiting、TimedWaiting状态。
 */
public class BlockedWaitingTimedWaiting implements Runnable {
    static BlockedWaitingTimedWaiting instance = new BlockedWaitingTimedWaiting();
    
    @Override
    public void run() {
        method();
    }
    
    private synchronized void method() {
        try {
            System.out.println(Thread.currentThread().getName() + "开始执行");
            // 在sleep过程中是time_waiting
            Thread.sleep(2000);
            // 在wait过程中是waiting
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        // 线程1和线程2共用一个实例
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        Thread.sleep(500);
        // 线程2进入run方法时，锁被线程1拿走了，且线程1在睡眠状态。
        // 线程1是Timed_Waiting状态，因为正在执行Thread.sleep(2000);
        System.out.println(thread1.getState());
        // 线程2是Blocked状态，因为线程2想拿到method方法的锁却拿不到。
        System.out.println(thread2.getState());
        Thread.sleep(2000);
        // 打印出WAITING状态，因为执行了wait()。
        System.out.println(thread1.getState());
        // 如果是synchronized声明的方法，wait()操作后会施放synchronized锁，相反notify()触发后会重拿起synchronized锁。
        // 此时线程1开始执行，开始沉睡。
        System.out.println(thread2.getState());
        Thread.sleep(2000);
        // 打印出WAITING状态
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
    }
}
