package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * @author: wjy
 * @date: 2020/2/28
 * @description: 1.先join再看mainThread.getState()
 * 2.通过debugger查看线程join前后状态的对比。
 */
public class JoinThreadState implements Runnable {
    static JoinThreadState instance = new JoinThreadState();
    Thread mainThread = Thread.currentThread();
    
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println(mainThread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "运行完毕");
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(instance);
        thread.start();
        System.out.println("等待子线程运行完毕");
        thread.join();
        System.out.println("子线程运行完毕");
    }
}
