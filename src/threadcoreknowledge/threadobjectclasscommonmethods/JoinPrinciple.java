package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @author: wjy
 * @date: 2020/2/28
 * @description: 通过讲解join原理，分析出join的代替写法。
 */
public class JoinPrinciple implements Runnable {
    static JoinPrinciple instance = new JoinPrinciple();
    
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "执行完毕");
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(instance);
        thread.start();
        System.out.println("等待子线程运行完毕");
        // 主线程拿到这把锁(thread实例本身作为锁)
        thread.join();
        // ?
        synchronized (thread) {
            // 主线程进入休眠状态，等子线程执行完之后，会唤醒它。
            thread.wait();
        }
        System.out.println("子线程执行完毕");
    }
}
