package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @author: wjy
 * @date: 2020/2/28
 * @description: 演示join用法，注意语句输出顺序，会变化。
 */
public class Join implements Runnable {
    static Join instance = new Join();
    
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
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        System.out.println("等待子线程运行完毕");
        thread1.join();
        thread2.join();
        System.out.println("所有子线程执行完毕");
    }
}
