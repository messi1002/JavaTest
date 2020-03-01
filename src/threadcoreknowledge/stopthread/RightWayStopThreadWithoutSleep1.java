package threadcoreknowledge.stopthread;

/**
 * @author: wjy
 * @date: 2020/2/26
 * @description: run方法内没有sleep或wait方法时，停止线程。
 */
public class RightWayStopThreadWithoutSleep1 implements Runnable {
    
    @Override
    public void run() {
        for (int i = 0; i <= Integer.MAX_VALUE / 2; i++) {
            if (i % 10000 == 0) {
                System.out.println(i + "是10000的倍数");
            }
        }
        System.out.println("任务运行结束了");
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep1());
        thread.start();
        // 主线程沉睡1秒钟
        Thread.sleep(1000);
        // 中断子线程，没有任何效果，因为想不想停止取决于子线程本身，我们需要对它进行响应工作的编写。
        thread.interrupt();
    }
}
