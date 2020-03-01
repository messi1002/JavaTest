package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @author: wjy
 * @date: 2020/2/28
 * @description: 演示join期间被中断的效果。
 */
public class JoinInterrupt implements Runnable {
    static JoinInterrupt instance = new JoinInterrupt();
    Thread mainThread = Thread.currentThread();
    
    @Override
    public void run() {
        try {
            // 子线程对主线程进行中断。
            mainThread.interrupt();
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "执行完毕");
        } catch (InterruptedException e) {
            System.out.println("子线程中断");
        }
    }
    
    public static void main(String[] args) {
        Thread thread = new Thread(instance);
        System.out.println("等待子线程运行完毕");
        thread.start();
        try {
            // 子线程加入到主线程时抛出异常，所以是主线程被中断，抛出了异常。
            // 主线程在等待期间被中断
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断了");
            // 当主线程获取到中断时将中断传递给子线程
            thread.interrupt();
        }
        // 在打印出子线程运行完毕后子线程还没有运行完毕。
        // 所以一旦join期间被打断，需要我们在处理中断异常时，把子线程停止，否则会出现主线程停止而子线程没有停止，不一致的情况。
        System.out.println("子线程已经运行完毕");
    }
}
