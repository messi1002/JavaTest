package threadcoreknowledge.stopthread;

/**
 * @author: wjy
 * @date: 2020/2/27
 * @description: 错误的停止方法: 用stop方法来停止线程，会导致线程运行到一半突然停止，没办法完成一个基本单位的操作，会造成脏数据。
 * suspend并不会破坏对象，但是会让一个线程挂起，在线程恢复之前不会释放锁（带着锁休息，如果其他线程不及时过来把它唤醒，或者是唤醒它的线程需要它的锁才能唤醒它，会造成死锁)。
 */
public class StopThread implements Runnable {
    
    @Override
    public void run() {
        for (int i = 0; i <= Integer.MAX_VALUE; i++) {
            if (i % 100 == 0) {
                System.out.println(i + "是100的倍数");
            }
        }
        System.out.println("执行结束");
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThread());
        thread.start();
        Thread.sleep(1000);
        thread.stop();
    }
}
