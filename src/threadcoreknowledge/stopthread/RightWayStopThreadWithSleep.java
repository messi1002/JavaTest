package threadcoreknowledge.stopthread;

/**
 * @author: wjy
 * @date: 2020/2/26
 * @description: 带有sleep的中断线程的写法
 */
public class RightWayStopThreadWithSleep implements Runnable {
    
    @Override
    public void run() {
        try {
            for (int i = 0; i <= 1000 && !Thread.currentThread().isInterrupted(); i++) {
                if (i % 100 == 0) {
                    System.out.println(i + "是100的倍数");
                }
            }
            // 当线程正在休眠的过程中收到中断信号时，它会响应中断，响应的方式是抛出异常，解决方式是处理这个异常。
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithSleep());
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
