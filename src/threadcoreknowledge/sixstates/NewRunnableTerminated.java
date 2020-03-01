package threadcoreknowledge.sixstates;

/**
 * @author: wjy
 * @date: 2020/2/27
 * @description: 展示线程的NEW、RUNNABLE、Terminated状态。
 * 即使是正在运行，也是RUNNABLE状态，而不是RUNNING。
 */
public class NewRunnableTerminated implements Runnable {
    
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }        
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new NewRunnableTerminated());
        // 打印出NEW的状态
        System.out.println("1." + thread.getState());
        thread.start();
        // 打印出RUNNABLE的状态
        System.out.println("2." + thread.getState());
        Thread.sleep(10);
        // run方法在运行中——打印出RUNNABLE的状态
        System.out.println("3." + thread.getState());
        Thread.sleep(1000);
        // run方法执行完毕——打印出TERMINATED的状态
        System.out.println("4." + thread.getState());
    }
}
