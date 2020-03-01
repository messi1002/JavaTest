package threadcoreknowledge.stopthread.volatiledemo;

/**
 * @author: wjy
 * @date: 2020/2/27
 * @description: 演示用Volatile的局限: part1——看似可行。
 * 即看似可以利用volatile中断线程。
 */
public class WrongWayVolatile implements Runnable {
    
    // volatile可以让变量具有可见性
    private volatile boolean canceled = false;
    
    @Override
    public void run() {
        try {
            // 如果canceled为false,表示没有线程被中断
            for (int i = 0; i <= 10000 && !canceled; i++) {
                if (i % 100 == 0) {
                    System.out.println(i + "是100的倍数");
                }
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile w = new WrongWayVolatile();
        Thread thread = new Thread(w);
        thread.start();
        Thread.sleep(5000);
        // 中断线程
        w.canceled = true;
    }
}
