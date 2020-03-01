package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @author: wjy
 * @date: 2020/2/28
 * @description: 演示打印main、Thread-0、Thread-1。
 */
public class CurrentThread implements Runnable {
    
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
    
    public static void main(String[] args) {
        // 调用主线程的run方法
        new CurrentThread().run();
        new Thread(new CurrentThread()).start();
        new Thread(new CurrentThread()).start();
    }
}
